/**
 *
 * Parameters.java
 *
 * Created by Marc-Alexandre Blanchard - all right reserved ©
 *
 * 2014
 *
 */
package com.ge.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Crée par Marc-Alexandre Blanchard
 */
public class Parameters
{

    /**
     * Name of the app
     */
    public final static String APPNAME = "Geometry Escape";

    /**
     * Default width of the game window
     */
    public final static int GAME_WINDOW_WIDTH = 500;

    /**
     * Default heigth of the game window
     */
    public final static int GAME_WINDOW_HEIGHT = 720;

    /**
     * Path to images
     */
    public final static String IMAGE_PATH = "/com/ge/ressources/images/";

    /**
     * Tell if game is in debug
     */
    public final static boolean DEBUG = false;

    /**
     * Dimension of the game window
     */
    public final static Dimension GAME_WINDOW_DIMENSION = new Dimension(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);

    //Version informations
    private final static int MAJOR_RELEASE_NUMBER = 2;
    private final static int SUB_RELEASE_NUMBER = 1;
    private final static int MINOR_RELEASE_NUMBER = 1;
    private final static char RELEASE_TYPE = 'r';

    /**
     * Version number of the grame
     */
    public final static String VERSION_NUMBER = MAJOR_RELEASE_NUMBER + "." + SUB_RELEASE_NUMBER + "." + MINOR_RELEASE_NUMBER + RELEASE_TYPE;

    /**
     * Default background color
     */
    public final static Color BG_COLOR = Color.BLACK;

    /**
     * Default color of the player
     */
    public final static Color PLAYER_COLOR = Color.WHITE;

    /**
     * bg element default color
     */
    public final static Color BG_ELEMENT_COLOR = Color.BLUE;

    /**
     * fg element foreground color
     */
    public final static Color FG_ELEMENT_COLOR_EASY = Color.GREEN;

    /**
     * fg element foreground color
     */
    public final static Color FG_ELEMENT_COLOR_MEDIUM = Color.YELLOW;

    /**
     * fg element foreground color
     */
    public final static Color FG_ELEMENT_COLOR_HARD = Color.ORANGE;

    /**
     * fg element foreground color
     */
    public final static Color FG_ELEMENT_COLOR_HELL = Color.RED;

    /**
     * menu select color
     */
    public final static Color MENU_SELECT_COLOR = Color.CYAN;

    /**
     * Ammo color
     */
    public final static Color BULLET_COLOR = Color.PINK;

    /**
     * Default check time for the Main Thread
     */
    public final static int DEFAULT_CHECK_TIME = 30;

    /**
     * Default check time for the Main Thread
     */
    public final static int REFLEX_CHECK_TIME = 30;

    /**
     * Default foreground element adding time
     */
    public final static int DEFAULT_FOREGROUND_ELMENT_TIME = 1000;

    /**
     * Default foreground element adding time
     */
    public final static int REFLEX_FOREGROUND_ELMENT_TIME = 500;

    /**
     * Default foreground element adding time
     */
    public final static int GH_FOREGROUND_ELMENT_TIME = 800;

    /**
     * Time reduction relative to dificulty level in GH mode
     */
    public final static int GH_TIME_REDUCTION = 75;

    /**
     * Default move padding for anticipation mode
     */
    public final static int DEFAULT_PLAYER_MOVE_PADDING_ANTICIPATION = 4;

    /**
     * Default move padding for reflex mode
     */
    public final static int DEFAULT_PLAYER_MOVE_PADDING_REFLEX = 8;

    /**
     * Default move padding for gh mode
     */
    public final static int DEFAULT_PLAYER_MOVE_PADDING_GH = 18;

    /**
     * Default move padding for foreground element
     */
    public final static int DEFAULT_FG_ELEMENT_PADDING_ANTICIPATION = 4;

    /**
     * Default move padding for foreground element
     */
    public final static int DEFAULT_FG_ELEMENT_PADDING_REFLEX = 8;

    /**
     * Default move padding for foreground element
     */
    public final static int DEFAULT_FG_ELEMENT_PADDING_GH = 8;

