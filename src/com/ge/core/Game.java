/**
 *
 * Game.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 * To add a game mode : - Add menu - Add highscore - Add enums - Modify threads
 * - update players and elements state checks - update pause state - update
 * repaint - Update notice if neaded
 */
package com.ge.core;

import com.ge.app.Parameters;
import com.ge.ui.GamePanel;
import com.ge.threading.ForegroundElementsThread;
import com.ge.threading.MainThread;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public final class Game
{

    /**
     * Current state of the game
     */
    private State state;

    /**
     * True going Left
     */
    private boolean goingLeft;

    /**
     * True Going right
     */
    private boolean goingRight;

    /**
     * Actual game panel
     */
    private final GamePanel gamep;

    /**
     * Current score
     */
    private int score;

    /**
     * Highscore for anticipation mode
     */
    private int highScoreAnticipation;

    /**
     * Highscore for reflex mode
     */
    private int highScoreReflex;

    /**
     * Highscore for geometry hero mode
     */
    private int highScoreGH;

    /**
     * Player
     */
    private Player plyr;

    /**
     * Foreground elements
     */
    private final ArrayList<ForegroundElement> foregroundElements;

    /**
     * Background elements
     */
    private final ArrayList<BackgroundElement> backgroundElements;

    /**
     * Path
     */
    private final ArrayList<Point> path;

    /**
     * Bullets
     */
    private final ArrayList<Bullet> bullets;

    /**
     * Current difficulty of the game
     */
    private DifficultyLevel dl;

    /**
     * Bullet available
     */
    private int bulletAmount;

    /**
     * Current index inside menu
     */
    private int menuIndex;

    /**
     *
     * @param gp
     */
    public Game(GamePanel gp)
    {
        this.gamep = gp;
        this.menuIndex = 0;
        this.state = State.ON_MENU;
        this.dl = DifficultyLevel.EASY;
        this.backgroundElements = new ArrayList<>();
        this.foregroundElements = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.path = new ArrayList<>();
        this.fillBackground();
        this.init();
    }

    /**
     * Fill background with element
     */
    public void fillBackground()
    {
        Random ran = new Random();
        int x, y, size;
        for (int i = 0; i < Parameters.NB_BG_ELEMENT / 7; i++)
        {
            x = ran.nextInt(Parameters.GAME_WINDOW_WIDTH - Parameters.MAX_BG_ELEMENT_SIZE);
            y = ran.nextInt(Parameters.GAME_WINDOW_HEIGHT - Parameters.MAX_BG_ELEMENT_SIZE);
            size = ran.nextInt(Parameters.MAX_BG_ELEMENT_SIZE) + 1;
            backgroundElements.add(new BackgroundElement(new Rectangle(x, y, size, size), this));
        }
        for (int i = 0; i < (Parameters.NB_BG_ELEMENT / 7) * 2; i++)
        {
            x = ran.nextInt(Parameters.GAME_WINDOW_WIDTH - Parameters.MAX_BG_ELEMENT_SIZE);
            y = ran.nextInt(Parameters.GAME_WINDOW_HEIGHT - Parameters.MAX_BG_ELEMENT_SIZE);
            size = ran.nextInt(Parameters.MAX_BG_ELEMENT_SIZE / 2) + 1;
            backgroundElements.add(new BackgroundElement(new Rectangle(x, y, size, size), this));
        }
        for (int i = 0; i < (Parameters.NB_BG_ELEMENT / 7) * 4; i++)
        {
            x = ran.nextInt(Parameters.GAME_WINDOW_WIDTH - Parameters.MAX_BG_ELEMENT_SIZE);
            y = ran.nextInt(Parameters.GAME_WINDOW_HEIGHT - Parameters.MAX_BG_ELEMENT_SIZE);
            size = ran.nextInt(Parameters.MAX_BG_ELEMENT_SIZE / 7) + 1;
            backgroundElements.add(new BackgroundElement(new Rectangle(x, y, size, size), this));
        }
    }

    /**
     * init some game element
     */
    public void init()
    {
        menuIndex = 0;
        MainThread mt = new MainThread(this);
        mt.start();
        ForegroundElementsThread fet = new ForegroundElementsThread(this);
        fet.start();
    }

    /**
     * @return the gamep
     */
    public GamePanel getGamep()
    {
        return gamep;
    }

    /**
     * Actualise UI
     */
    public void actualiseView()
    {
        this.gamep.repaint();
    }

    public void startgame(State st)
    {
        if (st == State.ON_PLAY_ANTICIPATION || st == State.ON_PLAY_REFLEX || st == State.ON_PLAY_GH)
        {
            //Start
            this.setState(st);
            this.plyr = new Player(new Rectangle(Parameters.DEFAULT_PLAYER_STARTING_POS, Parameters.DEFAULT_PLAYER_DIMENSION), this);
            this.setScore(0);
            this.bulletAmount = 0;
            this.foregroundElements.clear();
            this.bullets.clear();
            this.path.clear();
            this.dl = DifficultyLevel.EASY;
            this.goingLeft = false;
            this.goingRight = false;
        }
    }

    /**
     * Stops all threads
     */
    public void endThreads()
    {
        this.setState(State.ON_WAIT);
        //Wait for end of threads
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
        }
    }

    /**
     * Close window and game
     */
    public void quit()
    {
        this.endThreads();
        this.gamep.getGw().dispose();
    }

    /**
     * Update score
     */
    public void updateScore()
    {
        if (getScore() > getHighScore(state))
        {
            if (state == State.ON_PLAY_ANTICIPATION)
            {
                highScoreAnticipation = getScore();
            }
            else if (state == State.ON_PLAY_REFLEX)
            {
                highScoreReflex = getScore();
            }
            else if (state == State.ON_PLAY_GH)
            {
                highScoreGH = getScore();
            }
        }
    }

    /**
     * Shoot a bullet
     */
    public void shoot()
    {
        if (state == State.ON_PLAY_ANTICIPATION || state == State.ON_PLAY_REFLEX)
        {
            if (bulletAmount > 0)
            {
                getBullets().add(new Bullet(new Rectangle(this.plyr.box.x + (this.plyr.box.width / 2), this.plyr.box.y - 5, Parameters.BULLET_DIMENSION.width, Parameters.BULLET_DIMENSION.height), this));
                bulletAmount--;
            }
        }
    }

    /**
     * Move player to the right
     */
    public void moveRight()
    {
        getPlyr().moveRight();
    }

    /**
     * move player to the left
     */
    public void moveLeft()
    {
        getPlyr().moveLeft();
    }

    /**
     * @return the goingLeft
     */
    public boolean isGoingLeft()
    {
        return goingLeft;
    }

    /**
     * @param goingLeft the goingLeft to set
     */
    public void setGoingLeft(boolean goingLeft)
    {
        this.goingLeft = goingLeft;
    }

    /**
     * @return the goingRight
     */
    public boolean isGoingRight()
    {
        return goingRight;
    }

    /**
     * @param goingRight the goingRight to set
     */
    public void setGoingRight(boolean goingRight)
    {
        this.goingRight = goingRight;
    }

    /**
     * @return the state
     */
    public State getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state)
    {
        this.state = state;
    }

    /**
     * @return the plyr
     */
    public Player getPlyr()
    {
        return plyr;
    }

    /**
     * @return the foregroundElements
     */
    public ArrayList<ForegroundElement> getForegroundElements()
    {
        return foregroundElements;
    }

    /**
     * @return the backgroundElements
     */
    public ArrayList<BackgroundElement> getBackgroundElements()
    {
        return backgroundElements;
    }

    /**
     * @return the score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * @param st
     * @return the highScore relative to st or -1 if there's no highscore
     */
    public int getHighScore(State st)
    {
        if (st == State.ON_PAUSE_ANTICIPATION || st == State.ON_PLAY_ANTICIPATION)
        {
            return highScoreAnticipation;
        }
        else if (st == State.ON_PAUSE_REFLEX || st == State.ON_PLAY_REFLEX)
        {
            return highScoreReflex;
        }
        else if (st == State.ON_PAUSE_GH || st == State.ON_PLAY_GH)
        {
            return highScoreGH;
        }
        else
        {
            return -1;
        }
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score)
    {
        this.score = score;
        this.updateScore();
        if (score >= 0 && score < Parameters.EASY_SCORE_LIMIT)
        {
            dl = DifficultyLevel.EASY;
        }
        else if (score >= Parameters.EASY_SCORE_LIMIT && score < Parameters.MEDIUM_SCORE_LIMIT)
        {
            dl = DifficultyLevel.MEDIUM;
        }
        else if (score >= Parameters.MEDIUM_SCORE_LIMIT && score < Parameters.HARD_SCORE_LIMIT)
        {
            dl = DifficultyLevel.HARD;
        }
        else
        {
            dl = DifficultyLevel.HELL;
        }
    }

    /**
     * @return the dl
     */
    public DifficultyLevel getDifficultyLevel()
    {
        return dl;
    }

    /**
     * Add 1 ammo
     */
    public void increasAmmoAmount()
    {
        if (getBulletAmount() + 1 <= Parameters.MAX_BULLET_AMOUNT)
        {
            bulletAmount++;
        }
    }

    /**
     * @return the ammoAmount
     */
    public int getBulletAmount()
    {
        return bulletAmount;
    }

    /**
     * @return the bullets
     */
    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    /**
     * @return the path
     */
    public ArrayList<Point> getPath()
    {
        return path;
    }

    /**
     * @return the menuIndex
     */
    public int getMenuIndex()
    {
        return menuIndex;
    }

    /**
     * Change selection of the menu to the upper one (circular menu)
     */
    public void menuSelectionUp()
    {
        if (menuIndex == 0)
        {
            menuIndex = Parameters.MENU_ITEM_NUMBER - 1;
        }
        else
        {
            --menuIndex;
        }
    }

    /**
     * Change selection of the menu to the down one (circular menu)
     */
    public void menuSelectionDown()
    {
        menuIndex = (menuIndex + 1) % Parameters.MENU_ITEM_NUMBER;
    }
}
