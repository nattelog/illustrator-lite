package vector;

import filehandler.svg.ILAttributeConvertionException;
import filehandler.svg.dom.ElementType;
import filehandler.svg.dom.ILDOMElement;

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

    @Override public ILDOMElement generateSVGElement() {
        ILDOMElement element = new ILDOMElement("rect", ElementType.CLOSED);
        element.addAttribute("x", getX());
        element.addAttribute("y", getY());
        element.addAttribute("width", getWidth());
        element.addAttribute("height", getHeight());
        element.addAttribute("fill", colorToCSS(getFillColor()));
        return element;
    }

    @Override public void setAttributes(final ILDOMElement element) throws ILAttributeConvertionException {

	Color fill;
	int x = element.findAttribute("x").valueToInt();
	int y = element.findAttribute("y").valueToInt();
	int width = element.findAttribute("width").valueToInt();
	int height = element.findAttribute("height").valueToInt();

	try {
	    fill = element.findAttribute("fill").valueToColor();

	} catch (ILAttributeConvertionException e) {
	    System.out.println("WARNING: Element '" + element.getName() + "' has no 'fill' attribute. Replacing with color #000.");
	    System.out.println(e.getMessage());
	    fill = Color.BLACK;
	}

	move(x, y);
	resize(width, height);
	setFillColor(fill);
    }
}
