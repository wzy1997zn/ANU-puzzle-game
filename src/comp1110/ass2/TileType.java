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

    /**
     * Coded by Ziyue Wang
     * states of all tiles in 0 orientation
     * maybe useful
     */
    public static final State[][][] originStates = {
            {//a
                {State.G, State.W, State.R},
                {null,    State.R, null   }
            },
            {//b
                {null,    State.B, State.G, State.G},
                {State.W, State.W, null,    null   }
            },
            {//c
                {null,    null,    State.G, null   },
                {State.R, State.R, State.W, State.B}
            },
            {//d
                {State.R, State.R, State.R},
                {null,    null,    State.B}
            },
            {//e
                {State.B, State.B, State.B},
                {State.R, State.R, null   }
            },
            {//f
                {State.W, State.W, State.W}
            },
            {//g
                {State.W, State.B, null   },
                {null,    State.B, State.W}
            },
            {//h
                {State.R, State.G, State.G},
                {State.W, null,    null   },
                {State.W, null,    null   }
            },
            {//i
                {State.B, State.B},
                {null,    State.W}
            },
            {//j
                {State.G, State.G, State.W, State.R},
                {State.G, null,    null,    null,  }
            }
    };

    /**
     * Coded by Ziyue Wang
     * A util for rotate 2DArray 90 clockwise(N->E->S->W)
     * @param matrix 2D array
     * @return 2D array after rotate
     */
    public static State[][] rotate90_2DStateArray(State[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        State[][] newMatrix = new State[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix[n-j-1][i];
            }
        }

        return newMatrix;
    }
}
