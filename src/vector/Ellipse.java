package vector;

import filehandler.svg.ILAttributeConvertionException;
import filehandler.svg.dom.ElementType;
import filehandler.svg.dom.ILDOMElement;

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

    @Override public ILDOMElement generateSVGElement() {
        ILDOMElement element = new ILDOMElement("ellipse", ElementType.CLOSED);
        element.addAttribute("cx", getX() + getWidth()/2);
        element.addAttribute("cy", getY() + getHeight()/2);
        element.addAttribute("rx", getWidth()/2);
        element.addAttribute("ry", getHeight()/2);
        element.addAttribute("fill", colorToCSS(getFillColor()));
        return element;
    }


    /**
     *	Sets attributes for this vector.
     */
    @Override public void setAttributes(final ILDOMElement element) throws ILAttributeConvertionException
    {
	Color fill;
	int x = element.findAttribute("cx").valueToInt();
	int y = element.findAttribute("cy").valueToInt();
	int width = element.findAttribute("rx").valueToInt();
	int height = element.findAttribute("ry").valueToInt();

	try {
	    fill = element.findAttribute("fill").valueToColor();

	} catch (ILAttributeConvertionException e) {
	    System.out.println("WARNING: Element '" + element.getName() + "' has no 'fill' attribute. Replacing with color #000.");
	    System.out.println(e.getMessage());
	    fill = Color.BLACK;
	}

	move(x - width, y - height);
	resize(width * 2, height * 2);
	setFillColor(fill);
    }
}
