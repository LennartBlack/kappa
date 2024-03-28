package kappa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PreviousViewedCable extends ArrayList<Cable> implements Serializable {

    public static final long serialVersionUID = 1L;
    private int MAX_AMOUNT_OF_CABLES = 5;

    public PreviousViewedCable() {
        super();
    }

    public void clickedOnACable(Cable cable) {
        if (checkIfCableIsInPreviousViewedCables(cable)) {
            if (isCableLatestItem(cable)) {
                // do nothing
            } else {
                // putCableOnTopOfList(cable);
            }
        } else {
            if (isPreviousViewedCablesFull()) {
                this.addFirst(cable);
                this.removeLast();
            } else {
                this.addFirst(cable);
            }
        }
    }

    public boolean checkIfCableIsInPreviousViewedCables(Cable cable) {
        boolean isCableInPreviousViewedCables = false;
        for (Cable element : this) {
            if (element.getId().equals(cable.getId())) {
                isCableInPreviousViewedCables = true;
            }
        }
        return isCableInPreviousViewedCables;
    }

    public boolean isPreviousViewedCablesFull() {
        boolean isFull = this.size() >= MAX_AMOUNT_OF_CABLES;
        return isFull;
    }

    public void removeCableFromPreviousViewedCables(Cable cable) {
        this.remove(cable);
    }

    public void putCableOnTopOfList(int oldIndex, Cable cable) {
        this.addFirst(cable);
        this.remove(oldIndex + 1);
    }

    public int getIndexOFCable(Cable cable) {
        int i = 0;
        for (Cable element : this) {
            if (element.getId().equals(cable.getId())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean isCableLatestItem(Cable cable) {
        return this.get(0).getId().equals(cable.getId());
    }
}
