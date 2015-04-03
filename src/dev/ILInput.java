package dev;

import javax.swing.*;

/**
 * Created by nattelog on 15-04-03.
 */
public class ILInput
{

    // Error messages when user input is invalid
    final static String ONLY_POS_NUM_ERROR = "You can only use a positive number.\n";

    private String description;
    private JTextField valueField;

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

    // Returns the parsed, positive numeric value from valueFied
    // Returns -1 if input is not valid
    public int getPosNumValue(){
	try {
	    return Integer.parseUnsignedInt(getValueFieldContent());
	}
	catch (NumberFormatException e) {
	    Console.msg(e.getMessage());
	    return -1;
	}
    }
}
