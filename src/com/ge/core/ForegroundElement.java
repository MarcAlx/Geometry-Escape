/**
 *
 * ForegroundElement.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.core;

import com.ge.app.Parameters;
import java.awt.Rectangle;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class ForegroundElement extends Element
{

    public ForegroundElement(Rectangle box, Game g)
    {
        super(box, g);
    }

    @Override
    public void move()
    {
        if (gm.getState() == com.ge.core.State.ON_PLAY_ANTICIPATION)
        {
            box.y += Parameters.DEFAULT_FG_ELEMENT_PADDING_ANTICIPATION;
        }
        else if (gm.getState() == com.ge.core.State.ON_PLAY_REFLEX)
        {
            box.y += Parameters.DEFAULT_FG_ELEMENT_PADDING_REFLEX;
        }
        else if (gm.getState() == com.ge.core.State.ON_PLAY_GH)
        {
            box.y += Parameters.DEFAULT_FG_ELEMENT_PADDING_GH;
        }
    }

    public Rectangle getHitBox()
    {
        return box;
    }

}
