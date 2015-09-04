package modelview.vector;
import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public interface Vector
{
    int getX();
    int getY();
    int getSelectionWidth();
    int getSelectionHeight();
    Color getStrokeColor();
    Color getFillColor();
    Boolean isSelected();
    void select();
    void deselect();
    public void draw(final Graphics g);
    void drawSelectionBox(final Graphics g);
    String debug();
    String debugPosition();
    String debugSelection();
}
