package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-10.
 */
abstract class ILVector implements Vector
{
    protected int x, y, width, height;
    protected Color fillColor;
    protected VectorType type;

    ILVector(final int x, final int y, final int width, final int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.fillColor = Color.BLACK;
        this.type = VectorType.UNDEFINED;
    }

    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public void move(final int x, final int y) {
        if (x < 0)
            this.x = 0;
        else
            this.x = x;
        if (y < 0)
            this.y = 0;
        else
            this.y = y;
    }

    @Override public int getWidth() {
	return width;
    }

    @Override public int getHeight() {
	return height;
    }

    @Override public void resize(final int width, final int height) {
        if (width < 1)
            this.width = 1;
        else
	    this.width = width;
        if (height < 1)
            this.height = 1;
        else
	    this.height = height;
    }

    @Override public boolean contains(final int x, final int y) {
        boolean xInBounds = x >= this.x && x <= this.x + this.width;
        boolean yInBounds = y >= this.y && y <= this.y + this.height;
        return xInBounds && yInBounds;
    }

    @Override public Color getFillColor() {
	return fillColor;
    }

    @Override public void setFillColor(final Color fillColor) {
	this.fillColor = fillColor;
    }

    @Override public VectorType getType() {
	return type;
    }

    public void setType(final VectorType type) {
        this.type = type;
    }

    @Override public String toString() {
        return type + "\t=>" +
               "\tX: " + x +
               "\tY: " + y +
               "\tW: " + width +
               "\tH: " + height;
    }
}
