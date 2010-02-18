package com.mirblu.j2me.fui.app;

import java.util.Vector;

import com.mirblu.j2me.fui.items.image.FCanvas;

public interface FAppInterface {

    /*value -1*/
    public static final byte STATE_NONE = -1;
    /*value 0*/
    public static final byte STATE_MAIN_SCREEN = 0;
    /*value 1*/
    public static final byte STATE_LOADING = 1;
    /*value 2*/
    public static final byte STATE_SPLASH_SCREEN = 2;
    /*value 3*/
    public static final byte STATE_SCREEN_SOURCES = 3;
    /*value 4*/
    public static final byte STATE_SCREEN_DESTINATIONS = 4;
    /*value 5*/
    public static final byte STATE_SCREEN_SELECT_MAPS = 5;
    /*value 6*/
    public static final byte STATE_SCREEN_SHOW_IMAGE = 6;
    /*value 7*/
    public static final byte STATE_SCREEN_EXPLORE_MAP = 7;
    /*value 8*/
    public static final byte STATE_SCREEN_RUTE_PLANNER = 8;
    /*value 9*/
    public static final byte STATE_SCREEN_TRACEME = 9;
    /*used to reload the current state. value 10*/
    public static final byte STATE_RELOAD_CURRENT = 10;
    /*value 11*/
    public static final byte STATE_SCREEN_CATEGORIES = 11;
    /*value 12*/
    public static final byte STATE_SCREEN_PRODUCTS = 12;
    /*value 13*/
    public static final byte STATE_SCREEN_PRODUCTS_SPLASH = 13;
    /*value 13*/
    public static final byte STATE_SCREEN_SHOW_URIS = 14;

    /*value 100*/
    public static final byte STATE_NEXT_SCREEN = 100;
    /*value 101*/
    public static final byte STATE_NEXT_X_SCREEN = 101;
    /*value 97*/
    public static final byte STATE_BACK_TO_MAIN = 97;
    /*value 99*/
    public static final byte STATE_EXIT_PROGRAM = 99;
    /*value 98*/
    public static final byte STATE_BACK = 98;

    /*value 14*/
    public static int INITIAL_CAPACITY = 14;
    /*value 3*/
    public static int CAPACITY_INCREMENT = 3;

    /**
     * Prepares the canvas to be started at startState
     * @param startState the state to start at
     */
    public void startAt(byte startState);

    /**
     * Changes the current state to the new given one
     * @param newState the state to change to
     */
    public abstract void changeStateTo(byte newState);

    /**
     * Initialize this app.
     * <br>Sets the main screen for this app and all the elements.
     * <br>Main screen has EXIT action when KEY_EXIT
     */
    public void mainScreen(String string, Vector mainScreenItems);

    /**
     * Adds a new screen at position only if not previously inserted.
     * @param title the canvas title, must be not null and not ""
     * @param items the matrix of FSuperItems that the screen has,
     * must be not null and not be empty
     * @param actions the FInteraction controling user actions,
     * must be not null and not be empty
     * @param statePosition the constant that will reffer this screen.
     * @see removeScreen
     */
    public void addScreen(String title, Vector elements,
            byte statePosition);

    /**
     * Deletes a screen from this app.
     * Note that its better not to have uncontinuos screens, use it to
     * free a position and use it again.
     * @param statePosition the position that references the screen
     * @see addScreen
     */
    public void removeScreen();

    public Vector getItems();

    public String getTitle();

    /**
     * Prepare the FCanvas for the current state.
     * Loads title and all elements to use with this screen <br>
     * Set fullScreenMode to true
     */
    public FCanvas prepareCanvas();

    /**
     * Process all data before exit this aplication.
     * Stops threads, clean memory, save user data, close connections, ...
     * MUST NOT call to FMIDlet.notifyDestroy()
     */
    public void closeClean();

}