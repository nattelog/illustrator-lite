package dev;

import javax.swing.*;

/**
 * Created by nattelog on 15-03-30.
 */
public class ILDialog
{

    // Error messages when user input is invalid
    private static String ONLY_POS_NUM_ERROR = "You can only use positive number.\n";

    private static JLabel errorLabel;
    private ILFrame frame;

    public ILDialog(final ILFrame frame) {
	this.frame = frame;
	errorLabel = new JLabel();
    }

    // Inner class
    private class DialogItem {
	private String description;
	private int value;

	public DialogItem(final String description, final int value) {
	    this.description = description;
	    this.value = value;
	}

	public String getDescription() {
	    return description;
	}

	public int getValue() {
	    return value;
	}
    }

    public int getRadius(){
	JPanel panel = new JPanel();
	int radius = 0;
	JTextField radiusField = new JTextField(5);
	String sRadius = "";

	// Adding description and an input field for the dialog
	panel.add(errorLabel);
	panel.add(new JLabel("Radius:"));
	panel.add(radiusField);

	while (true) {
	    try {
		JOptionPane.showConfirmDialog(null, panel, "Circle radius", JOptionPane.OK_CANCEL_OPTION);
		if (radiusField.equals("")) break;

		radius = Integer.parseInt(sRadius);
		if (radius < 0) throw new NumberFormatException();
		else break;
	    } catch (NumberFormatException e){
		errorLabel.setText(ONLY_POS_NUM_ERROR);
	    }
	}
	errorLabel.setText("");
	return radius;
    }
}
