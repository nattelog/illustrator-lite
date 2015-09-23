package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-13.
 */
public class Rectangle extends ILVector
{
    public Rectangle(final int x, final int y, final int width, final int height) {
	super(x, y, width, height);
	setType(VectorType.RECTANGLE);
    }

    @Override public void draw(final Graphics g) {
	g.setColor(getFillColor());
	g.fillRect(x, y, width, height);
    }
}
