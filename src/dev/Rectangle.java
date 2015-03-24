package dev;

import java.awt.*;

/**
 * Created by nattelog on 15-03-23.
 */
public class Rectangle extends AbstractShape
{
    private int width, height;

    public Rectangle(final int x, final int y, final int width, final int height, final Color color) {
	super(x, y, color);
	this.width = width;
	this.height = height;
    }

    @Override public String toString() {
	return "Rectangle{" +
	       "x=" + x +
	       ", y=" + y +
	       ", width=" + width +
	       ", height=" + height +
	       ", color=" + color +
	       '}';
    }

    @Override public void draw(final Graphics g) {
	g.setColor(color);
	g.drawRect(x, y, width, height);
    }
}
