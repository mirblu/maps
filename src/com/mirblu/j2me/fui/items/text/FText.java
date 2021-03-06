/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mirblu.j2me.fui.items.text;

import com.mirblu.j2me.fui.action.FInteractionUtils;
import com.mirblu.j2me.fui.app.FApp;
import com.mirblu.j2me.fui.items.FSuperItem;
import com.mirblu.j2me.fui.items.image.FCanvas;
import com.mirblu.j2me.fui.items.menu.FHoverMenu;
import com.mirblu.j2me.fui.utils.Utils;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * TODO not tested and not implemented
 * A dialog box with a message and a pair of buttons
 * @author mirblu S.L.
 */
public class FText extends FSuperItem {

    public static final char newLineChar = '^';
    public static final int  SCROLLBAR_WIDTH = 7;

    public static String msg;
    private static int textColor;
    private static int backgroundColor;
    private static Font font;
    public static Vector lines;
    public static int linesToFitCanvas;
    public static int firstLine;
    private static int currH, currW, maxH, maxW;
    private static int startx, starty;
    private static boolean firstTime;
    private static boolean scrollOn;

    /**
     *
     * @param message
     * @param description
     * @param textColor
     * @param backgroundColor
     * @param textFont
     * @param anchor
     */
    public FText(String message, String description,
            int textColor, int backgroundColor, Font textFont, int startx, int starty, int maxWidth, int maxHeigh, int anchor) {
        super(message);
        firstTime = true;
        scrollOn = false;
        maxH = maxHeigh;
        maxW = maxWidth;
        currH = maxHeigh - starty;
        currW = maxWidth - startx;
        FText.font = textFont;
        msg = message;
        lines = Utils.lines(msg, textFont, SCROLLBAR_WIDTH + 2, currW, y, newLineChar);
        linesToFitCanvas = linesToFitCanvas();
        this.description = description;
        FText.textColor = textColor;
        FText.backgroundColor = backgroundColor;
        FText.startx = startx;
        FText.starty = starty;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        g.setColor(textColor);
        if (firstTime) {
            //bottom limit
            if (FApp._theCanvas.hasHoverMenu()) {
                currH -= FHoverMenu.hoverMenuHeight;
            }
            //upper limit
            if (starty < FCanvas.starty) {
                currH = currH + starty - FCanvas.starty;
                starty = FCanvas.starty;
            }
            linesToFitCanvas = linesToFitCanvas();
            firstTime = false;
        }
        paint(g, firstLine, firstLine + linesToFitCanvas);
        paintScrollBar(g);
    }

    public boolean execute(byte action) {
        System.out.println("FText execute action: " + action);
        switch (action) {
            case FInteractionUtils.ACTION_SCROLL_UP:
                firstLine++;
                if (firstLine > lines.size() - linesToFitCanvas) {
                    firstLine--;
                } else {
                    y = starty;
                    Utils.callRepaint();
                }
                break;
            case FInteractionUtils.ACTION_SCROLL_DOWN:
                firstLine--;
                if (firstLine < 0) {
                    firstLine++;
                } else {
                    y = starty;
                    Utils.callRepaint();
                }
                break;
        }
        return true;
    }

    /**
     * Paints each line needed limited by title heigh and hoverMenu heigh
     * @param g
     * @param firstLine
     * @param lastLine
     */
    private void paint(Graphics g, int firstLine, int lastLine) {
        if (firstLine > -1 && firstLine <= lastLine && lastLine <= lines.size()) {
            int i = firstLine;
            y = starty;
            String s;
            //System.out.println(firstLine + ".." + lastLine);
            for (; i < lastLine; i++) {
                s = (String) lines.elementAt(i);
                //System.out.println(y + ", " + i + ": " + s);
                g.drawString(s, x + SCROLLBAR_WIDTH + 1, y, anchor);
                y += font.getHeight();
            }
        }
    }

    private static final int linesToFitCanvas() {
        int i = 0;
        int y = starty;
        for (; i < lines.size(); i++) {
            if ((y + font.getHeight()) > currH) {
                break;
            }
            y += font.getHeight();
        }
        System.out.println("linesToFitCanvas " + i + " currH " + currH);
        return i-1;
    }

    /**
     * Paints a scroll bar to the left of the canvas
     * @param g
     */
    private void paintScrollBar(Graphics g) {
        if (linesToFitCanvas < lines.size() ) {
            int maxScrollHeight = y - (font.getHeight() << 1);
            int oneLineHeight = maxScrollHeight  / lines.size();
            g.setColor(Utils.COLOR_TANGO_ALUMINIUM1);
            //background bar
            g.fillRect(0, starty, SCROLLBAR_WIDTH, maxScrollHeight);
            //foreground bar
            g.setColor(FApp._theMIDlet.mainTextColor);
            g.fillRect(1, starty - 2, SCROLLBAR_WIDTH - 1, 2);//upper
            g.fillRect(1, starty + maxScrollHeight - 2, SCROLLBAR_WIDTH - 1, 2);//bottom
            g.fillRect(1, starty + (oneLineHeight * firstLine ),
                    SCROLLBAR_WIDTH - 1, (maxScrollHeight * linesToFitCanvas) / lines.size() );
        }
    }

}