/**
 *
 * GamePanel.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.ui;

import com.ge.app.Parameters;
import com.ge.core.BackgroundElement;
import com.ge.core.Bullet;
import com.ge.core.DifficultyLevel;
import com.ge.core.ForegroundElement;
import com.ge.core.Game;
import com.ge.core.MenuId;
import com.ge.core.State;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class GamePanel extends JPanel
{

    private final GameWindow gw;
    private Game gm;
    private ArrayList<ForegroundElement> temp;
    private ArrayList<Bullet> temp2;
    private ArrayList<Point> temp3;
    private BufferedImage title;

    public GamePanel(GameWindow g)
    {
        this.gw = g;
        this.setLayout(new BorderLayout());
        URL url = getClass().getResource(Parameters.IMAGE_PATH + "title.png");
        try
        {
            title = ImageIO.read(url);
        }
        catch (IOException ex)
        {
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (gm != null)
        {
            //Background fill
            g2d.setColor(Parameters.BG_COLOR);
            g2d.drawRect(0, 0, Parameters.GAME_WINDOW_WIDTH, Parameters.GAME_WINDOW_HEIGHT);
            g2d.fillRect(0, 0, Parameters.GAME_WINDOW_WIDTH, Parameters.GAME_WINDOW_HEIGHT);

            //Draw background element
            g2d.setColor(Parameters.BG_ELEMENT_COLOR);
            for (BackgroundElement bge : gm.getBackgroundElements())
            {
                g2d.drawOval(bge.getHitBox().x, bge.getHitBox().y, bge.getHitBox().width, bge.getHitBox().height);
            }

            if (gm.getState() == State.ON_PLAY_ANTICIPATION || gm.getState() == State.ON_PLAY_REFLEX || gm.getState() == State.ON_PLAY_GH)
            {
                temp = new ArrayList<>(gm.getForegroundElements());
                for (ForegroundElement fge : temp)
                {
                    if (gm.getDifficultyLevel() == DifficultyLevel.EASY)
                    {
                        g2d.setColor(Parameters.FG_ELEMENT_COLOR_EASY);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.MEDIUM)
                    {
                        g2d.setColor(Parameters.FG_ELEMENT_COLOR_MEDIUM);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.HARD)
                    {
                        g2d.setColor(Parameters.FG_ELEMENT_COLOR_HARD);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.HELL)
                    {
                        g2d.setColor(Parameters.FG_ELEMENT_COLOR_HELL);
                    }
                    g2d.drawRect(fge.getHitBox().x, fge.getHitBox().y, fge.getHitBox().width, fge.getHitBox().height);
                }

                //Draw bullets
                if (gm.getState() != State.ON_PLAY_GH)
                {
                    temp2 = new ArrayList<>(gm.getBullets());
                    for (Bullet bl : temp2)
                    {
                        g2d.setColor(Parameters.BULLET_COLOR);
                        g2d.drawRect(bl.getHitBox().x, bl.getHitBox().y, bl.getHitBox().width, bl.getHitBox().height);
                    }
                }

                //Draw player
                g2d.setColor(Parameters.PLAYER_COLOR);
                g2d.drawPolygon(gm.getPlyr().getPolygon());

                //Draw path
                temp3 = new ArrayList<>(gm.getPath());
                for (Point p : temp3)
                {
                    g2d.setColor(Parameters.PLAYER_COLOR);
                    g2d.drawRect(p.x, p.y, 1, 1);
                }

                //Draw scores
                g2d.setColor(Parameters.PLAYER_COLOR);
                g2d.drawString("Score : " + gm.getScore(), Parameters.SCORE_POSITION.x, Parameters.SCORE_POSITION.y);
                g2d.drawString("Highscore : " + gm.getHighScore(gm.getState()), Parameters.HIGHSCORE_POSITION.x, Parameters.HIGHSCORE_POSITION.y);

                if (gm.getState() != State.ON_PLAY_GH)
                {
                    //Draw bullet available
                    g2d.setColor(Parameters.BULLET_COLOR);
                    for (int i = 0; i < gm.getBulletAmount(); i++)
                    {
                        g2d.drawRect((i + 1) * Parameters.BULLET_POSITION.x + i * 5, Parameters.BULLET_POSITION.y, Parameters.BULLET_DIMENSION.width, Parameters.BULLET_DIMENSION.height);
                    }
                }
            }

            //State Menu
            if (gm.getState() == State.ON_MENU)
            {
                g2d.setColor(Parameters.MENU_SELECT_COLOR);
                //Title image
                g2d.drawImage(title, Parameters.TITLE_POSITION.x, Parameters.TITLE_POSITION.y, this);
                //State message
                g2d.drawString("Use ↑ ↓ to choose a game mode.", Parameters.GAME_WINDOW_WIDTH / 4, Parameters.STATE_MESSAGE_POSTION.y);

                //Menu selection
                if (gm.getMenuIndex() == MenuId.ANTICIPATION_INDEX.ordinal())
                {
                    g2d.setColor(Parameters.MENU_SELECT_COLOR);
                    g2d.drawString(Parameters.ANTICIPATION_MENU_TEXT, Parameters.ANTICIPATION_MENU_POSTION.x, Parameters.ANTICIPATION_MENU_POSTION.y);
                    g2d.drawString("Highscore - " + gm.getHighScore(State.ON_PLAY_ANTICIPATION), Parameters.GH_MENU_POSTION.x + Parameters.GH_MENU_TEXT.length() * 10, Parameters.ANTICIPATION_MENU_POSTION.y);
                    g2d.drawString("Press enter to start Anticipation mode.", Parameters.IM1_POSITION.x, Parameters.IM1_POSITION.y);
                    g2d.drawString(Parameters.ANTICIPATION_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                    g2d.drawString(Parameters.ANTICIPATION_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                }
                else
                {
                    g2d.setColor(Parameters.PLAYER_COLOR);
                    g2d.drawString(Parameters.ANTICIPATION_MENU_TEXT, Parameters.ANTICIPATION_MENU_POSTION.x, Parameters.ANTICIPATION_MENU_POSTION.y);
                }

                if (gm.getMenuIndex() == MenuId.REFLEX_INDEX.ordinal())
                {
                    g2d.setColor(Parameters.MENU_SELECT_COLOR);
                    g2d.drawString(Parameters.REFLEX_MENU_TEXT, Parameters.REFLEX_MENU_POSTION.x, Parameters.REFLEX_MENU_POSTION.y);
                    g2d.drawString("Highscore - " + gm.getHighScore(State.ON_PLAY_REFLEX), Parameters.GH_MENU_POSTION.x + Parameters.GH_MENU_TEXT.length() * 10, Parameters.REFLEX_MENU_POSTION.y);
                    g2d.drawString("Press enter to start Reflex mode.", Parameters.IM1_POSITION.x, Parameters.IM1_POSITION.y);
                    g2d.drawString(Parameters.REFLEX_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                    g2d.drawString(Parameters.REFLEX_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                }
                else
                {
                    g2d.setColor(Parameters.PLAYER_COLOR);
                    g2d.drawString(Parameters.REFLEX_MENU_TEXT, Parameters.REFLEX_MENU_POSTION.x, Parameters.REFLEX_MENU_POSTION.y);
                }

                if (gm.getMenuIndex() == MenuId.GH_INDEX.ordinal())
                {
                    g2d.setColor(Parameters.MENU_SELECT_COLOR);
                    g2d.drawString(Parameters.GH_MENU_TEXT, Parameters.GH_MENU_POSTION.x, Parameters.GH_MENU_POSTION.y);
                    g2d.drawString("Highscore - " + gm.getHighScore(State.ON_PLAY_GH), Parameters.GH_MENU_POSTION.x + Parameters.GH_MENU_TEXT.length() * 10, Parameters.GH_MENU_POSTION.y);
                    g2d.drawString("Press enter to start Geometry Hero mode.", Parameters.IM1_POSITION.x, Parameters.IM1_POSITION.y);
                    g2d.drawString(Parameters.GH_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                    g2d.drawString(Parameters.GH_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                }
                else
                {
                    g2d.setColor(Parameters.PLAYER_COLOR);
                    g2d.drawString(Parameters.GH_MENU_TEXT, Parameters.GH_MENU_POSTION.x, Parameters.GH_MENU_POSTION.y);
                }

                if (gm.getMenuIndex() == MenuId.QUIT_INDEX.ordinal())
                {
                    g2d.setColor(Parameters.MENU_SELECT_COLOR);
                    g2d.drawString(Parameters.QUIT_MENU_TEXT, Parameters.QUIT_MENU_POSTION.x, Parameters.QUIT_MENU_POSTION.y);
                    g2d.drawString("Press enter to quit.", Parameters.IM1_POSITION.x, Parameters.IM1_POSITION.y);
                }
                else
                {
                    g2d.setColor(Parameters.PLAYER_COLOR);
                    g2d.drawString(Parameters.QUIT_MENU_TEXT, Parameters.QUIT_MENU_POSTION.x, Parameters.QUIT_MENU_POSTION.y);
                }

                this.drawSharedMessages(g2d);
            }

            //Pause states
            if (gm.getState() == State.ON_PAUSE_ANTICIPATION)
            {
                g2d.setColor(Parameters.MENU_SELECT_COLOR);
                g2d.drawString("Pause", Parameters.STATE_MESSAGE_POSTION.x, Parameters.STATE_MESSAGE_POSTION.y);
                g2d.drawString(Parameters.ANTICIPATION_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                g2d.drawString(Parameters.ANTICIPATION_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                this.drawSharedMessages(g2d);
            }
            if (gm.getState() == State.ON_PAUSE_REFLEX)
            {
                g2d.setColor(Parameters.MENU_SELECT_COLOR);
                g2d.drawString("Pause", Parameters.STATE_MESSAGE_POSTION.x, Parameters.STATE_MESSAGE_POSTION.y);
                g2d.drawString(Parameters.REFLEX_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                g2d.drawString(Parameters.REFLEX_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                this.drawSharedMessages(g2d);
            }
            if (gm.getState() == State.ON_PAUSE_GH)
            {
                g2d.setColor(Parameters.MENU_SELECT_COLOR);
                g2d.drawString("Pause", Parameters.STATE_MESSAGE_POSTION.x, Parameters.STATE_MESSAGE_POSTION.y);
                g2d.drawString(Parameters.GH_IM1, Parameters.IM3_POSITION.x, Parameters.IM3_POSITION.y);
                g2d.drawString(Parameters.GH_IM2, Parameters.IM4_POSITION.x, Parameters.IM4_POSITION.y);
                this.drawSharedMessages(g2d);
            }
        }

        if (Parameters.DEBUG)
        {
            if (gm.getState() == State.ON_PLAY_ANTICIPATION || gm.getState() == State.ON_PLAY_REFLEX || gm.getState() == State.ON_PAUSE_ANTICIPATION || gm.getState() == State.ON_PAUSE_REFLEX)
            {
                g2d.setColor(Parameters.PLAYER_COLOR);
                g2d.drawRect(gm.getPlyr().getHitBox().x, gm.getPlyr().getHitBox().y, gm.getPlyr().getHitBox().width, gm.getPlyr().getHitBox().height);
            }
        }
    }

    /**
     * Draw shared messages between Menu and Pauses states
     *
     * @param g2d
     */
    private void drawSharedMessages(Graphics2D g2d)
    {
        g2d.setColor(Parameters.PLAYER_COLOR);
        if (gm.getMenuIndex() != MenuId.QUIT_INDEX.ordinal())
        {
            if (gm.getState() == State.ON_MENU && gm.getMenuIndex() == MenuId.GH_INDEX.ordinal() || gm.getState() == State.ON_PAUSE_GH)
            {
                g2d.drawString("In game use Q, D or  ← → to move.", Parameters.IM5_POSITION.x, Parameters.IM5_POSITION.y);
            }
            else
            {
                g2d.drawString("In game use Q, D or  ← → to move. And Z or ↑ to fire.", Parameters.IM5_POSITION.x, Parameters.IM5_POSITION.y);
            }
            g2d.drawString("You can pause game with <space-bar>.", Parameters.IM6_POSITION.x, Parameters.IM6_POSITION.y);
        }
        g2d.drawString(Parameters.AUTHOR_MESSAGE, Parameters.AUTHOR_POSTION.x, Parameters.AUTHOR_POSTION.y);
        g2d.drawString(Parameters.CONTACT_MESSAGE, Parameters.CONTACT_POSTION.x, Parameters.CONTACT_POSTION.y);
    }

    /**
     * @return the gm
     */
    public Game getGm()
    {
        return gm;
    }

    /**
     * @param gm the gm to set
     */
    public void setGm(Game gm)
    {
        this.gm = gm;
    }

    /**
     * @return the gw
     */
    public GameWindow getGw()
    {
        return gw;
    }
}
