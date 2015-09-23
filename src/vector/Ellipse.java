package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class Ellipse extends ILVector
{
    public Ellipse(final int x, final int y, final int width, final int height) {
	super(x, y, width, height);
	setType(VectorType.ELLIPSE);
    }

    @Override public void draw(final Graphics g) {
	g.setColor(fillColor);
	g.fillOval(x, y, width, height);
    }
}
