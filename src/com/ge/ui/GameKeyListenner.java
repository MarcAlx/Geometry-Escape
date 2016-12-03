/**
 *
 * DuckDuck - GameKeyListenner.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.ui;

import com.ge.core.Game;
import com.ge.core.MenuId;
import com.ge.core.State;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keylistenner of the game, manage key binding
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class GameKeyListenner implements KeyListener
{

    private final Game gm;

    public GameKeyListenner(Game g)
    {
        this.gm = g;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            this.gm.setGoingRight(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q)
        {
            this.gm.setGoingLeft(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_Z)
        {
            if (this.gm.getState() == State.ON_MENU)
            {
                this.gm.menuSelectionUp();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            if (this.gm.getState() == State.ON_MENU)
            {
                this.gm.menuSelectionDown();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER && gm.getState() == State.ON_MENU)
        {
            if (gm.getMenuIndex() == MenuId.ANTICIPATION_INDEX.ordinal())
            {
                gm.startgame(State.ON_PLAY_ANTICIPATION);
            }
            else if (gm.getMenuIndex() == MenuId.REFLEX_INDEX.ordinal())
            {
                gm.startgame(State.ON_PLAY_REFLEX);
            }
            else if (gm.getMenuIndex() == MenuId.GH_INDEX.ordinal())
            {
                gm.startgame(State.ON_PLAY_GH);
            }
            else if (gm.getMenuIndex() == MenuId.QUIT_INDEX.ordinal())
            {
                gm.quit();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            this.gm.setGoingRight(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q)
        {
            this.gm.setGoingLeft(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_Z)
        {
            this.gm.shoot();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if (this.gm.getState() == State.ON_PLAY_ANTICIPATION)
            {
                this.gm.setState(State.ON_PAUSE_ANTICIPATION);
            }
            else if (this.gm.getState() == State.ON_PAUSE_ANTICIPATION)
            {
                this.gm.setState(State.ON_PLAY_ANTICIPATION);
            }
            else if (this.gm.getState() == State.ON_PLAY_REFLEX)
            {
                this.gm.setState(State.ON_PAUSE_REFLEX);
            }
            else if (this.gm.getState() == State.ON_PAUSE_REFLEX)
            {
                this.gm.setState(State.ON_PLAY_REFLEX);
            }
            else if (this.gm.getState() == State.ON_PLAY_GH)
            {
                this.gm.setState(State.ON_PAUSE_GH);
            }
            else if (this.gm.getState() == State.ON_PAUSE_GH)
            {
                this.gm.setState(State.ON_PLAY_GH);
            }
        }
    }
}
