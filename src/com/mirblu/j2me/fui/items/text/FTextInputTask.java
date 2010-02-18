/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirblu.j2me.fui.items.text;

import com.mirblu.j2me.fui.items.image.FCanvas;
import com.mirblu.j2me.fui.utils.Utils;
import java.util.TimerTask;

/**
 * Controls cursor blink.
 * @author mirblu S.L.
 */
public class FTextInputTask extends TimerTask {

    public FTextInputTask() {
        super();
    }

    public void run() {
        if (FTextInput.blink) {
            FTextInput.blink = false;
        } else if (!FTextInput.blink) {
            FTextInput.blink = true;
        }
        Utils.callRepaint(
                0,
                FTextInput.cursorY - FTextInput.cursorH - 10,
                FCanvas.canvasWidth,
                FTextInput.cursorY + FTextInput.cursorH + 10);
    }
}