    /**
     * Default bullet move padding
     */
    public final static int DEFAULT_BULLET_MOVE_PADING = 8;

    /**
     * Number of menu item
     */
    public final static int MENU_ITEM_NUMBER = 4;

    /**
     * Default player height
     */
    public final static Dimension DEFAULT_PLAYER_DIMENSION = new Dimension(20, 20);

    /**
     * Default player starting position
     */
    public final static Point DEFAULT_PLAYER_STARTING_POS = new Point((GAME_WINDOW_WIDTH / 2) - (DEFAULT_PLAYER_DIMENSION.width / 2), (GAME_WINDOW_HEIGHT / 8) * 6);

    /**
     * Score position
     */
    public final static Point SCORE_POSITION = new Point(5, 15);

    /**
     * Highscore postion
     */
    public final static Point HIGHSCORE_POSITION = new Point(GAME_WINDOW_WIDTH - 200, 15);

    /**
     * Number of background element
     */
    public final static int NB_BG_ELEMENT = 80;

    /**
     * Default margin for text
     */
    public final static int MARGIN_TEXT = Parameters.GAME_WINDOW_WIDTH / 7;

    /**
     * Anticipation menu default postion
     */
    public final static Point ANTICIPATION_MENU_POSTION = new Point(MARGIN_TEXT, 350);

    /**
     * Reflex menu default postion
     */
    public final static Point REFLEX_MENU_POSTION = new Point(MARGIN_TEXT, 350 + 20);

    /**
     * Geometry menu hero default postion
     */
    public final static Point GH_MENU_POSTION = new Point(MARGIN_TEXT, 350 + 40);

    /**
     * Quit menu default postion
     */
    public final static Point QUIT_MENU_POSTION = new Point(MARGIN_TEXT, 350 + 60);

    /**
     * State message postion show "Menu or pause"
     */
    public final static Point STATE_MESSAGE_POSTION = new Point(Parameters.GAME_WINDOW_WIDTH / 2 - 25, Parameters.GAME_WINDOW_HEIGHT / 3);

