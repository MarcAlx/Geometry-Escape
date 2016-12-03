/**
 *
 * BackgroundElement.java
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
public class BackgroundElement extends Element
{

    public BackgroundElement(Rectangle box, Game g)
    {
        super(box, g);
    }

    @Override
    public void move()
    {
        if (box.y < Parameters.GAME_WINDOW_HEIGHT)
        {
            box.y += box.width;
        }
        else
        {
            box.y = 0 - Parameters.MAX_BG_ELEMENT_SIZE;
        }
    }

    public Rectangle getHitBox()
    {
        return box;
    }
}
