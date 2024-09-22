import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame 
{
    private JButton[][] buttons;
    private boolean xTurn = true; // X starts the game

    public TicTacToe()
	{
        buttons = new JButton[3][3];
        setTitle("Tic-Tac-Toe");
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeButtons()
	{
        ActionListener buttonListener = new ButtonClickListener();
        for (int row = 0; row < 3; row++) 
		{
            for (int col = 0; col < 3; col++)
			{
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(buttonListener);
                add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener 
	{
        @Override
        public void actionPerformed(ActionEvent e)
		{
            JButton button = (JButton) e.getSource();
            if (button.getText().equals(""))
			{
                if (xTurn)
				{
                    button.setText("X");
                }
				else 
				{
                    button.setText("O");
                }
                xTurn = !xTurn;
                checkForWin();
            }
        }
    }

    private void checkForWin()
	{
        String winner = null;
        // Check rows and columns
        for (int i = 0; i < 3; i++) 
		{
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().equals(""))
			{
                winner = buttons[i][0].getText();
                break;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().equals(""))
			{
                winner = buttons[0][i].getText();
                break;
            }
        }

        // Check diagonals
        if (winner == null)
		{
            if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) 
			{
                winner = buttons[0][0].getText();
            }
			else if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                       buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) 
		      {
                winner = buttons[0][2].getText();
              }
        }

        if (winner != null) 
		{
            JOptionPane.showMessageDialog(this, winner + " Wins!");
            resetBoard();
        }
		 else if (isBoardFull()) 
		{
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetBoard();
        }
    }

    private boolean isBoardFull() 
	{
        for (int row = 0; row < 3; row++) 
		{
            for (int col = 0; col < 3; col++)
			{
                if (buttons[row][col].getText().equals(""))
				{
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard()
	{
        for (int row = 0; row < 3; row++) 
		{
            for (int col = 0; col < 3; col++)
			{
                buttons[row][col].setText("");
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}

