package comp1110.ass2;

public class StrictSymmetry {

        //public enum TileType {
        // A,B,C,D,E,F,G,H,I,J;
        //public enum Orientation {
        //NORTH(0),EAST(1),SOUTH(2),WEST(3);

    public static void main(String[] args) {
        //F2->F0
        //F3->F1
        //G2->G0
        //G3->G1

        //fxy2, fxy3, gxy2, gxy3 are ignored.
    }
}

//According to the pieces given, we found that there are some particular pieces are different from others, as when
//they rotated twice, the direction of piece we got is the same as the original one. In this case, pieces which have
//this property are piece f and piece g(The reason for that is they are symmetric). Therefore, we can ignore the
//redundant rotations with higher numbering, as fxy2, fxy3, gxy2 and gxy3.

