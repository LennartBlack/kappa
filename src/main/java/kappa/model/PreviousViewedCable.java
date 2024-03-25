package kappa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PreviousViewedCable extends ArrayList<Cable> implements Serializable{

    public static final long serialVersionUID = 1L;
    private int MAX_AMOUNT_OF_CABLES = 10;

    public PreviousViewedCable() {
        super();
    }
    public int clickedOnCable(Cable cable) {
        if(checkIfCableIsInPreviousViewedCables(cable)) {
            return putCableOnTopOfList(cable);
        } else if(isPreviousViewedCablesFull()) {
            this.add(cable);
            return -1;
        } else {
            this.add(cable);
            this.remove(0);
            return -2;            
        }
    }
    public boolean checkIfCableIsInPreviousViewedCables(Cable cable) {
        boolean isCableInPreviousViewedCables = false;
        for (Cable element : this) {
            if (element.equals(cable)) {
                isCableInPreviousViewedCables = true;
            }
        }
        return isCableInPreviousViewedCables;
    }

    public boolean isPreviousViewedCablesFull() {
        return this.size() >= MAX_AMOUNT_OF_CABLES;
    }

    public void removeCableFromPreviousViewedCables(Cable cable) {
        this.remove(cable);
    }

    public int putCableOnTopOfList(Cable cable) {
        int oldIndex = this.indexOf(cable);
        this.add(cable);
        this.remove(oldIndex);
        return oldIndex;
    }
}