    /**
     * Information message 1 postion
     */
    public final static Point IM1_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 30);

    /**
     * Information message 2 postion
     */
    public final static Point IM2_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 50);

    /**
     * Information message 3 postion
     */
    public final static Point IM3_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 70);

    /**
     * Information message 4 postion
     */
    public final static Point IM4_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 90);

    /**
     * Information message 5 postion
     */
    public final static Point IM5_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 110);

    /**
     * Information message 6 postion
     */
    public final static Point IM6_POSITION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 3 + 130);

    /**
     * Author message postion
     */
    public final static Point AUTHOR_POSTION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 4 + 50);

    /**
     * Contact message postion
     */
    public final static Point CONTACT_POSTION = new Point(MARGIN_TEXT, (Parameters.GAME_WINDOW_HEIGHT / 5) * 4 + 70);

    /**
     * title image position
     */
    public final static Point TITLE_POSITION = new Point(MARGIN_TEXT, 0);

    /**
     * Bullet information default position
     */
    public final static Point BULLET_POSITION = new Point(5, 25);

    /**
     * Bullet dimension
     */
    public final static Dimension BULLET_DIMENSION = new Dimension(5, 10);

    /**
     * Default dimension of foreground bloc
     */
    public final static Dimension DEFAULT_FG_ELEMENT_DIMENSION = new Dimension(GAME_WINDOW_WIDTH / 5, 10);

    /**
     * Max bg element size
     */
    public final static int MAX_BG_ELEMENT_SIZE = 22;

    /**
     * Score minimal value
     */
    public final static int SCORE_MIN_VALUE = 1;

    /**
     * Pattern matrix for foreground element creation
     */
    public final static int[][] PATTERN_MATRIX =
    {
        {
            0, 0, 0, 0, 1
        },
        {
            0, 0, 0, 1, 0
        },
        {
            0, 0, 1, 0, 0
        },
        {
            0, 1, 0, 0, 0
        },
        {
            1, 0, 0, 0, 0
        },
        {
            0, 0, 0, 1, 1
        },
        {
            0, 0, 1, 1, 0
        },
        {
            0, 1, 1, 0, 0
        },
        {
            1, 1, 0, 0, 0
        },
        {
            1, 0, 0, 0, 1
        },
        {
            0, 1, 0, 0, 1
        },
        {
            0, 0, 1, 0, 1
        },
        {
            1, 0, 0, 1, 0
        },
        {
            1, 0, 1, 0, 0
        },
        {
            0, 1, 0, 1, 0
        },
        {
            0, 0, 1, 1, 1
        },
        {
            0, 1, 1, 1, 0
        },
        {
            1, 1, 1, 0, 0
        },
        {
            0, 1, 0, 1, 1
        },
        {
            1, 0, 0, 1, 1
        },
        {
            1, 1, 0, 0, 1
        },
        {
            1, 1, 0, 1, 0
        },
        {
            1, 0, 1, 0, 1
        },
        {
            0, 1, 1, 0, 1
        },
        {
            1, 0, 1, 1, 0
        },
        {
            0, 1, 1, 1, 1
        },
        {
            1, 1, 1, 1, 0
        },
        {
            1, 1, 0, 1, 1
        },
        {
            1, 1, 1, 0, 1
        },
        {
            1, 0, 1, 1, 1
        },
        {
            1, 1, 1, 1, 1
        }
    };

    /**
     * Limit index for easy mode inside Pattern matrix
     */
    public final static int EASY_PATTERN_LIMIT = 5;
    /**
     * Limit index for medium mode inside Pattern matrix
     */
    public final static int MEDIUM_PATTERN_LIMIT = 15;
    /**
     * Limit index for hard mode inside Pattern matrix
     */
    public final static int HARD_PATTERN_LIMIT = 24;
    /**
     * Limit index for hell mode inside Pattern matrix
     */
    public final static int HELL_PATTERN_LIMIT = PATTERN_MATRIX.length;

    /**
     * Easy score limit
     */
    public final static int EASY_SCORE_LIMIT = 1000;

    /**
     * medium score limit
     */
    public final static int MEDIUM_SCORE_LIMIT = 3000;

    /**
     * hard score limit
     */
    public final static int HARD_SCORE_LIMIT = 5000;

    /**
     * hell limit
     */
    public final static int HELL_SCORE_LIMIT = 8000;

    /**
     * Max bullet available
     */
    public final static int MAX_BULLET_AMOUNT = 3;

    /**
     * Default combo limit
     */
    public final static int COMBO_LIMIT = 40;

    /**
     * Anticipation menu default postion
     */
    public final static String ANTICIPATION_MENU_TEXT = "Anticipation";

    /**
     * Reflex menu default postion
     */
    public final static String REFLEX_MENU_TEXT = "Reflex";

    /**
     * Reflex menu default postion
     */
    public final static String GH_MENU_TEXT = "Geometry Hero";

    /**
     * Quit menu default postion
     */
    public final static String QUIT_MENU_TEXT = "Quit";

    /**
     * Use to build Instruction message
     */
    public final static String ANTICIPATION_IM1 = "Find you way between green squares.";

    /**
     * Use to build Instruction message
     */
    public final static String ANTICIPATION_IM2 = "Each " + Parameters.COMBO_LIMIT + " squares, you can shoot a bullet to destroy a square.";

    /**
     * Use to build Instruction message
     */
    public final static String REFLEX_IM1 = "Reflex is much more fast than Anticipation and needs quick moves.";

    /**
     * Use to build Instruction message
     */
    public final static String REFLEX_IM2 = ANTICIPATION_IM2;

    /**
     * Use to build Instruction message
     */
    public final static String GH_IM1 = "In GH Mode you have to go through squares.";

    /**
     * Use to build Instruction message
     */
    public final static String GH_IM2 = "Game speed up as long as you play.";

    /**
     * Author message
     */
    public final static String AUTHOR_MESSAGE = "Created and designed by Marc-Alx";

    /**
     * Author message
     */
    public final static String CONTACT_MESSAGE = "Feedback at marc-alx@outlook.com";
}
