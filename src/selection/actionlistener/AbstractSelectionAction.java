package selection.actionlistener;

import selection.ILSelection;

import java.awt.event.*;

/**
 * Created by nattelog on 2016-01-02.
 */
abstract class AbstractSelectionAction implements ActionListener
{
    protected ILSelection selection;

    AbstractSelectionAction(final ILSelection selection) {
	this.selection = selection;
    }
}
