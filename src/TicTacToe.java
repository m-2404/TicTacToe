import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardheight=550;
    int boardwidth=500;

    JFrame frame=new JFrame("Tic-Tac-Toe");
    JPanel panel=new JPanel();
    JLabel label=new JLabel();
    JPanel board=new JPanel();
    JButton [][] boardButton=new JButton[3][3]; 
    String playerX="X";
    String playerO="O";
    String currentPlayer=playerX;
    int turns=0;

    boolean gameOver=false;


    public void ticTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        label.setBackground(Color.black);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial",Font.BOLD,40));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("TIC-TAC-TOE");
        label.setOpaque(true);


        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel,BorderLayout.NORTH);

        board.setLayout(new GridLayout(3,3));
        board.setBackground(Color.black);
        frame.add(board);

        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                JButton tile=new JButton();
                boardButton[r][c]=tile;
                board.add(tile);

                tile.setBackground(Color.black);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);
                tile.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e)
                   {
                        if(gameOver) return;
                        JButton tile=(JButton)e.getSource();
                        if(tile.getText()=="")
                        {
                            
                            tile.setText(currentPlayer);
                            turns++;
                            checkwinner();
                            if(!gameOver)
                            {
                                currentPlayer=currentPlayer==playerX?playerO:playerX;
                                label.setText(currentPlayer+"'s turn");
                            }
                            
                        }
                    }
                });




            }
        }


    }
    void checkwinner()
    {
        //horizontal
        for(int r=0;r<3;r++)
        {
            if(boardButton[r][0].getText()=="") continue;
            if(boardButton[r][0].getText()==boardButton[r][1].getText() && boardButton[r][1].getText()==boardButton[r][2].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(boardButton[r][i]);
                }
                gameOver=true;
                return;
            }
        }

        //vertical
        for(int c=0;c<3;c++)
        {
            if(boardButton[0][c].getText()=="") continue;
            if(boardButton[0][c].getText()==boardButton[1][c].getText() && boardButton[1][c].getText()==boardButton[2][c].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(boardButton[i][c]);
                }
                gameOver=true;
                return;
            }
        }

        //diagonal
        if(boardButton[0][0].getText()==boardButton[1][1].getText() && boardButton[1][1].getText()==boardButton[2][2].getText() && boardButton[0][0].getText()!="")
        {
            for(int i=0;i<3;i++)
            {
                setWinner(boardButton[i][i]);
            }
            gameOver=true;
            return;
        }

        //antiDiagonal

        if(boardButton[0][2].getText()==boardButton[1][1].getText() && boardButton[1][1].getText()==boardButton[2][0].getText() && boardButton[0][2].getText()!="")
        {
            setWinner(boardButton[0][2]);
            setWinner(boardButton[1][1]);
            setWinner(boardButton[2][0]);

            gameOver=true;
            return;
        }

        //tie
        if(turns==9)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    setTie(boardButton[i][j]);
                }
            }
            gameOver=true;
            return;
        }




    }

    void setWinner(JButton tile)
    {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        label.setText(currentPlayer+" is winner");

    }
    void setTie(JButton tile)
    {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.red);
        label.setText("Its a Tie");
    }
}
