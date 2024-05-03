package kappa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopWorkloud extends HashMap<Cable, Double> {

    // Attributes
    private CableCoreDataDB ccddb;
    private final ArrayList<Cable> availibleCables = new ArrayList<Cable>();
    private Connection connection;

    // Constructor
    public TopWorkloud(CableCoreDataDB ccddb) {
        this.ccddb = ccddb;

        try {
            Connection connection = MySqlManager.getConnection();
            String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'kappa';";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                String tableName = rs.getString("table_name");
                if(this.ccddb.containsKey(tableName.toUpperCase())){
                    this.availibleCables.add(this.ccddb.get(tableName.toUpperCase()));
                }
                i++;
                if(i == 300){
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Top Workloud failed.");

        }
        try {
            this.connection = MySqlManager.getConnection();
            determineTopWorkloud();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determines the top workloud of each cable
     */
    private void determineTopWorkloud() {
        for (int i = 0; i < this.availibleCables.size(); i++) {
            double integral = calculateIntegral(this.availibleCables.get(i));
            if (this.size() < 11) {
                this.put(this.availibleCables.get(i), integral);
            } else {
                for (Map.Entry<Cable, Double> entry : this.entrySet()) {
                    if (entry.getValue() < integral) {
                        this.remove(entry.getKey());
                        this.put(this.availibleCables.get(i), integral);
                        break;
                    }
                }
            }
        }
    }
    private double calculateIntegral(Cable cable){
        double ampacity = cable.getAmpacity();
        double workloud = 0;
        String sqlQuery = "Select Count(*) as Anzahl, sum(abs(ampere)) as SumAmpere from " + cable.getId() + ";";
        try {
            ResultSet rs = MySqlManager.executeQuery(sqlQuery, this.connection);
            rs.next();
            double sumAmpere = rs.getDouble("SumAmpere");
            double anzahl = rs.getDouble("Anzahl");
            double averageAmpere = sumAmpere / anzahl;
            workloud = averageAmpere / ampacity * 100.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return workloud;
    }
}
