/**
 *
 * ForegroundElementsThread.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.threading;

import com.ge.app.Parameters;
import com.ge.core.DifficultyLevel;
import com.ge.core.ForegroundElement;
import com.ge.core.Game;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class ForegroundElementsThread extends Thread
{

    private final Game gm;

    public ForegroundElementsThread(Game g)
    {
        this.gm = g;
    }

    @Override
    public void run()
    {
        Random ran = new Random();
        int line = 0;
        while (gm.getState() == com.ge.core.State.ON_PLAY_ANTICIPATION || gm.getState() == com.ge.core.State.ON_PLAY_REFLEX || gm.getState() == com.ge.core.State.ON_PAUSE_ANTICIPATION || gm.getState() == com.ge.core.State.ON_PAUSE_REFLEX || gm.getState() == com.ge.core.State.ON_PLAY_GH || gm.getState() == com.ge.core.State.ON_PAUSE_GH || gm.getState() == com.ge.core.State.ON_MENU)
        {
            if (gm.getState() != com.ge.core.State.ON_PAUSE_ANTICIPATION && gm.getState() != com.ge.core.State.ON_PAUSE_REFLEX && gm.getState() != com.ge.core.State.ON_PAUSE_GH && gm.getState() != com.ge.core.State.ON_MENU)
            {
                if (gm.getState() != com.ge.core.State.ON_PLAY_GH)
                {
                    if (gm.getDifficultyLevel() == DifficultyLevel.EASY)
                    {
                        line = ran.nextInt(Parameters.EASY_PATTERN_LIMIT);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.MEDIUM)
                    {
                        line = ran.nextInt(Parameters.MEDIUM_PATTERN_LIMIT);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.HARD)
                    {
                        line = ran.nextInt(Parameters.HARD_PATTERN_LIMIT);
                    }
                    else if (gm.getDifficultyLevel() == DifficultyLevel.HELL)
                    {
                        line = ran.nextInt(Parameters.HELL_PATTERN_LIMIT);
                    }
                }
                //GH MODE
                else
                {
                    line = ran.nextInt(Parameters.EASY_PATTERN_LIMIT);
                }

                for (int i = 0; i < Parameters.PATTERN_MATRIX[line].length; i++)
                {
                    if (Parameters.PATTERN_MATRIX[line][i] == 1)
                    {
                        gm.getForegroundElements().add(new ForegroundElement(new Rectangle(i * Parameters.DEFAULT_FG_ELEMENT_DIMENSION.width, 0, Parameters.DEFAULT_FG_ELEMENT_DIMENSION.width, Parameters.DEFAULT_FG_ELEMENT_DIMENSION.height), gm));
                    }
                }
            }

            //Refresh
            try
            {
                //Reflex
                if (gm.getState() == com.ge.core.State.ON_PAUSE_REFLEX || gm.getState() == com.ge.core.State.ON_PLAY_REFLEX)
                {
                    Thread.sleep(Parameters.REFLEX_FOREGROUND_ELMENT_TIME);
                }
                //GH
                else if (gm.getState() == com.ge.core.State.ON_PAUSE_GH || gm.getState() == com.ge.core.State.ON_PLAY_GH)
                {
                    Thread.sleep(Parameters.GH_FOREGROUND_ELMENT_TIME - (gm.getDifficultyLevel().ordinal() + 1) * Parameters.GH_TIME_REDUCTION);
                }
                //Default MENU + ANTICIPATION
                else
                {
                    Thread.sleep(Parameters.DEFAULT_FOREGROUND_ELMENT_TIME);
                }
            }
            catch (InterruptedException ex)
            {
            }

            this.gm.actualiseView();
        }
    }
}
