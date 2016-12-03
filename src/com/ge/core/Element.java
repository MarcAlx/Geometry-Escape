/**
 *
 * Element.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.core;

import java.awt.Rectangle;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public abstract class Element
{

    protected Rectangle box;
    protected Game gm;

    public Element(Rectangle box, Game g)
    {
        this.box = box;
        this.gm = g;
    }

    /**
     * Define moves of the element
     */
    public abstract void move();
}
