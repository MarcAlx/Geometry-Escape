/**
 *
 * MainThread.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.threading;

import com.ge.app.Parameters;
import com.ge.core.BackgroundElement;
import com.ge.core.Bullet;
import com.ge.core.ForegroundElement;
import com.ge.core.Game;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class MainThread extends Thread
{

    private final Game gm;
    private final ArrayList<ForegroundElement> toRemove;
    private final ArrayList<Bullet> toRemove2;
    private final ArrayList<Point> toRemove3;
    private ArrayList<ForegroundElement> temp;
    private int blocCombo;

    public MainThread(Game g)
    {
        this.gm = g;
        toRemove = new ArrayList<>();
        toRemove2 = new ArrayList<>();
        toRemove3 = new ArrayList<>();
        temp = new ArrayList<>();
        blocCombo = 0;
    }

    @Override
    public void run()
    {
        while (gm.getState() == com.ge.core.State.ON_PLAY_ANTICIPATION
                || gm.getState() == com.ge.core.State.ON_PLAY_REFLEX
                || gm.getState() == com.ge.core.State.ON_PLAY_GH
                || gm.getState() == com.ge.core.State.ON_PAUSE_ANTICIPATION
                || gm.getState() == com.ge.core.State.ON_PAUSE_REFLEX
                || gm.getState() == com.ge.core.State.ON_PAUSE_GH
                || gm.getState() == com.ge.core.State.ON_MENU)
        {
            //Move Background elements
            for (BackgroundElement bge : this.gm.getBackgroundElements())
            {
                bge.move();
            }

            if (gm.getState() != com.ge.core.State.ON_PAUSE_ANTICIPATION
                    && gm.getState() != com.ge.core.State.ON_PAUSE_REFLEX
                    && gm.getState() != com.ge.core.State.ON_PAUSE_GH
                    && gm.getState() != com.ge.core.State.ON_MENU
                    && gm.getPlyr() != null)
            {

                //Player's moves
                if (this.gm.isGoingLeft() && !this.gm.isGoingRight())
                {
                    this.gm.moveLeft();
                }
                else if (!this.gm.isGoingLeft() && this.gm.isGoingRight())
                {
                    this.gm.moveRight();
                }

                //Check collision
                temp = new ArrayList<>(this.gm.getForegroundElements());
                for (ForegroundElement fge : temp)
                {
                    if (fge.getHitBox().intersects(this.gm.getPlyr().getHitBox()))
                    {
                        if (gm.getState() != com.ge.core.State.ON_PLAY_GH)
                        {
                            this.gm.setState(com.ge.core.State.ON_MENU);
                        }
                        else
                        {
                            toRemove.add(fge);
                        }
                    }
                }

                //Move Foreground elements
                temp = new ArrayList<>(this.gm.getForegroundElements());
                for (int i = 0; i < temp.size(); i++)
                {
                    temp.get(i).move();
                    if (temp.get(i).getHitBox().y > Parameters.GAME_WINDOW_HEIGHT)
                    {
                        toRemove.add(temp.get(i));
                    }
                    else if (temp.get(i).getHitBox().y > Parameters.DEFAULT_PLAYER_STARTING_POS.y + gm.getPlyr().getHitBox().height && this.gm.getState() == com.ge.core.State.ON_PLAY_GH)
                    {
                        this.gm.setState(com.ge.core.State.ON_MENU);
                    }
                }

                if (gm.getState() != com.ge.core.State.ON_PLAY_GH)
                {
                    //Bullets interactions
                    Bullet bl;
                    for (int j = 0; j < gm.getBullets().size(); j++)
                    {
                        bl = gm.getBullets().get(j);

                        //collision
                        temp = new ArrayList<>(this.gm.getForegroundElements());
                        for (int i = 0; i < temp.size(); i++)
                        {
                            //collision check or out of screen
                            if (bl.getHitBox().intersects(temp.get(i).getHitBox()) || temp.get(i).getHitBox().contains(bl.getHitBox()) || bl.getHitBox().x < 0)
                            {
                                //Check if toRemove has already the block in it
                                if (!toRemove.contains(temp.get(i)))
                                {
                                    toRemove.add(temp.get(i));
                                }

                                //Add bullet to remove list
                                toRemove2.add(bl);
                            }
                        }

                        //move
                        bl.move();

                    }

                    //Remove bullets
                    if (!toRemove2.isEmpty())
                    {
                        for (int i = 0; i < toRemove2.size(); i++)
                        {
                            gm.getBullets().remove(toRemove2.get(i));
                        }
                        toRemove2.clear();
                    }

                    //Update ammo amount
                    if (blocCombo >= Parameters.COMBO_LIMIT)
                    {
                        while (blocCombo - Parameters.COMBO_LIMIT >= 0)
                        {
                            gm.increasAmmoAmount();
                            blocCombo -= Parameters.COMBO_LIMIT;
                        }
                    }
                }

                //Remove fg element
                if (!toRemove.isEmpty())
                {
                    for (int i = 0; i < toRemove.size(); i++)
                    {
                        gm.getForegroundElements().remove(toRemove.get(i));
                        blocCombo++;
                    }
                    toRemove.clear();
                }

                //update score
                gm.setScore(gm.getScore() + Parameters.SCORE_MIN_VALUE);//* (gm.getDifficultyLevel().ordinal() + 1));

                //move path
                for (Point p : this.gm.getPath())
                {
                    if (gm.getState() == com.ge.core.State.ON_PLAY_ANTICIPATION)
                    {
                        p.y += Parameters.DEFAULT_PLAYER_MOVE_PADDING_ANTICIPATION;
                    }
                    else if (gm.getState() == com.ge.core.State.ON_PLAY_REFLEX)
                    {
                        p.y += Parameters.DEFAULT_PLAYER_MOVE_PADDING_REFLEX;
                    }
                    else if (gm.getState() == com.ge.core.State.ON_PLAY_GH)
                    {
                        p.y += Parameters.DEFAULT_PLAYER_MOVE_PADDING_GH;
                    }

                    if (p.y > Parameters.GAME_WINDOW_HEIGHT)
                    {
                        toRemove3.add(p);
                    }
                }

                //Add point
                this.gm.getPath().add(new Point(this.gm.getPlyr().getHitBox().x + this.gm.getPlyr().getHitBox().width / 2, this.gm.getPlyr().getHitBox().y + this.gm.getPlyr().getHitBox().height + 5));

                //Remove hidden points
                for (Point p : toRemove3)
                {
                    this.gm.getPath().remove(p);
                }

            }

            //Refresh
            try
            {
                if (gm.getState() == com.ge.core.State.ON_PAUSE_REFLEX || gm.getState() == com.ge.core.State.ON_PLAY_REFLEX)
                {
                    Thread.sleep(Parameters.REFLEX_CHECK_TIME);
                }
                else
                {
                    Thread.sleep(Parameters.DEFAULT_CHECK_TIME);
                }
            }
            catch (InterruptedException ex)
            {
            }

            this.gm.actualiseView();

        }
    }
}
