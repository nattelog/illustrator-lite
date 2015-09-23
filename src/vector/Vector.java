package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-10.
 */
public interface Vector
{
    int getX();
    int getY();
    void move(final int x, final int y);
    int getWidth();
    int getHeight();
    void resize(final int width, final int height);
    boolean contains(final int x, final int y);
    Color getFillColor();
    void setFillColor(final Color fillColor);
    VectorType getType();
    void setType(final VectorType type);
    void draw(final Graphics g);
}
