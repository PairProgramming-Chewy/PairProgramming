import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class DiceGameGUI extends JFrame implements ActionListener {
    private JButton rollButton;
    private JButton guessThreeOrBelowButton;
    private JButton guessFourOrAboveButton;
    private JLabel resultLabel;
    private JLabel guessLabel;
    private JLabel scoreLabel;
    private int userScore = 0;
    private int computerScore = 0;
    private Random random;

    public DiceGameGUI() {
        setTitle("Dice Game");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(this);
        panel.add(rollButton);

        guessThreeOrBelowButton = new JButton("Guess 3 or Below");
        guessThreeOrBelowButton.addActionListener(this);
        panel.add(guessThreeOrBelowButton);

        guessFourOrAboveButton = new JButton("Guess 4 or Above");
        guessFourOrAboveButton.addActionListener(this);
        panel.add(guessFourOrAboveButton);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(resultLabel);

        guessLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(guessLabel);

        scoreLabel = new JLabel("Computer: 0, User: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(scoreLabel);

        add(panel);
        random = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            int diceRoll = random.nextInt(6) + 1;
            resultLabel.setText("You rolled: " + diceRoll);
            checkGuess(diceRoll);
        } else if (e.getSource() == guessThreeOrBelowButton) {
            guessLabel.setText("You guessed: 3 or Below");
        } else if (e.getSource() == guessFourOrAboveButton) {
            guessLabel.setText("You guessed: 4 or Above");
        }
    }

    private void checkGuess(int diceRoll) {
        int guessedNumber = Integer.parseInt(guessLabel.getText().replaceAll("\\D+", ""));
        if (diceRoll == guessedNumber) {
            resultLabel.setText("Congratulations! You guessed right! The number rolled was: " + diceRoll + ". You win!");
            userScore++;
        } else {
            resultLabel.setText("Sorry, you guessed wrong. The number rolled was: " + diceRoll);
            computerScore++;
        }
        updateScoreboard();
    }

    private void updateScoreboard() {
        scoreLabel.setText("Computer: " + computerScore + ", User: " + userScore);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DiceGameGUI game = new DiceGameGUI();
            game.setVisible(true);
        });
    }
}
