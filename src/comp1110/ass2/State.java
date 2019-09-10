package comp1110.ass2;

public enum State {
    R('R'), // RED
    G('G'), // GREEN
    B('B'), // BLUE
    W('W'); // WHITE

    private char c;

    private State(char c){
        this.c = c;
    }

    public static State getState(char c){
        switch (c){
            case 'R': return State.R;
            case 'G': return State.G;
            case 'B': return State.B;
            case 'W': return State.W;
        }
        return null;
    }
}
