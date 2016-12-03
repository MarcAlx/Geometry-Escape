/**
 *
 * Player.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.core;

import com.ge.app.Parameters;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class Player extends Element
{

    private final Point[] points;

    public Player(Rectangle box, Game g)
    {
        super(box, g);
        points = new Point[3];
        points[0] = new Point(box.x, box.y + box.height);//Bottom left point
        points[1] = new Point(box.x + box.width, box.y + box.height);//Bottom right point
        points[2] = new Point(box.x + box.width / 2, box.y);//Top point
    }

    public void moveLeft()
    {
        int padding = 0;
        if (gm.getState() == State.ON_PLAY_ANTICIPATION)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_ANTICIPATION;
        }
        else if (gm.getState() == State.ON_PLAY_REFLEX)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_REFLEX;
        }
        else if (gm.getState() == State.ON_PLAY_GH)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_GH;
        }

        if (box.x - padding > 0)
        {
            for (Point p : getPoints())
            {
                p.x -= padding;
            }
            box.x -= padding;
        }
    }

    public void moveRight()
    {
        int padding = 0;
        if (gm.getState() == State.ON_PLAY_ANTICIPATION)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_ANTICIPATION;
        }
        else if (gm.getState() == State.ON_PLAY_REFLEX)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_REFLEX;
        }
        else if (gm.getState() == State.ON_PLAY_GH)
        {
            padding = Parameters.DEFAULT_PLAYER_MOVE_PADDING_GH;
        }

        if (box.x + padding < Parameters.GAME_WINDOW_WIDTH - (2 * padding))
        {
            for (Point p : getPoints())
            {
                p.x += padding;
            }
            box.x += padding;
        }
    }

    @Override
    public void move()
    {
    }

    /**
     * @return the points
     */
    public Point[] getPoints()
    {
        return points;
    }

    /**
     *
     * @return
     */
    public Polygon getPolygon()
    {
        int[] xcoords = new int[3];
        int[] ycoords = new int[3];
        for (int i = 0; i < points.length; i++)
        {
            xcoords[i] = points[i].x;
            ycoords[i] = points[i].y;
        }
        return new Polygon(xcoords, ycoords, 3);
    }

    public Rectangle getHitBox()
    {
        return box;
    }
}
