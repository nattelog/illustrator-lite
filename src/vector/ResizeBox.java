package vector;

import filehandler.svg.ILAttributeConvertionException;
import filehandler.svg.dom.ILDOMElement;

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

    @Override public ILDOMElement generateSVGElement() {
        return null;
    }

    @Override public void setAttributes(final ILDOMElement element) throws ILAttributeConvertionException {

    }
}
