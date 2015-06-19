package dev;

import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public abstract class AbstractShape implements Shape
{
    protected int x;
    protected int y;
    protected Color color;
    protected UserState type;

    public AbstractShape(final int x, final int y, final Color color) {
	this.x = x;
	this.y = y;
	this.color = color;
    }

    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public Color getColor() {
	return color;
    }

    @Override public String toString() {
	return "AbstractShape{" +
	       "x=" + x +
	       ", y=" + y +
	       ", color=" + color +
	       '}';
    }
}
