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
        List<String> dropModZwei = new ArrayList<>();

        // Pfad zum Verzeichnis
        String verzeichnisPfad = "C:\\Users\\Lennart Schwartz\\OneDrive - IBS IT & Business School Oldenburg e. V\\Desktop\\messwerte";

        // Erstelle eine Dateiobjekt für das Verzeichnis
        File verzeichnis = new File(verzeichnisPfad);

        // Überprüfe, ob das Verzeichnis existiert und ein Verzeichnis ist
        if (verzeichnis.exists() && verzeichnis.isDirectory()) {
            // Erstelle eine ArrayList für die Dateinamen und zugehörigen Techplätze und Anlagen
            List<String[]> dateiInformationen = new ArrayList<>();

            // Erhalte eine Liste von Dateien im Verzeichnis
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

                        // Speichere Techplatz und Anlage in einem Array
                        String[] dateiInfo = {techplatz, anlage};


                        // Löschen mod 2 = 1
                        dropModZwei.add("delete from " + techplatz + " where mod(id, 2) = 1;");
                        // Erstelle SQL-Import-Befehl
                        if(!sqlcreatetables.contains("create table " + techplatz + " like ewes2;")){
                            sqlcreatetables.add("create table " + techplatz + " like ewes2;");
                        }
                        // Füge das Array zur Liste hinzu
                        dateiInformationen.add(dateiInfo);
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

            // Ausgabe der Dateiinformationen
            System.out.println("Techplatz und Anlage für jede Datei:");
            for (String[] info : dateiInformationen) {
                System.out.println("Techplatz: " + info[0] + ", Anlage: " + info[1]);
            }
            System.out.println("SQL-Imports:");
            for (int i = 0 ; i < sqlImports.size(); i++){
                System.out.println(sqlImports.get(i));
            }
        } else {
            System.out.println("Das Verzeichnis existiert nicht oder ist kein Verzeichnis.");
        }
        //schreibeSqlImportsInDatei(sqlImports, "sql_imports.txt");
        //schreibeSqlImportsInDatei(sqlcreatetables, "sqlcreatetables.txt");
        //schreibeSqlImportsInDatei(dropModZwei, "dropModZwei.txt");
    }
    @SuppressWarnings("unused")
    private static void schreibeSqlImportsInDatei(List<String> sqlImports, String dateiName) {
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

