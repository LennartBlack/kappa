package kappa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MySqlManager {

    // Attributes
    private static final String URL = "jdbc:mysql://localhost:3306/kappa";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String START_DATE_OF_RECORDING_STR = "2023-01-01 00:00:00";
    private static final String END_DATE_OF_RECORDING_STR = "2023-12-31 23:45:00";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime END_DATE_OF_RECORDING_LDT = LocalDateTime.parse(END_DATE_OF_RECORDING_STR, formatter);

    // Constructor
    private MySqlManager() {
    }

    // Methods
    public static String buildQuery(String calbelId){
        return "SELECT date, ampere FROM " + calbelId + ";";
    }
    public static String buildQueryStartOfRecords(String calbelId){
        return "SELECT date FROM " + calbelId + " ORDER BY date ASC LIMIT 1;";
    }
    public static String buildQueryEndOfRecords(String calbelId){
        return "SELECT date FROM " + calbelId + " ORDER BY date DESC LIMIT 1;";
    }
    public static String buildQueryBetweenDates(String calbelId, String startDate, String endDate){
        return "SELECT date, ampere FROM " + calbelId + " WHERE date > '" + startDate + "' AND date < '" + endDate + "';";
    }
    public static String buildQueryLastFiveDays(String calbelId){
        return "SELECT date, ampere FROM " + calbelId + 
        " WHERE date > '" + END_DATE_OF_RECORDING_LDT.minusDays(5).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryLastTenDays(String calbelId){
        return "SELECT date, ampere FROM " + calbelId +
        " WHERE date > '" + END_DATE_OF_RECORDING_LDT.minusDays(10).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryLastThreeMonths(String calbelId){
        return "SELECT date, ampere " +
        "FROM " + calbelId +
        " WHERE date > '" + END_DATE_OF_RECORDING_LDT.minusMonths(3).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryLastSixMonths(String calbelId){
        return "SELECT date, ampere FROM " + calbelId + 
        " WHERE date > '" + END_DATE_OF_RECORDING_LDT.minusMonths(6).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryLastNineMonths(String calbelId){
        return "SELECT date, ampere " +
        "FROM " + calbelId +
        " WHERE date > '" + END_DATE_OF_RECORDING_LDT.minusMonths(9).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryLastTwelveMonths(String calbelId){
        return "SELECT date, ampere FROM " + calbelId +
        " WHERE date > '" + START_DATE_OF_RECORDING_STR + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
    }
    public static String buildQueryFullRecordTime(String calbelId){
        return "SELECT date, ampere FROM " + calbelId + " WHERE date >= '" + START_DATE_OF_RECORDING_STR + "' AND date <='" + END_DATE_OF_RECORDING_STR + "';" ;
    }

    /** Executes a SQL-Query with a PreparedStatement
     *
     * @param sqlQuery Muss eine korrekte SQL-Query in Form eines String enthalten
     * @return In case the query succeeded method returns a ResulSet. In case the query failed return is null.
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sqlQuery){
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery);
            return preparedStatement.executeQuery();
        }
        catch (SQLSyntaxErrorException sqlSyntaxErrorException){
           sqlSyntaxErrorException.printStackTrace();
            System.out.println("SQL-Syntax Fehler: Bitte kontaktieren Sie den Anwendungsadministrator.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Datenbank Fehler: Bitte kontaktieren Sie den Anwendungsadministrator.");
        }
        return null;   
    }
    public static ResultSet executeQuery(String sqlQuery, Connection connection){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            return preparedStatement.executeQuery();
        }
        catch (SQLSyntaxErrorException sqlSyntaxErrorException){
           sqlSyntaxErrorException.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;   
    }

    /** Creats a connection to MySQL via JDBC
     *
     * @return the connection
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
