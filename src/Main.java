import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Menjalankan aplikasi GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
