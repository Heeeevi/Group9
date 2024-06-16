import java.util.ArrayList;
import java.util.Scanner;

public class SnakeAndLadder {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardsize;
    private int status;
    private int playerTurn;

    public SnakeAndLadder(int size) {
        this.boardsize = size;
        this.players = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.status = 0;
    }

    // GETTER
    public int getBoardsize() {
        return this.boardsize;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public int getStatus() {
        return this.status;
    }

    public int getPlayerTurn() {
        return this.playerTurn;
    }

    // SETTER
    public void setStatus(int status) {
        this.status = status;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void addPlayer(Player x) {
        this.players.add(x);
    }

    public void addSnake(Snake x) {
        this.snakes.add(x);
    }

    public void addLadder(Ladder x) {
        this.ladders.add(x);
    }

    public void addSnakenLadder() {
        Snake s1 = new Snake(38, 15);
        Snake s2 = new Snake(47, 5);
        Snake s3 = new Snake(53, 33);
        Snake s4 = new Snake(29, 9);
        Snake s5 = new Snake(62, 37);
        Snake s6 = new Snake(86, 54);
        Snake s7 = new Snake(97, 25);
        Snake s8 = new Snake(92, 70);
        Ladder l1 = new Ladder(2, 23);
        Ladder l2 = new Ladder(8, 34);
        Ladder l3 = new Ladder(20, 77);
        Ladder l4 = new Ladder(41, 79);
        Ladder l5 = new Ladder(74, 88);
        Ladder l6 = new Ladder(32, 68);
        Ladder l7 = new Ladder(85, 95);
        Ladder l8 = new Ladder(82, 100);
        this.addSnake(s1);
        this.addSnake(s2);
        this.addSnake(s3);
        this.addSnake(s4);
        this.addSnake(s5);
        this.addSnake(s6);
        this.addSnake(s7);
        this.addSnake(s8);
        this.addLadder(l1);
        this.addLadder(l2);
        this.addLadder(l3);
        this.addLadder(l4);
        this.addLadder(l5);
        this.addLadder(l6);
        this.addLadder(l7);
        this.addLadder(l8);
    }

    public int exceptionBoard(int x) {
        Scanner scan = new Scanner(System.in);
        int[] cantuse = {38, 47, 53, 29, 62, 86, 97, 92, 2, 8, 20, 41, 74, 32, 85, 82};
        for (int i = 0; i < cantuse.length; i++) {
            if (x == cantuse[i]) {
                System.out.println("Sorry you can't choose that number, please insert another number");
                System.out.print("Enter board size : ");
                return scan.nextInt();
            }
        }
        return 0;
    }

    public void jumlahPemain(int x) {
        // Dihandle di MainWindow
    }

    public void endGame(Player p) {
        if (p.getPosisi() == this.getBoardsize()) {
            this.setStatus(2);
            System.out.println("the winner is : " + p.getName());
        }
    }

    public void movePlayer(Player p, int x) {
        System.out.println(p.getName() + " is on " + p.getPosisi());
        p.move(x, this);
        System.out.println("The new position of " + p.getName() + " is " + p.getPosisi());
        for (int i = 0; i < this.getSnakes().size(); i++) {
            Snake snakeInTurn = this.getSnakes().get(i);
            if (p.getPosisi() == snakeInTurn.getHeadPosition()) {
                System.out.println(p.getName() + " hit a snake! Sliding down to " + snakeInTurn.getTailPosition());
                p.setPosition(snakeInTurn.getTailPosition());
            }
        }
        for (int i = 0; i < this.getLadders().size(); i++) {
            Ladder ladderInTurn = this.getLadders().get(i);
            if (p.getPosisi() == ladderInTurn.getFromPosition()) {
                System.out.println(p.getName() + " find a ladder! Climbing up to: " + ladderInTurn.getToPosition());
                p.setPosition(ladderInTurn.getToPosition());
            }
        }
    }
}
