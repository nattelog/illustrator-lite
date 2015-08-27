package modelview.vector;

import java.awt.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class Text extends ILVector
{
    private String text;

    public Text(final int x, final int y, final String text, final Color color) {
	super(x, y, color);
	this.text = text;
    }

    @Override public void draw(final Graphics g) {
	g.setColor(color);
	g.drawString(text, x, y);
    }
}
