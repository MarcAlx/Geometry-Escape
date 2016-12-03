/**
 *
 * App.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.app;

import com.ge.ui.GameWindow;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class App implements Runnable
{

    /**
     * Current game window
     */
    private final GameWindow gw;
    
    public App()
    {
        gw = new GameWindow();
    }

    @Override
    public void run()
    {
        gw.build();
        gw.init();
    }
}
