package dev;

import javax.naming.NotContextException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-03-30.
 */
public class ILDialog extends JOptionPane
{
    private ILFrame frame;

    public ILDialog(final ILFrame frame) {
	this.frame = frame;
    }

    private int openDialog(final ILPanel panel, final String title){
	// If user pressed ok
	return showConfirmDialog(null, panel, title, OK_CANCEL_OPTION);
    }

    public Boolean getRectangleProperties(int width, int height) {
	ILInput widthInput = new ILInput("Width");
	ILInput heightInput = new ILInput("Height");
	ILPanel panel = new ILPanel(0, 1);

	panel.add(widthInput);
	panel.add(heightInput);

	while (true) {
	    if (openDialog(panel, "Rectangle") == OK_OPTION) {
		if (panel.setPosNumInput())
		    break;
		else panel.addErrorLabel("You can only use a positive number!");
	    }
	}

	try {
	    width = (int) panel.getInputValue("Width");
	    Console.msg("width " + width);
	    height = (int) panel.getInputValue("Height");
	    Console.msg("height " + height);
	    return true;

	}
	catch (NotContextException e){
	    Console.msg(e.getMessage());
	    return false;
	}
    }

    public int getRadius(){
	int radius = 0;
	return radius;
    }

    // Inner class used when several inputs are required
    // in one dialog
    private class InputList {
	private List<ILInput> list;

	public InputList(){
	    list = new ArrayList<>();
	}

	public void add(final ILInput item){
	    list.add(item);
	}

	public void clear(){
	    list.clear();
	}

	public List<ILInput> getList(){
	    return list;
	}
    }
}
