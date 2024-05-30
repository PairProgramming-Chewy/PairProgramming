import javax.swing.SwingUtilities;

public class MainMethod {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure the GUI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create an instance of DiceGameGUI
            DiceGameGUI game = new DiceGameGUI();
            // Make the GUI visible
            game.setVisible(true);
        });
    }
}
