package filehandler.svg.dom;

import filehandler.svg.ILAttributeConvertionException;
import java.awt.*;

/**
 * Created by nattelog on 2015-12-22.
 */
public class ILDOMAttribute
{
    private String name;
    private String value;

    public ILDOMAttribute(final String name, final String value) {
	this.name = name;
	this.value = value;
    }

    public ILDOMAttribute(final String name, final int value) {
	this.name = name;
	this.value = String.valueOf(value);
    }

    public String getName() {
	return name;
    }


    public int valueToInt() throws ILAttributeConvertionException
    {
	String msg;

	try {
	    return (int) Float.parseFloat(value);

	} catch (NumberFormatException e) {
	    msg = e.getMessage();
	}

	throw new ILAttributeConvertionException("Could not convert to int:" + msg, name);
    }


    public Color valueToColor() throws ILAttributeConvertionException
    {
	String msg;

	try {
	    return Color.decode(value);

	} catch (NumberFormatException e) {
	    msg = e.getMessage();
	}

	throw new ILAttributeConvertionException("Could not convert to Color:" + msg, name);
    }

    @Override public String toString() {
	return name + "=\"" + value + "\"";
    }
}
