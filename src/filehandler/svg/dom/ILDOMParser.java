package filehandler.svg.dom;

import java.util.Stack;

/**
 *	SOME JAVADOC HERE.
 */
public class ILDOMParser
{
    final static String INVALID_NAME_CHARACTERS = "<> ";
    final static String INVALID_VALUE_CHARACTERS = "\"=";

    private String input;

    /* Used to get the position of the current element in @input. */
    private int index;


    public ILDOMParser(final String input)
    {
	this.input = input;
	this.index = 0;
    }


    /**
     *	Throws an exception if the number of tag-characters in @input doesn't
     *	add up.
     */
    public void validateTree() throws ILParseException
    {
	Stack<Integer> stack = new Stack<>();

	for (int i = 0; i < input.length(); i++) {
	    char c = getChar(i);

	    if (c == '<') {
		stack.push(0);

	    } else if (c == '>') {
		if (stack.size() == 1) {
		    stack.pop();

		} else {
		    throw new ILParseException("Should not find a '>'-character here.", i, input);
		}
	    }
	}

	if (!stack.empty()) {
	    throw new ILParseException("Did not find a '>'-character at the end of the file.");
	}
    }


    /**
     *	 Expects @input to start with an element and if the element is open,
     *	 it should end with a closing tag. If the element is closed there
     *	 should not be any other elements prescent.
     */
    public void validateInput() throws ILParseException
    {
	/* Check if @input starts with an element. */
	if (getChar() != '<') {
	    throw new ILParseException("@index is not on an element.", index, input);
	}

	int closingTagIndex = findClosingTag(), oldIndex = index;

	/* Go to the closing element if there is one. */
	if (closingTagIndex != 0) {
	    goToElement(closingTagIndex);
	}

	if (nextElement()) {
	    throw new ILParseException("There should not be any more elements here.", index, input);
	}

	index = oldIndex;
    }


    /**
     *	Expects @index to be somewhere inside an element-tag. Moves it to the
     *  start-tag.
     */
    private void goToElement() throws ILParseException
    {
	int startTag = input.lastIndexOf('<', index);

	/* Make sure @index is inside an element-tag. */
	if (!(startTag <= index && index <= nextEndTag())) {
	    throw new ILParseException("@index is not inside an element.", index, input);

	} else {
	    goToElement(startTag);
	}
    }


    /**
     *	Sets @index to the first element with name @name found.
     */
    public void goToElement(final String name) throws ILParseException
    {
	int i = input.indexOf("<" + name);

	if (i != -1) {
	    index = i;

	} else {
	    throw new ILParseException("Element '" + name + "' was not found.");

	}
    }


    /**
     *	Sets @this.index to @index. @index must be on a start-tag.
     */
    private void goToElement(final int index) throws ILParseException
    {
	if (index > input.length() - 1) {
	    throw new ILParseException("@index is too big (" + index + ").");

	} else if (getChar(index) != '<') {
	    throw new ILParseException("This is not an element, can't go here.", index, input);

	} else {
	    this.index = index;
	}
    }


    /**
     *	Returns the element @index is pointing at.
     */
    public String getElement() throws ILParseException
    {
	goToElement();

	int closingTagIndex = findClosingTag();

	/* Check if this element is closed. */
	if (closingTagIndex == 0) {
	    int end = getIndex("/>") + 2;
	    return input.substring(index, end);

	} else {
	    int end = closingTagIndex + getElementName().length() + 3;
	    return input.substring(index, end);
	}
    }


    /**
     *	Returns all content between the current element and its closing tag.
     */
    public String getContent() throws ILParseException
    {
	goToElement();

	if (!elementIsOpen()) {
	    return null;

	} else {
	    int start = nextEndTag() + 1;
	    int end = findClosingTag() - 1;

	    return input.substring(start, end).trim();
	}
    }


