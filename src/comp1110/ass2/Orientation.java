package comp1110.ass2;

/**
 * An idea from ass1 and coded by Ziyue Wang and modified by Zeming Wang
 * Shows relations between num and orientation.
 */
public enum Orientation {
    NORTH(0),EAST(1),SOUTH(2),WEST(3);

    private int index;

    private Orientation(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
