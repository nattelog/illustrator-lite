package selection.actionlistener;

import canvas.ILVectorException;
import selection.ILSelection;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class SelectionActionDelete extends AbstractSelectionAction
{
    public SelectionActionDelete(final ILSelection selection) {
	super(selection);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	try {
	    selection.delete();

	} catch (ILVectorException ex) {
	    System.out.println(ex.getMessage());
	}
    }
}
