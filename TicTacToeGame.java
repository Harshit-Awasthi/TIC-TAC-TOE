import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private JButton[][] buttonGrid = new JButton[3][3];
    private JLabel statusLabel;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());
        initializeBoard();
        createGrid();
        createStatusLabel();
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void createGrid() {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        add(panel, BorderLayout.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 80));
                button.addActionListener(this);
                buttonGrid[i][j] = button;
                panel.add(button);
            }
        }
    }

    private void createStatusLabel() {
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int row = -1, column = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonGrid[i][j] == button) {
                    row = i;
                    column = j;
                    break;
                }
            }
        }

        if (isValidMove(row, column)) {
            board[row][column] = currentPlayer;
            button.setText("" + currentPlayer);

            if (currentPlayer == 'X') {
                button.setForeground(Color.RED);
            } else {
                button.setForeground(Color.GREEN);
            }

            if (isWinner()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableButtons();
            } else if (isDraw()) {
                statusLabel.setText("Game is a draw!");
                disableButtons();
            } else {
                switchPlayer();
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Please try again.");
        }
    }

    private boolean isValidMove(int row, int column) {
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            return false;
        } else if (board[row][column] != '-') {
            return false;
        } else {
            return true;
        }
    }

    private boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true;
            } else if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true;
        } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonGrid[i][j].setEnabled(false);
            }
        }
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
