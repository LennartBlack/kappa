package kappa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Watchlist extends HashMap<String, WatchlistElement> implements Serializable {

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
            watchlist.forEach((key, value) -> {
                try {
                    oos.writeObject(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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
            for (String key = (String) ois.readObject(); key != null; key = (String) ois.readObject()) {
                watchlist.addCable(key);
            }
            return watchlist;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void serializeHashMap(Watchlist watchlist) {
        try (FileOutputStream fileOut = new FileOutputStream("watchlist.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(watchlist);
        } catch (IOException e) {
        }
    }

    // Methode zum Auslesen der HashMap aus einer .ser-Datei
    public static Watchlist deserializeHashMap(CableCoreDataDB cableCoreDataDB) {
        Watchlist watchlist = new Watchlist(cableCoreDataDB);
        File file = new File("watchlist.ser");
        if (!file.exists()) {
            return watchlist;
        } else if (file.length() == 0) {
            return watchlist;
        }
        try (FileInputStream fileIn = new FileInputStream("watchlist.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            watchlist = (Watchlist) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return watchlist;
    }

    public WatchlistElement getWatchlistElement(String cableId) {
        return this.get(cableId);
    }
}
