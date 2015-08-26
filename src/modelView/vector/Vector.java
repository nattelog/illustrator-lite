package modelview.vector;

import modelview.ModelView;

import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public interface Vector
{
    int getX();
    int getY();
    Color getColor();
    public void draw(final Graphics g);
}
