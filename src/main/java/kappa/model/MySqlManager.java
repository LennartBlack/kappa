package kappa.model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

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


    /**
     * Builds a SQL-Query for the given calbelId
     * @param calbelId
     * @return
     */
    public static String buildQuery(String calbelId){
        String sqlQuery = "SELECT date, ampere FROM ewes WHERE techplatz = 'BA57' LIMIT 30000;" ;
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
            System.out.println("Kritische Fehler: SQL-Syntax-Fehler. Bitte kontaktieren Sie den Anwendungsadministrator.");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Kritische Fehler: SQL-Fehler. Bitte kontaktieren Sie den Anwendungsadministrator.");
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
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
