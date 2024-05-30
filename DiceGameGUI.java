import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class DiceGameGUI extends JFrame implements ActionListener {
    // Declare GUI components and variables
    private JButton rollButton;
    private JButton guessThreeOrBelowButton;
    private JButton guessFourOrAboveButton;
    private JLabel resultLabel;
    private JLabel guessLabel;
    private JLabel scoreLabel;
    private int userScore = 0;
    private int computerScore = 0;
    private Random random;

    // Constructor to set up the GUI
    public DiceGameGUI() {
        // Set the title of the window
        setTitle("Dice Game");
        // Set the size of the window
        setSize(1920, 1080);
        // Close the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Allow the window to be resizable
        setResizable(true);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        // Set the layout of the panel to a grid with 5 rows and 1 column
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        // Add a border with empty space around the panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the roll button and add it to the panel
        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(this);
        panel.add(rollButton);

        // Create the "Guess 3 or Below" button and add it to the panel
        guessThreeOrBelowButton = new JButton("Guess 3 or Below");
        guessThreeOrBelowButton.addActionListener(this);
        panel.add(guessThreeOrBelowButton);

        // Create the "Guess 4 or Above" button and add it to the panel
        guessFourOrAboveButton = new JButton("Guess 4 or Above");
        guessFourOrAboveButton.addActionListener(this);
        panel.add(guessFourOrAboveButton);

        // Create the result label and add it to the panel
        resultLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(resultLabel);

        // Create the guess label and add it to the panel
        guessLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(guessLabel);

        // Create the score label and add it to the panel
        scoreLabel = new JLabel("Computer: 0, User: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(scoreLabel);

        // Add the panel to the frame
        add(panel);
        // Initialize the random number generator
        random = new Random();
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button was clicked
        if (e.getSource() == rollButton) {
            // Roll the dice (get a random number between 1 and 6)
            int diceRoll = random.nextInt(6) + 1;
            // Display the result of the dice roll
            resultLabel.setText("You rolled: " + diceRoll);
            // Check if the user's guess was correct
            checkGuess(diceRoll);
        } else if (e.getSource() == guessThreeOrBelowButton) {
            // Set the guess label to "3 or Below" when the corresponding button is clicked
            guessLabel.setText("3 or Below");
        } else if (e.getSource() == guessFourOrAboveButton) {
            // Set the guess label to "4 or Above" when the corresponding button is clicked
            guessLabel.setText("4 or Above");
        }
    }

    // Check if the user's guess matches the dice roll
    private void checkGuess(int diceRoll) {
        String guess = guessLabel.getText();
        boolean correctGuess = (guess.equals("3 or Below") && diceRoll <= 3) || (guess.equals("4 or Above") && diceRoll >= 4);

        // Update the result label and the scores based on the guess
        if (correctGuess) {
            resultLabel.setText("Congratulations! You guessed right! The number rolled was: " + diceRoll + ". You win!");
            userScore++;
        } else {
            resultLabel.setText("Sorry, you guessed wrong. The number rolled was: " + diceRoll);
            computerScore++;
        }
        // Update the scoreboard
        updateScoreboard();
    }

    // Update the scoreboard with the current scores
    private void updateScoreboard() {
        scoreLabel.setText("Computer: " + computerScore + ", User: " + userScore);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DiceGameGUI game = new DiceGameGUI();
            game.setVisible(true);
        });
    }
}
