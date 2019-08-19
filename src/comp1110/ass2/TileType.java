package comp1110.ass2;

/**
 * An idea from ass1 and coded by Ziyue Wang and modified by Zeming Wang
 * I would like move the "stateFromOffset" function into Tile
 * because of the irregular tile.
 */
public enum TileType {
    A(1),B(2),C(3),D(4),E(5),F(6),G(7),H(8),I(9),J(10);

    private int index;

    private TileType(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
