package canvas;

import vector.Vector;
import vector.VectorType;

import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public interface Selection
{
    Vector getVector();
    VectorType getType();
    Vector getSelectionBox();
    Vector getResizeBox();
    boolean duplicate();
    void delete();
    void move(final int x, final int y);
    void resize(final int width, final int height);
    void changeFillColor(final Color color);
    void select(final int x, final int y);
    void select(final int index);
    void deselect();
    boolean isActive();
    void addListener(final SelectionListener listener);
    void draw(final Graphics g);
}
