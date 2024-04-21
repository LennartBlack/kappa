package kappa.loadScripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCSVCoreDataToDb {
    public static void main(String[] args) {
        List<String> sqlImports = new ArrayList<>();
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
                    Double length = checkAndParseDouble(parts[7]);
                    int yearOfConstruction = checkAndParseInt(parts[8]);
                    int crossSection = checkAndParseInt(parts[9]);

                    String query = "INSERT INTO coredata (name, start, end, resistance, reactance, ampacity, length, yearOfConstruction, crossSection) VALUES " +
                    "('" + id + "', '" + start + "', '" + end + "', " + resistance + ", " + reactance +
                    ", " + ampacity + ", " + length + ", " + yearOfConstruction + ", " + crossSection + "); ";
                    sqlImports.add(query);
                }
            }
            erstelleSqlImportsInDatei(sqlImports, "sql_imports_coredate.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     // Methods
     private static String checkAndParse(String value) {
        return (value != null && !value.isEmpty()) ? value : "";
    }

    private static Double checkAndParseDouble(String value) {
        return (value != null && !value.isEmpty()) ? Double.parseDouble(value) : 0.0;
    }

    private static int checkAndParseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }
    private static void erstelleSqlImportsInDatei(List<String> sqlImports, String dateiName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiName))) {
            for (String sqlImport : sqlImports) {
                writer.write(sqlImport);
                writer.newLine(); // Neue Zeile für jeden SQL-Import
            }
            System.out.println("SQL-Imports wurden in die Datei '" + dateiName + "' geschrieben.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
