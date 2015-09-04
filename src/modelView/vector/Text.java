package modelview.vector;

import modelview.ILDebug;

import java.awt.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class Text extends ILVector
{
    private String text;
    private Font font;
    private FontMetrics fm;

    public Text(final int x, final int y, final String text, final Color strokeColor, final Color fillColor, final Font font) {
	super(x, y, strokeColor, fillColor);
	this.text = text;
        this.font = font;
        this.fm = null;
    }

    @Override public void draw(final Graphics g) {
        g.setFont(font);
        fm = g.getFontMetrics(font);
	g.setColor(strokeColor);
	g.drawString(text, x, y + fm.getHeight());
        if (isSelected())
            drawSelectionBox(g);
    }

    @Override public String debug() {
        return "TEXT" + "\t\t" +
               debugPosition() + "\t" +
               debugSelection();
    }

    @Override public int getSelectionWidth() {
        if (fm == null) {
            ILDebug.getInstance().msg("There is no FontMetrics initialized for this Text-instance.");
            return 0;
        } else
            return fm.stringWidth(text);
    }

    @Override public int getSelectionHeight() {
        if (fm == null) {
            ILDebug.getInstance().msg("There is no FontMetrics initialized for this Text-instance.");
            return 0;
        } else
            return fm.getHeight();
    }
}