    /**
     * 	Expects @index to stand on a '<'-character. Returns the name of the DOM element.
     */
    public String getElementName() throws ILParseException
    {
	/* Make sure @index is on a '<'-character. */
	if (getChar() != '<') {
	    throw new ILParseException("@index is not standing on a '<'-character.", index, input);
	}

	StringBuilder result = new StringBuilder();

	for (int i = index + 1; i < input.length(); i++) {
	    char c = getChar(i);

	    if (validNameCharacter(c)) {
		result.append(c);

	    /* The name always ends with a space or a '>'-character. */
	    } else if (c == ' ' || c == '>') {
		break;

	    } else {
		throw new ILParseException("Found a non-valid character in element-name.", i, input);

	    }
	}

	if (result.length() == 0) {
	    throw new ILParseException("Result is empty.");
	}

	return result.toString();
    }


    /**
     * 	Moves @index to the next DOM element in @input. Returns false if no more
     * 	elements could be found.
     */
    public boolean nextElement() throws ILParseException
    {
	if (nextEndTag() == -1) {
	    throw new ILParseException("Could not find any more '>'-characters.", index, input);

	} else if (nextStartTag() == -1) {
	    return false;

	} else if (nextStartTag() < nextEndTag()) {
	    throw new ILParseException("There should not be a '<'-character here.", nextStartTag(), input);

	} else {
	    index = nextStartTag();

	    /* Do this just to make sure there are both a '<'- and a '>'-
	    character here. */
	    goToElement();

	    return true;
	}
    }


    /**
     *	Moves @index to the next DOM element in @input. Returns false if a
     *	closing tag is found that doesn't belong to any element previously
     *	found.
     */
    public boolean nextChild() throws ILParseException
    {
	/* If the current element is open, jump two times. */
	if (elementIsOpen() && getElementName().charAt(0) != '/') {
	    goToElement(findClosingTag());
	}

	/* Make sure it's possible to jump to the next element. */
	if (!nextElement()) {
	    throw new ILParseException("It should be possible to go to any next element while iterating through children.",
				       index, input);
	}

	/* If the element is a closing tag, it must belong to the parent. */
	if (getElementName().charAt(0) == '/') {
	    return false;

	} else {
	    return true;
	}
    }


    /**
     *	Expects @index to stand on the beginning of an attribute name. Returns
     *	the name of the attribute.
     */
    public String getAttributeName() throws ILParseException
    {
	/* Make sure @index is standing on an attribute. */
	if (getChar() != '=') {
	    throw new ILParseException("@index is not on an attribute.", index, input);

	} else if (!validNameCharacter(getChar(index - 1))) {
	    throw new ILParseException("Syntax error in attribute name.", index - 1, input);
	}

	int start = input.lastIndexOf(' ', index) + 1;
	int end = index;

	/* Make sure a ' '-character is found. */
	if (start == 0) {
	    throw new ILParseException("Syntax error in attribute.", index, input);

	} else {
	    String result = input.substring(start, end);

	    if (!validName(result)) {
		throw new ILParseException("Syntax error on this attribute.", index, input);

	    } else {
		return result;
	    }

	}
    }


    /**
     *	Expects @index to stand on an '='-character. Returns the value of the
     *	attribute.
     */
    public String getAttributeValue() throws ILParseException
    {
	/* Make sure @index is on an attribute. */
	if (getChar() != '=') {
	    throw new ILParseException("@index is not on an attribute.", index, input);

	/* Make sure the next character is a '"'-character. */
	} else if (getChar(index + 1) != '"') {
	    throw new ILParseException("Syntax error on this attribute.", index + 1, input);
	}

	int start = index + 2;
	int end = input.indexOf('"', index + 2);

	if (end == -1) {
	    throw new ILParseException("No ending \"-character found for this attribute.", index, input);

	} else if (start == end) {
	    return null;

	} else {
	    String result = input.substring(start, end);

	    if (!validValue(result)) {
		throw new ILParseException("Syntax error on this attribute.", index, input);

	    } else {
		return result;
	    }
	}
    }


