/**
 *
 * GameWindow.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.ui;

import com.ge.app.Parameters;
import com.ge.core.Game;
import javax.swing.JFrame;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class GameWindow extends JFrame
{

    /**
     * Panel of the game window
     */
    private GamePanel gamePanel;

    /**
     * Game
     */
    private Game currentGame;

    public void build()
    {
        this.setTitle(Parameters.APPNAME + " " + Parameters.VERSION_NUMBER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Parameters.GAME_WINDOW_DIMENSION);
        this.setLocationRelativeTo(null);

        gamePanel = new GamePanel(this);
        this.setContentPane(gamePanel);

        this.setVisible(true);

    }

    public void init()
    {
        this.currentGame = new Game(gamePanel);
        this.gamePanel.setGm(getCurrentGame());
        this.addKeyListener(new GameKeyListenner(getCurrentGame()));

        //this.getCurrentGame().start();
    }

    /**
     * @return the currentGame
     */
    public Game getCurrentGame()
    {
        return currentGame;
    }
}
