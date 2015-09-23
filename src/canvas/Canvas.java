package canvas;

import vector.Vector;

/**
 * Created by nattelog on 15-09-11.
 */
public interface Canvas
{
    void addVector(final Vector vector);
    Vector getVector(final int index);
    Selection getSelection();
    ClickMode getClickMode();
    void setClickMode(final ClickMode clickMode);
    void saveCanvas();
    void openCanvas();
}
