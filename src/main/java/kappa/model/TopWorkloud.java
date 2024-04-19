package kappa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kappa.FileTest;

public class TopWorkloud  extends HashMap<Cable, Double>{
    
    // Attributes
    private CableCoreDataDB ccddb;

    // Constructor
    public TopWorkloud(CableCoreDataDB ccddb){
        this.ccddb = ccddb;
        // Enthält alle Kabel, die in der Datenbank vorhanden sind
        List<String> availibleCableIds = FileTest.getDateiInfo();
        ArrayList<Cable> availibleCables = new ArrayList<Cable>();

        for(String str : availibleCableIds) {
            if(ccddb.containsKey(str)){
                availibleCables.add(ccddb.get(str));
            }
        }
        try {
            Connection connection = MySqlManager.getConnection();

            for(int i = 0; i < availibleCables.size(); i++) {
            

                double integral = calculateIntegral(availibleCables.get(i), connection);
                if(this.size() < 11) {
                    this.put(availibleCables.get(i), integral);
                }
                else {
                    for(Map.Entry<Cable, Double> entry : this.entrySet()) {
                        if(entry.getValue() < integral) {
                            this.remove(entry.getKey());
                            this.put(availibleCables.get(i), integral);
                            break;
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Map.Entry<Cable, Double> entry : this.entrySet()){
            System.out.println("Top workload Cable is: " + entry.getKey().getId() + " with " + entry.getValue());
        }
    }
    public static double calculateIntegral(Cable cable, Connection connection) throws SQLException {
        double ampacity = cable.getAmpacity();
        double workloud = 0;
        String sql = "Select Count(*) as Anzahl, sum(abs(ampere)) as SumAmpere from " + cable.getId() + ";";
        try {
            ResultSet rs = MySqlManager.executeQuery(sql, connection);
            rs.next();
            double sumAmpere = rs.getDouble("SumAmpere");
            double anzahl = rs.getDouble("Anzahl");
            double averageAmpere = sumAmpere / anzahl;
            workloud = averageAmpere / ampacity  * 100.0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return workloud;
    }
}
