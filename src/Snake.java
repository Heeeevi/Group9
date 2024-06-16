public class Snake {
    private int headPosition;
    private int tailPosition;

    public Snake(int from, int to) {
        this.headPosition = from;
        this.tailPosition = to;
    }

    public int getHeadPosition() {
        return this.headPosition;
    }

    public int getTailPosition() {
        return this.tailPosition;
    }
}
