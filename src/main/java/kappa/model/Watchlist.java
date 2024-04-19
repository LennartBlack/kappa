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
    private static String fileName = "watchlist.ser";

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

    public boolean containsCable(Cable cable) {
        return this.containsKey(cable.getId());
    }
    
    /**
     * Serialize the watchlist to a file
     * @param watchlist
     */
    public static void serializeHashMap(Watchlist watchlist) {
        try (FileOutputStream fileOut = new FileOutputStream(Watchlist.fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(watchlist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize the watchlist from a file
     * @param cableCoreDataDB
     * @return
     */
    public static Watchlist deserializeHashMap(CableCoreDataDB cableCoreDataDB) {
        Watchlist watchlist = new Watchlist(cableCoreDataDB);
        File file = new File(Watchlist.fileName);
        if (!file.exists() || file.length() == 0) {
            return watchlist;
        }
        try (FileInputStream fileIn = new FileInputStream(Watchlist.fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            watchlist = (Watchlist) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return watchlist;
    }

    // Getter
    public WatchlistElement getWatchlistElement(String cableId) {
        return this.get(cableId);
    }
}
