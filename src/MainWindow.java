import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow {
    private SnakeAndLadder game;
    private JLabel statusLabel;
    private JButton rollDiceButton;
    private int currentPlayerIndex = 0;
    private JPanel boardPanel;
    private ArrayList<JLabel> playerLabels;

    public MainWindow() {
        // Inisialisasi game
        int boardSize = getBoardSizeFromUser();
        game = new SnakeAndLadder(boardSize);
        game.exceptionBoard(boardSize);
        game.addSnakenLadder();

        int numberOfPlayers = getNumberOfPlayersFromUser();
        game.jumlahPemain(numberOfPlayers);

        // Membuat frame (jendela) utama
        JFrame frame = new JFrame("Snake and Ladder");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat label status
        statusLabel = new JLabel("Welcome to Snake and Ladder Game", JLabel.CENTER);
        panel.add(statusLabel, BorderLayout.NORTH);

        // Membuat tombol untuk melempar dadu
        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        panel.add(rollDiceButton, BorderLayout.SOUTH);

        // Membuat panel papan permainan
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10));
        boardPanel.setPreferredSize(new Dimension(600, 600));
        setupBoard();

        // Menambahkan panel papan ke panel utama
        panel.add(boardPanel, BorderLayout.CENTER);

        // Menambahkan panel ke frame
        frame.add(panel);

        // Menampilkan frame
        frame.setVisible(true);
    }

    private void setupBoard() {
        playerLabels = new ArrayList<>();
        for (int i = 0; i < game.getBoardsize(); i++) {
            JLabel label = new JLabel();
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            boardPanel.add(label);
            playerLabels.add(label);
        }
    }

    private void updateBoard() {
        for (int i = 0; i < playerLabels.size(); i++) {
            playerLabels.get(i).setText("");
        }
        for (Player player : game.getPlayers()) {
            playerLabels.get(player.getPosisi()).setText(player.getName());
        }
    }

    private void rollDice() {
        Player currentPlayer = game.getPlayers().get(currentPlayerIndex);
        int diceRoll = currentPlayer.rollDice();
        game.movePlayer(currentPlayer, diceRoll);
        game.endGame(currentPlayer);

        statusLabel.setText(currentPlayer.getName() + " rolled a " + diceRoll + " and is now on position " + currentPlayer.getPosisi());
        updateBoard();

        // Ganti ke pemain berikutnya
        currentPlayerIndex++;
        if (currentPlayerIndex >= game.getPlayers().size()) {
            currentPlayerIndex = 0;
        }

        // Nonaktifkan tombol jika game sudah selesai
        if (game.getStatus() == 2) {
            rollDiceButton.setEnabled(false);
            statusLabel.setText("The winner is " + currentPlayer.getName() + "!");
        }
    }

    private int getBoardSizeFromUser() {
        String input = JOptionPane.showInputDialog("Enter board size:");
        return Integer.parseInt(input);
    }

    private int getNumberOfPlayersFromUser() {
        String input = JOptionPane.showInputDialog("Enter number of players:");
        int numberOfPlayers = Integer.parseInt(input);

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = JOptionPane.showInputDialog("Enter name for Player " + (i + 1) + ":");
            Player player = new Player(playerName);
            game.addPlayer(player);
        }

        return numberOfPlayers;
    }
}
