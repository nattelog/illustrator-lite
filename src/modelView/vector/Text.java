package modelview.vector;

import java.awt.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class Text extends ILVector
{
    private String text;

    public Text(final int x, final int y, final String text, final Color strokeColor, final Color fillColor) {
	super(x, y, strokeColor, fillColor);
	this.text = text;
    }

    @Override public void draw(final Graphics g) {
	g.setColor(strokeColor);
	g.drawString(text, x, y);
    }

    @Override public String debug() {
        return "TEXT" + "\t\t" +
               debugPosition() + "\t" +
               debugSelection();
    }

    @Override public int getSelectionWidth() {
        return 0;
    }

    @Override public int getSelectionHeight() {
        return 0;
    }
}
