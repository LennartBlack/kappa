package kappa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PreviousViewedCable extends ArrayList<Cable> implements Serializable{

    public static final long serialVersionUID = 1L;
    private int MAX_AMOUNT_OF_CABLES = 5;

    public PreviousViewedCable() {
        super();
    }

    /**
     * 
     * @param cable
     * @return old index of cable if cable is already in pvc OR -1 if pvc is full OR -2 if cable is not in pvc and pvc is not full
     */
    public int clickedOnCable(Cable cable) {
        System.out.println("ArrayListsize: " + this.size());
        if(checkIfCableIsInPreviousViewedCables(cable)) {
            for(Cable element : this){
                System.out.println(element.getId() + "and index is: " + getIndexOFCable(element));
            }
            System.out.println("clickedOnCable: cable is already in pvc");
            int oldIndex = getIndexOFCable(cable);
            System.out.println("old index is: " + oldIndex);

            putCableOnTopOfList(cable);
            return oldIndex;
            // hier wird null zurückgegeben obwohl der alte index zurückgegeben werden sollte
        } else if(isPreviousViewedCablesFull()) {
            System.out.println("clickedOnCable: pvc is full");
            this.add(cable);
            this.remove(0);
            return -1;
        } else {
            System.out.println("clickedOnCable: cable not in pvc and pvc not full");
            this.add(cable);
            return -2;            
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
        System.out.println("size of arraylist" + this.size() + "therfore its full" + isFull);
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

    private int getIndexOFCable(Cable cable) {
        int i = 0;
        for(Cable element : this){
            i++;
            if(element.getId().equals(cable.getId())){
                System.out.println("getIndexOfcable will return: element id" + element.getId() + "and i should show index" + i);
                return i;
            }
        }
        return -1;
    }
}
