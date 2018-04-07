package com.marcelbruckner.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[][] board = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
    //0 = yellow, 1 = red
    private int activePlayer = 0;
    private boolean isActive = true;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when a player clicks on an empty position.
     */
    public void dropIn(View view) {
        if (!isActive) {
            return;
        }

        ImageView counter = (ImageView) view;

        int x = Integer.parseInt(String.valueOf(counter.getTag().toString().charAt(0)));
        int y = Integer.parseInt(String.valueOf(counter.getTag().toString().charAt(1)));

        if (board[x][y] != 2) {
            return;
        }

        board[x][y] = activePlayer;

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
        } else {
            counter.setImageResource(R.drawable.red);
        }
        activePlayer = (activePlayer + 1) % 2;
        
        counter.setTranslationY(-1000f);
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

        int winner = checkWinner();
        processWin(winner);
    }

    /**
     * Checks if a player has won.
     *
     * @return the number of the player that has won. 2 if none has won.
     */
    private int checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 2 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] != 2 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        if (board[0][0] != 2 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != 2 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return 2;
    }

    /**
     * Processes if a winner is found and shows ui.
     */
    public void processWin(int winner) {
        if (winner == 2) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 2) {
                        return;
                    }
                }
            }
        }

        RelativeLayout ui = findViewById(R.id.ui);
        ui.setVisibility(View.VISIBLE);

        TextView message = findViewById(R.id.winMessage);
        String messageText;
        if (winner == 0) {
            messageText = "Player Yellow won!";
        } else if (winner == 1) {
            messageText = "Player Red won!";
        } else {
            messageText = "Draw!";
        }

        message.setText(messageText);
        isActive = false;
        activePlayer = winner;
    }

    /**
     * Resets the game.
     */
    public void restart(View view) {
        RelativeLayout ui = findViewById(R.id.ui);
        ui.setVisibility(View.INVISIBLE);
        board = new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};

        GridLayout boardLayout = findViewById(R.id.boardLayout);
        for (int i = 0; i < boardLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) boardLayout.getChildAt(i);
            counter.setImageResource(0);
        }
        isActive = true;
    }
}