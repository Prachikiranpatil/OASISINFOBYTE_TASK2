import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int attemptsLeft = 5; // Number of attempts allowed

    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Generate a random number between 1 and 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // Components initialization
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea(5, 10);
        resultArea.setEditable(false); // Make it read-only

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your guess: "));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        if (attemptsLeft > 0) {
            try {
                int guess = Integer.parseInt(guessField.getText().trim());

                if (guess == randomNumber) {
                    resultArea.append("Congratulations! You guessed the number.\n");
                    guessButton.setEnabled(false); // Disable the button after correct guess
                } else if (guess < randomNumber) {
                    resultArea.append("Too low. Try again.\n");
                } else {
                    resultArea.append("Too high. Try again.\n");
                }

                attemptsLeft--;
                resultArea.append("Attempts left: " + attemptsLeft + "\n");

                if (attemptsLeft == 0 && guess != randomNumber) {
                    resultArea.append("Sorry, you've run out of attempts. The number was " + randomNumber + ".\n");
                    guessButton.setEnabled(false); // Disable the button after running out of attempts
                }

                guessField.setText(""); // Clear the input field
                guessField.requestFocus(); // Focus back on the input field
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
