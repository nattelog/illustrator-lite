package dev;

import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class Circle extends AbstractShape
{
    private int radius;

    public Circle(final int x, final int y, final int radius, final Color color) {
	super(x, y, color);
	if (radius < 0)
	    throw new IllegalArgumentException("Radius can't be negative. Found radius = " + radius);
	this.radius = radius;
    }

    @Override public void draw(final Graphics g) {
	g.setColor(color);
	g.drawOval(x, y, radius, radius);
    }
}
