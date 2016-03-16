package filehandler.svg;

import filehandler.svg.dom.ILDOMElement;

/**
 * Created by nattelog on 2015-12-22.
 */
public interface SVGVectorHandler
{
    ILDOMElement generateSVGElement();
    void setAttributes(final ILDOMElement element) throws ILAttributeConvertionException;
}
