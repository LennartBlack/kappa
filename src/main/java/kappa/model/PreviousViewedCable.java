package kappa.model;

import java.util.ArrayList;

public class PreviousViewedCable extends ArrayList<Cable> {

    // Attributes
    public static final long serialVersionUID = 1L;
    private int MAX_AMOUNT_OF_CABLES = 5;

    /**
     * Constructor for the PreviousViewedCable class
     */
    public PreviousViewedCable() {
        super();
    }

    // Methods
    /**
     * Method to handle the click on a cable
     * @param cable
     */
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

    /**
     * Method to check if a cable is in the previous viewed cables
     * @param cable
     * @return
     */
    public boolean checkIfCableIsInPreviousViewedCables(Cable cable) {
        boolean isCableInPreviousViewedCables = false;
        for (Cable element : this) {
            if (element.getId().equals(cable.getId())) {
                isCableInPreviousViewedCables = true;
            }
        }
        return isCableInPreviousViewedCables;
    }

    /**
     *  Method to check if the previous viewed cables are full
     * @return
     */
    public boolean isPreviousViewedCablesFull() {
        boolean isFull = this.size() >= MAX_AMOUNT_OF_CABLES;
        return isFull;
    }

    /**
     * Method to remove a cable from the previous viewed cables
     * @param cable
     */
    public void removeCableFromPreviousViewedCables(Cable cable) {
        this.remove(cable);
    }

    /**
     * Method to put a cable on top of the list
     * @param oldIndex
     * @param cable
     */
    public void putCableOnTopOfList(int oldIndex, Cable cable) {
        this.addFirst(cable);
        this.remove(oldIndex + 1);
    }

    /**
     * Method to add a cable to the first index
     * @param cable
     */
    public void addFirst(Cable cable) {
        this.add(0, cable);
    }

    /**
     * Method to remove the last cable
     * @return
     */
    public Cable removeLast() {
        Cable cable = this.get(this.size() - 1);
        this.remove(this.size() - 1);
        return cable;
    }

    // Getters
    /**
     * Method to get the index of a cable
     * @param cable
     * @return
     */
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

    /**
     * Method to check if a cable is the latest item
     * @param cable
     * @return
     */
    public boolean isCableLatestItem(Cable cable) {
        return this.get(0).getId().equals(cable.getId());
    }
}
