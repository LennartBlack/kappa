package kappa.model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MySqlManager {

    /*
    private static final String INFLUXDB_URL = "http://localhost:8086/";
    private static final String TOKEN = "tst5d2GxzhoBD28R0SizSJApa60NlFkq4pCbWw7_r2wI_y-t6hk2ygIT_KYEeuxCqcy97IEJO5jfYwj3G2CSLg==";
    private static final String USERNAME = "leschwar";
    private static final String PASSWORD = "J0n4Kape!!e";
    private static final String DATABASE = "bucket";
    */

    private static final String URL = "jdbc:mysql://localhost:3306/mars";
    private static final String USER = "user_2";
    private static final String PASSWORD = "1234";
    private static final String START_DATE_OF_RECORDING_STR = "2023-01-01 00:00:00";
    private static final String END_DATE_OF_RECORDING_STR = "2023-12-31 23:45:00";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime START_DATE_OF_RECORDING_LDT = LocalDateTime.parse(START_DATE_OF_RECORDING_STR, formatter);
    private static final LocalDateTime END_DATE_OF_RECORDING_LDT = LocalDateTime.parse(END_DATE_OF_RECORDING_STR, formatter);

    /**
     * Builds a SQL-Query for the given calbelId
     * @param calbelId
     * @return
     */
    public static String buildQuery(String calbelId){
        String sqlQuery = "SELECT date, ampere FROM ewes WHERE techplatz = '" + calbelId + "' LIMIT 30000;" ;
        return sqlQuery;
    }

    public static String buildQueryBetweenGivenDates(String calbelId, String startDate, String endDate){
        String sqlQuery = "SELECT date, ampere FROM ewes WHERE techplatz = '" + calbelId + "' AND date BETWEEN '" + startDate + "' AND '" + endDate + "';" ;
        return sqlQuery;
    }
    public static String buildQueryLastTenDays(String calbelId){
        String sqlQuery = "SELECT date, ampere " +
        "FROM ewes " + 
        "WHERE techplatz = '" + calbelId +
        "' AND date > '" + END_DATE_OF_RECORDING_LDT.minusDays(10).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
        return sqlQuery;
    }
    public static String buildQueryLastThreeMonths(String calbelId){
        String sqlQuery = "SELECT date, ampere " +
        "FROM ewes " + 
        "WHERE techplatz = '" + calbelId +
        "' AND date < '" + END_DATE_OF_RECORDING_LDT.minusMonths(3).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
        return sqlQuery;
    }
    public static String buildQueryLastSixMonths(String calbelId){
        String sqlQuery = "SELECT date, ampere " +
        "FROM ewes " + 
        "WHERE techplatz = '" + calbelId +
        "' AND date < '" + END_DATE_OF_RECORDING_LDT.minusMonths(6).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
        return sqlQuery;
    }
    public static String buildQueryLastNineMonths(String calbelId){
        String sqlQuery = "SELECT date, ampere " +
        "FROM ewes " + 
        "WHERE techplatz = '" + calbelId +
        "' AND date < '" + END_DATE_OF_RECORDING_LDT.minusMonths(9).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
        return sqlQuery;
    }
    public static String buildQueryLastTwelveMonths(String calbelId){
        String sqlQuery = "SELECT date, ampere " +
        "FROM ewes " + 
        "WHERE techplatz = '" + calbelId +
        "' AND date < '" + END_DATE_OF_RECORDING_LDT.minusMonths(12).toString() + "' " + 
        "AND date < '" + END_DATE_OF_RECORDING_STR + "';";
        return sqlQuery;
    }

    public static String buildQueryFullRecordTime(String calbelId){
        String sqlQuery = "SELECT date, ampere FROM ewes WHERE techplatz = '" + calbelId + "' AND date BETWEEN '" + START_DATE_OF_RECORDING_STR + "' AND '" + END_DATE_OF_RECORDING_STR + "';" ;
        return sqlQuery;
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
