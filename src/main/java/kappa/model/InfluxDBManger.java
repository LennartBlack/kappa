package kappa.model;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class InfluxDBManger {

    // Konfigurieren Sie die InfluxDB-Verbindung
    // All Access token 0B7facf9mWDzpnzrhW1pwbrV5OFaCJzZOIJWBXQaYBcHZxgBzrytQynIYpP-CDyn9NzyJkVgRkjzFEcTllTvtw==
    //private static final String INFLUXDB_URL = "https://mars.ewenet.ewe.de/influxdb/";
    private static final String INFLUXDB_URL = "http://localhost:8086/";
    private static final String USERNAME = "leschwar";
    private static final String PASSWORD = "J0n4Kape!!e";
    private static final String DATABASE = "ewes";

    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect(INFLUXDB_URL, USERNAME, PASSWORD);
        if(influxDB == null){
            System.out.println("InfluxDB connection failed");
        }
        else{
            System.out.println("InfluxDB connection successful");
        }
    }
}
