package kappa;

import java.sql.ResultSet;
import kappa.model.CableCoreDataDB;
import kappa.model.MySqlManager;
import kappa.model.TopWorkloud;

public class Test {

    public static void main(String[] args) {
        try{
            MySqlManager.getConnection();
            String query = "SELECT date, ampere FROM BA17;";
            ResultSet rs = MySqlManager.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("date") + " " + rs.getString("ampere"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    
    }
}