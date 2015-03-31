package dev;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-03-30.
 */
public class ILDialog
{

    // Error messages when user input is invalid
    private static String ONLY_POS_NUM_ERROR = "You can only use a positive number.\n";

    private ILFrame frame;

    public ILDialog(final ILFrame frame) {
	this.frame = frame;
    }

    private Boolean openDialog(final InputList inputList, final String title){
	JPanel panel = new JPanel();
	JLabel errorLabel = new JLabel();

	panel.setLayout(new GridLayout(0, 1));

	// Add each panelItem to the panel in the dialog
	for (InputItem item : inputList.getList()){
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new GridLayout(1, 2));

	    subPanel.add(new JLabel(item.getDescription() + ":"));
	    subPanel.add(item.getValueField());

	    panel.add(subPanel);
	}

	// If user pressed ok
	if (JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

	    for (InputItem item : inputList.getList())
	    	if (!checkAndSet(item)) break;

	    return true;

	} else return false;
    }

    private Boolean checkAndSet(InputItem item){
	try {
	    item.setValue(Integer.parseInt(String.valueOf(item.getValueField())));
	    return true;
	}
	catch (NumberFormatException e) {
	    return false;
	}
    }

    public Boolean getRectangleProperties(int width, int height){
	InputList inputList = new InputList();
	InputItem widthItem = new InputItem("Width", width);
	InputItem heightItem = new InputItem("Height", height);

	inputList.add(widthItem);
	inputList.add(heightItem);

	return openDialog(inputList, "Rectangle");
    }

    public int getRadius(){
	int radius = 0;
	return radius;
    }

    // Inner class used to represent an input value with
    // its corresponding description
    private class InputItem {
	private String description;
	private int value;
	private JTextField valueField;

	public InputItem(final String description, final int value) {
	    this.description = description;
	    this.value = value;
	    this.valueField = new JTextField();
	}

	public String getDescription() {
	    return description;
	}

	public int getValue() {
	    return value;
	}

	public JTextField getValueField() {
	    return valueField;
	}

	public void setValue(final int value) {
	    this.value = value;
	}
    }

    // Inner class used when several inputs are required
    // in one dialog
    private class InputList {
	private List<InputItem> list;

	public InputList(){
	    list = new ArrayList<>();
	}

	public void add(final InputItem item){
	    list.add(item);
	}

	public void clear(){
	    list.clear();
	}

	public List<InputItem> getList(){
	    return list;
	}
    }
}
