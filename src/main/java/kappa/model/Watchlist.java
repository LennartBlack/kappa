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
    private static String fileName = "watchlist.ser";

    /**
     *  Constructor for the Watchlist class
     * @param cableCoreDataDB
     */
    private Watchlist(CableCoreDataDB cableCoreDataDB) {
        super();
        this.cableCoreDataDB = cableCoreDataDB;
    }

    // Methods
    /**
     * Method to add a cable to the watchlist
     * @param cable
     */
    public void addCable(Cable cable) {
        this.put(cable.getId(), new WatchlistElement(cable));
    }

    /**
     * Method to add a cable to the watchlist
     * @param cableId
     */
    public void addCable(String cableId) {
        this.put(cableId, new WatchlistElement(cableCoreDataDB.get(cableId)));
    }

    /**
     * Method to remove a cable from the watchlist
     * @param cable
     */
    public void removeCable(Cable cable) {
        this.remove(cable.getId());
    }

    /**
     * Method to remove a cable from the watchlist
     * @param cableId
     */
    public void removeCable(String cableId) {
        this.remove(cableId);
    }

    /**
     * Method to check if the watchlist contains a cable
     * @param cable
     * @return
     */
    public boolean containsCable(Cable cable) {
        return this.containsKey(cable.getId());
    }

    /**
     * Serialize the watchlist to a file
     * 
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
     * 
     * @param cableCoreDataDB
     * @return watchlist with all its elements or an empty watchlist if the file
     *         does not exist or is empty
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
    /**
     * Getter for the watchlist element
     * @param cableId
     * @return
     */
    public WatchlistElement getWatchlistElement(String cableId) {
        return this.get(cableId);
    }
}
