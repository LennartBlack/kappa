package kappa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Watchlist extends HashMap<String, WatchlistElement>{

    // Attributes
    private static final long serialVersionUID = 1L;
    private CableCoreDataDB cableCoreDataDB;

    // Constructor
    private Watchlist(CableCoreDataDB cableCoreDataDB) {
        super();
        this.cableCoreDataDB = cableCoreDataDB;
    }

    // Methods
    public void addCable(Cable cable) {
        this.put(cable.getId(), new WatchlistElement(cable));
    }

    public void addCable(String cableId) {
        this.put(cableId, new WatchlistElement(cableCoreDataDB.get(cableId)));
    }

    public void removeCable(Cable cable) {
        this.remove(cable.getId());
    }

    public void removeCable(String cableId) {
        this.remove(cableId);
    }

    /**
     * Serialize the watchlist to a file
     * @param watchlist
     */
    public static void serializeHashMap(Watchlist watchlist) {
        try (FileOutputStream fileOut = new FileOutputStream("watchlist.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(watchlist);
        } catch (IOException e) {
        }
    }

    /**
     * Deserialize the watchlist from a file
     * @param cableCoreDataDB
     * @return
     */
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

    // Getter
    public WatchlistElement getWatchlistElement(String cableId) {
        return this.get(cableId);
    }
}
