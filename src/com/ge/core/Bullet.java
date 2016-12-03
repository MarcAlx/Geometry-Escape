/**
 *
 * Bullet.java
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
public class Bullet extends Element
{

    public Bullet(Rectangle box, Game g)
    {
        super(box, g);
    }

    @Override
    public void move()
    {
        box.y -= Parameters.DEFAULT_BULLET_MOVE_PADING;
    }

    public Rectangle getHitBox()
    {
        return box;
    }
}
