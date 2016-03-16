package selection.actionlistener;

import canvas.ILVectorException;
import selection.ILSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class SelectionActionColor extends AbstractSelectionAction
{
    public SelectionActionColor(final ILSelection selection) {
	super(selection);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	try {
	    Color newColor = JColorChooser.showDialog(null, "Choose Background Color", selection.getVector().getFillColor());
	    if (newColor != null) {
		selection.changeFillColor(newColor);
	    }

	} catch (ILVectorException ex) {
	    System.out.println(ex.getMessage());
	}
    }
}
