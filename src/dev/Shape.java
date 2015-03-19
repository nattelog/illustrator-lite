package dev;

import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public interface Shape
{
    int getX();
    int getY();
    Color getColor();
    public void draw(final Graphics g);
}
