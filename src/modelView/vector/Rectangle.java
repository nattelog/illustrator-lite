package modelview.vector;

import modelview.ILDebug;

import java.awt.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class Rectangle extends ILVector
{
    private int width, height;

    public Rectangle(final int x, final int y, final int width, final int height, final Color strokeColor, final Color fillColor) {
    	super(x, y, strokeColor, fillColor);
    	if (width < 1 || height < 1) {
	    ILDebug.getInstance().msg("Width or height can't be less than zero!");
	    throw new IllegalArgumentException("Width or height can't be less than zero!");
	}
    	this.width = width;
	this.height = height;
    }

    @Override public void draw(final Graphics g) {
    	g.setColor(strokeColor);
    	g.drawRect(x, y, width, height);
	if (isSelected())
	    drawSelectionBox(g);
    }

    @Override public String debug() {
    	return "RECTANGLE" + "\t" +
    	       debugPosition() + "\t" +
    	       debugSelection() + "\t" +
	       "Width: " + width + "\t" +
	       "Height: " + height;
    }

    @Override public int getSelectionWidth() {
	return width;
    }

    @Override public int getSelectionHeight() {
	return height;
    }
}