    /**
     *	 Moves @index to the next '='-character in @input.
     */
    public boolean nextAttribute()
    {
	int eq = input.indexOf('=', index + 1);

	if (eq != -1 && eq < nextEndTag()) {

	    index = eq;

	    return true;

	} else {
	    return false;
	}
    }


    public boolean elementIsOpen() throws ILParseException
    {
	goToElement();
	return getChar(nextEndTag() - 1) != '/';
    }


    /**
     *	Return true if the current element has any elements as children.
     */
    public boolean elementHasChildren() throws ILParseException
    {
	goToElement();

	if (!elementIsOpen()) {
	    return false;

	} else {
	    String parentName = getElementName();
	    int oldIndex = index;
	    nextElement();

	    /* If the next element is the closing tag of the previous, the
	    element does not have any children. */
	    boolean result = !getElementName().equals("/" + parentName);
	    index = oldIndex;
	    return result;
	}
    }


    /**
     *	Returns true if the current element has any content as children.
     */
    public boolean elementHasContent() throws ILParseException
    {
	goToElement();

	if (!elementIsOpen()) {
	    return false;

	/* Take out the area between the current element and its closing tag.
	Check to see if that string has any length. */
	} else {
	    int start = nextEndTag() + 1;
	    int end = findClosingTag();
	    String content = input.substring(start, end);
	    return !content.trim().isEmpty();
	}
    }


    /**
     *	Returns the index of the closing tag for the current element. Throws an
     *	exception if the tag is not found. If the current element is closed, it
     *	returns 0.
     */
    private int findClosingTag() throws ILParseException
    {
	String name = getElementName();
	int closingTagIndex, oldIndex = index;

	if (!elementIsOpen()) {
	    return 0;
	}

	/* Using @stack to handle nested elements with the same name. */
	Stack<Integer> stack = new Stack<>();
	boolean tagFound = false;

	/* Loop through all elements until the closing tag is found. */
	while (nextElement()) {

	    /* If an element with the same name is found nested, push it to
	    the stack. */
	    if (getElementName().equals(name)) {
		stack.push(0);

	    /* If a closing tag is found with the correct name, make sure it's
	    not a nested tag. */
	    } else if (getElementName().equals("/" + name)) {

		/* If the stack is empty, no previous nested tag with the same
		name has been found, this must be the correct closing tag. */
		if (stack.empty()) {
		    tagFound = true;
		    break;

		} else {
		    stack.pop();
		}
	    }
	}

	if (!tagFound) {
	    throw new ILParseException("Found no closing tag for this element.", oldIndex, input);
	}

	closingTagIndex = index;
	index = oldIndex;

	return closingTagIndex;
    }


    private boolean validNameCharacter(final char character)
    {
	return INVALID_NAME_CHARACTERS.indexOf(character) == -1;
    }


    private boolean validName(final String name)
    {
	for (int i = 0; i < INVALID_NAME_CHARACTERS.length(); i++) {
	    char c = INVALID_NAME_CHARACTERS.charAt(i);
	    if (name.indexOf(c) != -1) {
		return false;
	    }
	}
	return true;
    }


    private boolean validValue(final String value)
    {
	for (int i = 0; i < INVALID_VALUE_CHARACTERS.length(); i++) {
	    char c = INVALID_VALUE_CHARACTERS.charAt(i);
	    if (value.indexOf(c) != -1) {
		return false;
	    }
	}
	return true;
    }


    /**
     *	Since @index always will be standing on either a '<'-character or any
     *	other valid character, the search need to start one step ahead.
     */
    private int nextStartTag()
    {
	return input.indexOf('<', index + 1);
    }


    private int nextEndTag()
    {
	return getIndex('>');
    }


    private char getChar()
    {
	return input.charAt(index);
    }


    private char getChar(final int i)
    {
	return input.charAt(i);
    }


    private int getIndex(final char c)
    {
	return input.indexOf(c, index);
    }


    private int getIndex(final String s)
    {
	return input.indexOf(s, index);
    }
}
