package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class SelectionBox extends ILVector
{
    public SelectionBox() {
	super(0, 0, 0, 0);
	setFillColor(Color.BLUE);
    }

    @Override public void draw(final Graphics g) {
	// SelectionBox will not be a filled box,
	// so fillColor is actually strokeColor.
	g.setColor(fillColor);
	g.drawRect(x, y, width, height);
    }
}
