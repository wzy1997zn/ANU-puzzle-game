package comp1110.ass2;

/**
 * An idea from ass1 and coded by Ziyue Wang and modified by Zeming Wang
 * It is a class for good managing the Location of tiles.
 * According to README, a placement will like "f842",the
 * first shows col, the second shows row. So in Location,
 * Y shows col, X shows row.
 */
public class Location {
    /**
     * the row of this location.
     */
    private int X;

    /**
     * the column of this location.
     */
    private int Y;

    /**
     * the constructor
     * @param X row of this location
     * @param Y column of this location
     */
    public Location(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    /**
     * change the row
     * @param x row of this location
     */
    public void setX(int x) {
        X = x;
    }

    /**
     * change the column
     * @param y row of this location
     */
    public void setY(int y) {
        Y = y;
    }

    /**
     * get the row
     * @return  row of this location
     */
    public int getX() {
        return X;
    }

    /**
     * change the column
     * @return column of this location
     */
    public int getY() {
        return Y;
    }

    /**
     * An idea from Ziyue Wang.
     * It should be a method for judging whether 2 Location is the same.
     * FIXME
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
