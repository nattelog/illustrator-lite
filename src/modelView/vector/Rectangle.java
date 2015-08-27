package modelview.vector;

import modelview.ILDebug;

import java.awt.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class Rectangle extends ILVector
{
    private int width, height;

    public Rectangle(final int x, final int y, final int width, final int height, final Color color) {
    	super(x, y, color);
    	if (width < 0 || height < 0) {
	    ILDebug.getInstance().msg("Width or height can't be negative!");
	    throw new IllegalArgumentException("Width or height can't be negative!");
	}
    	this.width = width;
	this.height = height;
    }

    @Override public void draw(final Graphics g) {
    	g.setColor(color);
    	g.drawRect(x, y, width, height);
    }
}
