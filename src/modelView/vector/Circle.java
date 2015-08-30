package modelview.vector;

import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class Circle extends ILVector
{
    private int radius;

    public Circle(final int x, final int y, final int radius, final Color strokeColor, final Color fillColor) {
	super(x, y, strokeColor, fillColor);
	if (radius < 1)
	    throw new IllegalArgumentException("Radius can't be less than 1. Found radius = " + radius);
	this.radius = radius;
    }

    @Override public void draw(final Graphics g) {
	g.setColor(strokeColor);
	g.drawOval(x, y, radius, radius);
	if (isSelected())
	    drawSelectionBox(g);
    }

    @Override public String debug() {
	return "CIRCLE" + "\t" +
	       debugPosition() + "\t" +
	       debugSelection() + "\t" +
	       "Radius: " + radius;
    }

    @Override public int getSelectionWidth() {
	return radius;
    }

    @Override public int getSelectionHeight() {
	return radius;
    }
}