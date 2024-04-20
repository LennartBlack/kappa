package kappa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kappa.FileTest;

public class TopWorkloud extends HashMap<Cable, Double> {

    // Attributes
    private CableCoreDataDB ccddb;
    private final ArrayList<Cable> availibleCables = new ArrayList<Cable>();
    private Connection connection;

    // Constructor
    public TopWorkloud(CableCoreDataDB ccddb) {
        this.ccddb = ccddb;

        // TODO: Hier muss eine Liste mit allen Kabeln erstellt werden.
        // Enthält alle Kabel, die in der Datenbank vorhanden sind
        List<String> availibleCableIds = FileTest.getDateiInfo();
        for (String str : availibleCableIds) {
            if (ccddb.containsKey(str)) {
                this.availibleCables.add(this.ccddb.get(str));
            }
        }
        // TODO: ENDE

        try {
            this.connection = MySqlManager.getConnection();

            // Für alle Kabel
            determineTopWorkloud();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Cable, Double> entry : this.entrySet()) {
            System.out.println("Top workload Cable is: " + entry.getKey().getId() + " with " + entry.getValue());
        }
    }

    // Methods
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
    public double calculateIntegral(Cable cable){
        double ampacity = cable.getAmpacity();
        double workloud = 0;
        String sql = "Select Count(*) as Anzahl, sum(abs(ampere)) as SumAmpere from " + cable.getId() + ";";
        try {
            ResultSet rs = MySqlManager.executeQuery(sql, this.connection);
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
    // TODO: Entfernen
    public void createTopWorkloudGui(){
        for (Map.Entry<Cable, Double> entry : this.entrySet()) {
            //entry.getKey() hier hat man das cable
            //entry.getValue() hier hat man den workloud

        } 
    }
}
