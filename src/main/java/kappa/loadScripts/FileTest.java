package kappa.loadScripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {

        List<String> sqlImports = new ArrayList<>();
        List<String> sqlcreatetables = new ArrayList<>();
        List<String> dropAllNotMaxValuesAtAFixDate = new ArrayList<>();

        //String verzeichnisPfad = "C:\\Users\\Lennart Schwartz\\OneDrive - IBS IT & Business School Oldenburg e. V\\Desktop\\messwerte";
        String verzeichnisPfad = "C:\\ProgramData\\MySQL\\MySQL Server 8.3\\Uploads\\messwerte";

        File verzeichnis = new File(verzeichnisPfad);

        if (verzeichnis.exists() && verzeichnis.isDirectory()) {
            File[] dateien = verzeichnis.listFiles();

            // Überprüfe, ob Dateien vorhanden sind
            if (dateien != null) {
                // Iteriere durch die Dateien
                for (File datei : dateien) {
                    if (datei.isFile() && datei.getName().endsWith(".csv")) {
                        // Extrahiere Techplatz und Anlage aus dem Dateinamen
                        String dateiName = datei.getName();
                        String techplatz = dateiName.substring(0, 4); // Annahme: Techplatz besteht aus den ersten 4 Zeichen
                        String anlage = dateiName.substring(9, 18); // Annahme: Anlage besteht aus den ersten 9 Zeichen

                        // Fitler alle Maximalwerte für jeden Zeitstempel
                        if(!dropAllNotMaxValuesAtAFixDate.contains("DELETE FROM " + techplatz +
                        " WHERE (date, ampere, id) NOT IN ( " +
                            " SELECT date, ampere, id " +
                            " FROM (" +
                                " SELECT date, ampere, id," +
                                       " ROW_NUMBER() OVER (PARTITION BY date ORDER BY ampere DESC, id) AS row_num" +
                                " FROM " + techplatz +
                            ") AS temp" +
                            " WHERE row_num = 1" +
                        " );"))
                        dropAllNotMaxValuesAtAFixDate.add("DELETE FROM " + techplatz +
                        " WHERE (date, ampere, id) NOT IN ( " +
                            " SELECT date, ampere, id " +
                            " FROM (" +
                                " SELECT date, ampere, id," +
                                       " ROW_NUMBER() OVER (PARTITION BY date ORDER BY ampere DESC, id) AS row_num" +
                                " FROM " + techplatz +
                            ") AS temp" +
                            " WHERE row_num = 1" +
                        " );");
                        
                        // SQL-Befehl zum erstellen der Tabellen für jedes Kabel
                        if(!sqlcreatetables.contains("create table " + techplatz + " like ewes2;")){
                            sqlcreatetables.add("create table " + techplatz + " like ewes2;");
                        }
                        sqlImports.add(
                        "LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.3/Uploads/messwerte/" + techplatz + "_____" + anlage + ".csv'" +
                        "INTO TABLE " + techplatz + 
                        " FIELDS TERMINATED BY ','"+
                        "LINES TERMINATED BY '\n'" +
                        "IGNORE 1 LINES" +
                        "(date, ampere)" +
                        "SET anlage = '" + anlage + "', date = DATE_SUB(date, INTERVAL 1 HOUR);");
                    }
                }
            }
        } else {
            System.out.println("Das Verzeichnis existiert nicht oder ist kein Verzeichnis.");
        }
        schreibeSqlImportsInDatei(sqlImports, "sql_imports.txt");
        schreibeSqlImportsInDatei(sqlcreatetables, "sqlcreatetables.txt");
        schreibeSqlImportsInDatei(dropAllNotMaxValuesAtAFixDate, "dropAllNotMaxValuesAtAFixDate.txt");
    }
    private static void schreibeSqlImportsInDatei(List<String> sqlImports, String dateiName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiName))) {
            for (String sqlImport : sqlImports) {
                writer.write(sqlImport);
                writer.newLine();
            }
            System.out.println("SQL-Imports wurden in die Datei '" + dateiName + "' geschrieben.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static List<String> getDateiInfo(){
        List<String> availibleCableIds = new ArrayList<>();
        
        // Pfad zum Verzeichnis
        String verzeichnisPfad = "C:\\Users\\Lennart Schwartz\\OneDrive - IBS IT & Business School Oldenburg e. V\\Desktop\\messwerte";

        // Erstelle eine Dateiobjekt für das Verzeichnis
        File verzeichnis = new File(verzeichnisPfad);

        // Überprüfe, ob das Verzeichnis existiert und ein Verzeichnis ist
        if (verzeichnis.exists() && verzeichnis.isDirectory()) {
            // Erstelle eine ArrayList für die Dateinamen und zugehörigen Techplätze und Anlagen

            // Erhalte eine Liste von Dateien im Verzeichnis
            File[] dateien = verzeichnis.listFiles();

            // Überprüfe, ob Dateien vorhanden sind
            if (dateien != null) {
                // Iteriere durch die Dateien
                for (File datei : dateien) {
                    if (datei.isFile() && datei.getName().endsWith(".csv")) {
                        // Extrahiere Techplatz und Anlage aus dem Dateinamen
                        String dateiName = datei.getName();
                        String techplatz = dateiName.substring(0, 4);
                        String anlage = dateiName.substring(9, 18);

                        // Speichere Techplatz und Anlage in einem Array
                        String[] dateiInfo = {techplatz};
                        availibleCableIds.add(techplatz);
                    }
                }
            }
        } else {
            System.out.println("Das Verzeichnis existiert nicht oder ist kein Verzeichnis.");
        }
        return availibleCableIds;
    }
}

