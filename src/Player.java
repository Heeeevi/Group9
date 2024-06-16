public class Player {
    private String name;
    private int posisi;

    // Konstruktor
    public Player(String x) {
        this.name = x;
        this.posisi = 0;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int posisi) {
        this.posisi = posisi;
    }

    // Getter
    public int getPosisi() {
        return this.posisi;
    }

    public String getName() {
        return this.name;
    }

    // Method
    public int rollDice() {
        int x = (int) (Math.random() * 6) + 1;
        System.out.println(this.getName() + " get " + x + " dice");
        return x;
    }

    public void move(int move, SnakeAndLadder x) {
        if (this.posisi + move > x.getBoardsize()) {
            this.posisi = x.getBoardsize() - ((this.posisi + move) % x.getBoardsize());
        } else {
            this.posisi = this.posisi + move;
        }
    }
}
