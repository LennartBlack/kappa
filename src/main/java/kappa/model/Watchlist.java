package kappa.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Watchlist extends ArrayList<Cable> {

    private static final long serialVersionUID = 1L;
    
    public Watchlist() {
        super();
    }

    public void addCable(Cable cable) {
        this.add(cable);
    }

    public void removeCable(Cable cable) {
        this.remove(cable);
    }

    public void sort() {
        Collections.sort(this, new Comparator<Cable>() {
            @Override
            public int compare(Cable cable1, Cable cable2) {
                return cable1.getId().compareTo(cable2.getId());
            }
        });
    }

    // Methode zum Speichern der Watchlist in einer Datei
    public static void saveToFile(Watchlist watchlist) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("watchlist.ser"))) {
            for (Cable cable : watchlist) {
                oos.writeObject(cable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Laden der Watchlist aus einer Datei
    public static Watchlist loadFromFile() {
        Watchlist watchlist = new Watchlist();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("watchlist.ser"))) {
            watchlist = (Watchlist) ois.readObject();
            return watchlist;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}