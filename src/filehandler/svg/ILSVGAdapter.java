package filehandler.svg;

import canvas.ILCanvas;
import canvas.ILVectorList;
import filehandler.svg.dom.ILDOMElement;
import filehandler.svg.dom.ILDOMParser;
import filehandler.svg.dom.ILParseException;
import vector.Ellipse;
import vector.Rectangle;
import vector.Text;
import vector.Vector;

import java.io.InvalidObjectException;

/**
 * Created by nattelog on 2015-12-21.
 */
public class ILSVGAdapter
{
    private ILCanvas canvas;

    public ILSVGAdapter(final ILCanvas canvas) {
	this.canvas = canvas;
    }


    /**
     *	Converts @vectorList to an ILDOMElement and returns it.
     */
    public ILDOMElement createSVGTreeFromVectorList()
    {
	ILDOMElement svgTree = new ILDOMElement("svg");

	svgTree.addAttribute("width", (int) canvas.getSize().getWidth());
	svgTree.addAttribute("height", (int) canvas.getSize().getHeight());

	for (Vector vector : canvas.getVectorList().getVectorList()) {
	    svgTree.addChild(vector.generateSVGElement());
	}

	return svgTree;
    }


    /**
     *	Converts @tree to an ILVectorList and returns it.
     */
    public ILVectorList createVectorListFromSVGTree(final ILDOMElement tree) throws InvalidObjectException
    {
	/* Make sure @tree has root named 'svg'. */
	if (!tree.getName().equals("svg")) {
	    throw new InvalidObjectException("@tree must have root named 'svg'. Aborting.");
	}

	ILVectorList vectorList = new ILVectorList();

	System.out.println("Converting vectors...");

	/* Loop through all children and tries to convert them to vectors. */
	for (ILDOMElement child : tree.getChildren()) {
	    Vector vector = convertToVector(child);

	    if (vector != null) {
		vectorList.add(vector);
	    }
	}

	if (vectorList.getVectorList().isEmpty()) {
	    throw new InvalidObjectException("Converted list is empty!");
	}

	System.out.println("Successfully converted " + vectorList.getVectorList().size() + " vectors!");

	return vectorList;
    }


    /**
     *	Converts @child to a supported Vector. If @child isn't supported,
     *	returns null.
     */
    private Vector convertToVector(final ILDOMElement child)
    {
	Vector vector;

	/* Initialize @vector to the corresponding shape found in child. */
	switch (child.getName()) {
	    case "rect":
		vector = new Rectangle(0, 0, 0, 0);
		break;

	    case "ellipse":
		vector = new Ellipse(0, 0, 0, 0);
		break;

	    case "text":
		Text text = new Text(0, 0);
		text.setValue(child.getChildren().get(0).getName());
		vector = text;
		break;

	    default:
		System.out.println("Element '" + child.getName() + "' is not a supported vector.");
		return null;
	}

	/* Try to convert attributes found in @child. */
	try {
	    vector.setAttributes(child);
	    return vector;

	} catch (ILAttributeConvertionException e) {
	    System.out.println("CONVERTION ERROR for element '" + child.getName() + "' and attribute '" + e.getAttributeName() + "':\n  " + e.getMessage());
	    return null;
	}
    }


    /**
     * 	Takes an input string and turn it into a ILDOMElement-object. Throws an
     * 	ILParseException if there are any errors.
     */
    public ILDOMElement parseDOMFile(final String input) throws ILParseException
    {
	ILDOMParser parser = new ILDOMParser(input);

	/* Start by making sure all '<'/'>'-characters adds up. */
	parser.validateTree();

	/* Start parsing from the first <svg>-element. */
	parser.goToElement("svg");

	/* Start a recursive process. */
	return getElement(parser.getElement());
    }


    /**
     *	Expects @input to start with an element tag. Returns the element
     *	including its children in an ILDOMElement object. @input should not
     *	have siblings.
     */
    private ILDOMElement getElement(final String input) throws ILParseException
    {
	ILDOMParser parser = new ILDOMParser(input);

	/* Make sure @input starts with an element. If the element is open,
	make sure @input ends with the correct closing tag. */
	parser.validateInput();

	ILDOMElement element = new ILDOMElement(parser.getElementName());

	/* Add all attributes. */
	while (parser.nextAttribute()) {
	    element.addAttribute(parser.getAttributeName(), parser.getAttributeValue());
	}

	/* Add all children if there exist any. */
	if (parser.elementHasChildren()) {

	    parser.nextElement();

	    while (true) {
		ILDOMElement child = getElement(parser.getElement());
		element.addChild(child);

		if (!parser.nextChild()) {
		    break;
		}
	    }

	/* If there are no children but content, add it. */
	} else if (parser.elementHasContent()) {
	    element.addChild(parser.getContent());
	}

	return element;
    }
}
