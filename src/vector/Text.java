package vector;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-09-13.
 */
public class Text extends ILVector
{
    private static final int DEFAULT_SIZE = 12;

    private String value;
    private int size;

    public Text(final int x, final int y) {
	super(x, y, 0, 0);
	value = "";
	size = DEFAULT_SIZE;
        setType(VectorType.TEXT);
    }

    // Shows an input dialog where the user inputs the Text-vector's value.
    public boolean setValue(final JComponent parentComponent){
	String inputValue = JOptionPane.showInputDialog(parentComponent, "Input text", "Input", JOptionPane.PLAIN_MESSAGE);
        if (inputValue != null && !inputValue.isEmpty()) {
	    value = inputValue;
	    return true;

	} else
	    return false;
    }

    public int getSize(){
	return size;
    }

    public void setSize(final int size){
	if (size < 1)
	    this.size = 1;
	else
	    this.size = size;
    }

    private void initDimensions(final Graphics g) {
	FontMetrics fm = g.getFontMetrics();
	resize(fm.stringWidth(value), fm.getHeight());
    }

    @Override public void draw(final Graphics g) {
	g.setFont(new Font("serif", Font.PLAIN, size));
	initDimensions(g);
	g.setColor(getFillColor());
	g.drawString(value, x, y + height);
    }


}
