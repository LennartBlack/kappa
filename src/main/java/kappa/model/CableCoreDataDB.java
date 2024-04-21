package kappa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CableCoreDataDB extends HashMap<String, Cable> {

    // Constructor
    public CableCoreDataDB(){
        super();
        try {
            Connection connection = MySqlManager.getConnection();
            String query = "SELECT * FROM coredata;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String start = resultSet.getString("start");
                String end = resultSet.getString("end");
                Double resistance = resultSet.getDouble("resistance");
                Double reactance = resultSet.getDouble("reactance");
                Double ampacity = resultSet.getDouble("ampacity");
                Double length = resultSet.getDouble("length");
                int yearOfConstruction = resultSet.getInt("yearOfConstruction");
                int crossSection = resultSet.getInt("crossSection");

                Cable cable = new Cable(name, start, end, resistance, reactance, ampacity, length, yearOfConstruction, crossSection);
                this.put(cable.getId(), cable);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
