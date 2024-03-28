package kappa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Watchlist extends HashMap<String, WatchlistElement> {

    // Attributes
    private static final long serialVersionUID = 1L;
    private CableCoreDataDB cableCoreDataDB;

    // Constructor
    private Watchlist(CableCoreDataDB cableCoreDataDB) {
        super();
        this.cableCoreDataDB = cableCoreDataDB;
    }

    // Add Cable to Watchlist
    public void addCable(Cable cable) {
        this.put(cable.getId(), new WatchlistElement(cable));
    }

    public void addCable(String cableId) {
        this.put(cableId, new WatchlistElement(cableCoreDataDB.get(cableId)));
    }

    // Remove Cable from Watchlist
    public void removeCable(Cable cable) {
        this.remove(cable.getId());
    }

    public void removeCable(String cableId) {
        this.remove(cableId);
    }

    // Methode zum Speichern der Watchlist in einer Datei
    public static void saveToFile(Watchlist watchlist) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("watchlist.ser"))) {
            for (String cableId : watchlist) {
                oos.writeObject(cableId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Laden der Watchlist aus einer Datei
    public static Watchlist loadFromFile(CableCoreDataDB cableCoreDataDB) {
        Watchlist watchlist = new Watchlist(cableCoreDataDB);
        File file = new File("watchlist.ser");
        if (!file.exists()) {

        } else if (file.length() == 0) {
            return watchlist;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("watchlist.ser"))) {
            watchlist = (Watchlist) ois.readObject();
            return watchlist;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
