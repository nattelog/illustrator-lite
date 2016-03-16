package filehandler.svg.dom;

import filehandler.svg.ILAttributeConvertionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 2015-12-22.
 */
public class ILDOMElement
{
    private String name;
    private ElementType type;
    private List<ILDOMAttribute> attributes;
    private List<ILDOMElement> children;

    public ILDOMElement(final String name, final ElementType type) {
	this.name = name;
	this.type = type;
	this.attributes = new ArrayList<>();
	this.children = new ArrayList<>();
    }

    public ILDOMElement(final String name) {
    	this.name = name;
    	this.type = ElementType.OPEN;
    	this.attributes = new ArrayList<>();
    	this.children = new ArrayList<>();
    }

    public String getName() {
	return name;
    }

    /**
     *	Returns the first found attribute in @attributes with name @name. If no
     *	attribute was found, returns null.
     */
    public ILDOMAttribute findAttribute(final String name) throws ILAttributeConvertionException
    {
	for (ILDOMAttribute attribute : attributes) {
	    if (attribute.getName().equals(name)) {
		return attribute;
	    }
	}

	throw new ILAttributeConvertionException("Attribute not found.", name);
    }

    public void addAttribute(final String name, final String value) {
	    attributes.add(new ILDOMAttribute(name, value));
    }

    public void addAttribute(final String name, final int value) {
	    attributes.add(new ILDOMAttribute(name, value));
    }

    public List<ILDOMElement> getChildren() {
    	return children;
    }

    public void addChild(final ILDOMElement child) {
	    children.add(child);
    }

    public void addChild(final String content) {
	    children.add(new ILDOMElement(content, ElementType.CONTENT));
    }

    private String toStringRec(final int intendation) {
	StringBuilder intend = new StringBuilder();

	for (int i = 0; i < intendation; i++) {
	    intend.append(" ");
	}

	// If this element is just text.
	if (type == ElementType.CONTENT) {
	    intend.append(name);
	    intend.append('\n');
	    return intend.toString();

	} else {
	    StringBuilder result = new StringBuilder();

	    result.append(intend);
	    result.append('<');
	    result.append(name);

	    // Attributes
	    for (ILDOMAttribute attribute : attributes) {
		result.append(" ");
		result.append(attribute);
	    }

	    // If the element is OPEN, it might have children.
	    if (type == ElementType.OPEN) {
		result.append(">\n");

		for (ILDOMElement child : children) {
		    result.append(child.toStringRec(intendation + 1));
		}

		result.append(intend);
		result.append("</");
		result.append(name);
		result.append(">\n");

		return result.toString();

	    } else {
		// If the element is CLOSED, it won't have any children and it will end
		// with />.
		result.append(" />\n");
		return result.toString();
	    }
	}
    }

    @Override public String toString() {
	return toStringRec(0);
    }
}
