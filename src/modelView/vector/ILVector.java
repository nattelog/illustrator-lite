package modelview.vector;

import controller.ILMouseAdapter;
import modelview.*;
import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public abstract class ILVector extends ILView implements Vector
{
    protected int x;
    protected int y;
    protected Color strokeColor;
    protected Color fillColor;
    protected Boolean selected;

    public ILVector(final int x, final int y, final Color strokeColor, final Color fillColor) {
        this.x = x;
        this.y = y;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.selected = false;
    }

    @Override public Color getStrokeColor() {
        return this.strokeColor;
    }

    @Override public Color getFillColor() {
        return this.fillColor;
    }

    @Override public int getY() {
        return this.y;
    }

    @Override public int getX() {
        return this.x;
    }

    @Override public Boolean isSelected() {
        return this.selected;
    }

    @Override public void select() {
        this.selected = true;
    }

    @Override public void deselect() {
        this.selected = false;
    }

    @Override public String debugPosition() {
        return "(" + x + ", " + y + ")";
    }

    @Override public String debugSelection() {
        return "Selected: " + isSelected();
    }

    @Override public void drawSelectionBox(final Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(x, y, getSelectionWidth(), getSelectionHeight());
    }
}
