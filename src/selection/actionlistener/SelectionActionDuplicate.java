package selection.actionlistener;

import canvas.ILVectorException;
import selection.ILSelection;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class SelectionActionDuplicate extends AbstractSelectionAction
{
    public SelectionActionDuplicate(final ILSelection selection) {
	super(selection);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	try {
	    selection.duplicate();

	} catch (ILVectorException ex) {
	    System.out.println(ex.getMessage());
	}
    }
}
