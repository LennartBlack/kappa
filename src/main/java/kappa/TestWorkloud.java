package kappa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kappa.model.Cable;
import kappa.model.CableCoreDataDB;
import kappa.model.MySqlManager;

public class TestWorkloud {

    public static void main(String[] args) {
 
        // Enhält alle Kabel
        CableCoreDataDB ccddb = new CableCoreDataDB();

        // Enthält 10 Kabel mit der größten Workloud
        ArrayList<Double> workloud = new ArrayList<Double>();
        
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
                if(workloud.size() < 11) {
                    workloud.add(integral);
                }
                else {
                    for(int k = 0; k < workloud.size(); k++) {
                        if(workloud.get(k) < integral) {
                            workloud.remove(k);
                            workloud.add(integral);
                            break;
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Done"); 
        for(Double d : workloud) {
            System.out.println(d);
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
