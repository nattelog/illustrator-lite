package dev;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by nattelog on 15-03-30.
 */
public class ILDialog extends JOptionPane
{
    private ILFrame frame;

    // When in use of several inputs in one dialog
    // a stack is needed to store them
    private Stack inputStack;

    public ILDialog(final ILFrame frame) {
	this.frame = frame;
	inputStack = new Stack();
    }

    private Boolean openDialog(final ILPanel panel, final String title){
	// If user pressed ok
	if (showConfirmDialog(null, panel, title, OK_CANCEL_OPTION) == OK_OPTION) {

	  return true;

	} else return false;
    }

    public Boolean getRectangleProperties(){
	int width, height;
	ILInput widthInput = new ILInput("Width");
	ILInput heightInput = new ILInput("Height");
	ILPanel panel = new ILPanel(0, 1);

	panel.add(widthInput);
	panel.add(heightInput);

	return openDialog(panel, "Rectangle");
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
