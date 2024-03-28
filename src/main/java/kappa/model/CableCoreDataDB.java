package kappa.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CableCoreDataDB extends HashMap<String, Cable> {

    public CableCoreDataDB() {
        super();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/leitungsgebilde.csv"))) {
            String line = "";
            String csvSplitBy = ",";
            reader.readLine();
            while (line != null && (line = reader.readLine()) != null) {
                String[] parts = line.split(csvSplitBy);
                if (parts.length == 10) {
                    String id = checkAndParse(parts[0]);
                    String start = checkAndParse(parts[1]);
                    String end = checkAndParse(parts[2]);
                    Double resistance = checkAndParseDouble(parts[3]);
                    Double reactance = checkAndParseDouble(parts[4]);
                    Double ampacity = checkAndParseDouble(parts[5]);
                    Double electricity = checkAndParseDouble(parts[6]);
                    Double length = checkAndParseDouble(parts[7]);
                    int yearOfConstruction = checkAndParseInt(parts[8]);
                    int crossSection = checkAndParseInt(parts[9]);

                    Cable cable = new Cable(id, start, end, resistance, reactance, ampacity, electricity, length,
                            yearOfConstruction, crossSection);

                    this.put(cable.getId(), cable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String checkAndParse(String value) {
        return (value != null && !value.isEmpty()) ? value : "";
    }

    private Double checkAndParseDouble(String value) {
        return (value != null && !value.isEmpty()) ? Double.parseDouble(value) : 0.0;
    }

    private int checkAndParseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }
}
