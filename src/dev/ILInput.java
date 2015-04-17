package dev;

import javax.swing.*;

/**
 * Created by nattelog on 15-04-03.
 */
public class ILInput
{

    private String description;
    private JTextField valueField;
    private Object value;

    public ILInput(final String description) {
	this.description = description;
	this.valueField = new JTextField();
    }

    public String getDescription() {
   	    return description;
   	}

    public JTextField getValueField() {
   	    return valueField;
   	}

    public String getValueFieldContent(){
	return valueField.getText();
    }

    public Object getValue() {
	return value;
    }

    // Returns the parsed, positive numeric value from valueFied
    // Returns -1 if input is not valid
    private int getPosNumValue(){
	try {
	    return Integer.parseUnsignedInt(getValueFieldContent());
	}
	catch (NumberFormatException e) {
	    Console.msg(e.getMessage());
	    return -1;
	}
    }

    public Boolean setPosNumValue(){
	int val = getPosNumValue();
	if (val != -1) {
	    value = val;
	    return true;

	} else return false;
    }
}
