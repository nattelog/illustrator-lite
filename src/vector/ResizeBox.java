package vector;

import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class ResizeBox extends ILVector
{
    public ResizeBox() {
	super(0, 0, 10, 10);
	setFillColor(Color.BLUE);
    }

    @Override public void draw(final Graphics g) {
        g.setColor(getFillColor());
        g.fillRect(x, y, width, height);
    }
}
