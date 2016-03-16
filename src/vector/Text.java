package vector;

import filehandler.svg.ILAttributeConvertionException;
import filehandler.svg.dom.ILDOMElement;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-09-13.
 */
public class Text extends ILVector
{
    private static final int DEFAULT_SIZE = 12;

    private String value;
    private float size;
    private Font font;

    public Text(final int x, final int y) {
	super(x, y, 0, 0);
	value = "";
	size = DEFAULT_SIZE;
	font = new Font("serif", Font.PLAIN, (int) size);
        setType(VectorType.TEXT);
    }

    // Shows an input dialog where the user inputs the Text-vector's value.
    public boolean setValueFromInput(final JComponent parentComponent){
	String inputValue = JOptionPane.showInputDialog(parentComponent, "Input text", "Input", JOptionPane.PLAIN_MESSAGE);
        if (inputValue != null && !inputValue.isEmpty()) {
	    value = inputValue;
	    return true;

	} else
	    return false;
    }

    public String getValue() {
	return value;
    }

    public void setValue(final String value) {
	this.value = value;
    }

    public float getSize(){
	return size;
    }

    public void setSize(final float size){
	//System.out.println("setSize::size " + size);
	this.size = size < 1 ? 1 : size;
    }


    /**
     * 	Resize on text doesn't behave like it does on the other vectors.
     * 	Since @width and @height are set in initDimensions(), they should not
     * 	be set here.
     *
     * 	Inspection issue here as well. The reason the super-method isn't called
     * 	is because that will change @widht and @height which will mess with
     * 	this method.
     * */
    @Override public void resize(final int width, final int height) {
	float scale, ratio = (float) getWidth() / getHeight();

	/* Height should be changed by a specific amount max. */
	float maxHeightIncrease = width / ratio;

	/* If height is too small, height decide size. */
	if (height < maxHeightIncrease) {

	    /* Decrease size. */
	    if (height < getHeight()) {
		scale = 1 - (float) (getHeight() - height) / getHeight();

		/* Increase size. */
	    } else {
		scale = 1 + (float) (height - getHeight()) / getHeight();
	    }

	    /* Else, width decide size. */
	} else {

	    /* Decrease size. */
	    if (width < getWidth()) {
		scale = 1 - (float) (getWidth() - width) / getWidth();

		/* Increase size. */
	    } else {
		scale = 1 + (float) (width - getWidth()) / getWidth();
	    }

	}

	setSize(scale * size);
    }


    /**
     * 	The text's @width and @height values are set each time the vector is
     * 	redrawn. This is due to the need of the Graphics-variable to get the
     * 	width and height of the string @value represents, therefore this
     * 	function is called inside draw().
     * */
    private void initDimensions(final Graphics g) {
	FontMetrics fm = g.getFontMetrics();
	this.width = fm.stringWidth(value);
	this.height = fm.getHeight();
    }

    @Override public void draw(final Graphics g) {
	g.setFont(font.deriveFont(size));
	initDimensions(g);
	g.setColor(getFillColor());
	g.drawString(value, x, y + height);
    }

    @Override public ILDOMElement generateSVGElement() {
	ILDOMElement element = new ILDOMElement("text");
	element.addAttribute("x", getX());
	element.addAttribute("y", getY());
	element.addAttribute("fill", colorToCSS(getFillColor()));
	element.addAttribute("font-size", (int) size);
	element.addChild(value);
	return element;
    }

    @Override public void setAttributes(final ILDOMElement element) throws ILAttributeConvertionException
    {
	int x = element.findAttribute("x").valueToInt();
	int y = element.findAttribute("y").valueToInt();
	int size = element.findAttribute("font-size").valueToInt();
	Color fill = element.findAttribute("fill").valueToColor();

	move(x, y);
	setSize(size);
	setFillColor(fill);
    }
}
