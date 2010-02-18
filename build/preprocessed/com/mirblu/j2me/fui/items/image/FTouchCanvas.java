/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mirblu.j2me.fui.items.image;

/**
 * A touch FCanvas
 * @author mirblu S.L.
 */
public class FTouchCanvas extends FCanvas{

    public FTouchCanvas() {
        super();
        checkPointerCapabilities();
    }

    private void checkPointerCapabilities() {
        hasPointerEvents();
        hasPointerMotionEvents();
    }



}
