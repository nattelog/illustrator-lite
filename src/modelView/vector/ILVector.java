package modelview.vector;

import modelview.*;
import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public abstract class ILVector extends ILModel implements Vector
{
    protected int x;
    protected int y;
    protected Color color;

    public ILVector(final int x, final int y, final Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override public Color getColor() {
        return this.color;
    }

    @Override public int getY() {
        return this.y;
    }

    @Override public int getX() {
        return this.x;
    }
}
