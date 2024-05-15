package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.Model.InfoGame;

/**
 * Interface for the winner listener
 * Manages both small board and big board winners
 */
public interface WinnerListener {
    /**
     * When a new small board winner is detected, the method is called
     * @param winner a number telling the winner
     * @param id of the board
     * @see InfoGame.Winner
     */
    void onNewWinner(InfoGame.Winner winner, int id);

    /**
     * When a big board winner is detected, the method is called
     * @param winner a number telling the winner
     * @see InfoGame.Winner
     */
    void onGameWinner(InfoGame.Winner winner);
}
