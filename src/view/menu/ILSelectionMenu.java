package view.menu;

import selection.ILSelection;
import selection.SelectionListener;
import selection.actionlistener.SelectionActionColor;
import selection.actionlistener.SelectionActionDelete;
import selection.actionlistener.SelectionActionDuplicate;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class ILSelectionMenu extends JMenu implements SelectionListener
{
    private JMenuItem duplicate, delete, color;

    public ILSelectionMenu(final ILSelection selection) {
	setText("Selection");
	setMnemonic(KeyEvent.VK_S);
	selection.addListener(this);

	duplicate = new JMenuItem("Duplicate");
	delete = new JMenuItem("Delete");
	color = new JMenuItem("Color");

	duplicate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
	duplicate.addActionListener(new SelectionActionDuplicate(selection));

	delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
	delete.addActionListener(new SelectionActionDelete(selection));

	color.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0));
	color.addActionListener(new SelectionActionColor(selection));

	add(duplicate);
	add(delete);
	add(color);

	enableItems(false);
    }

    private void enableItems(final boolean enabled) {
	duplicate.setEnabled(enabled);
	delete.setEnabled(enabled);
	color.setEnabled(enabled);
    }

    @Override public void selectionActivated() {
	enableItems(true);
    }

    @Override public void selectionDeactivated() {
	enableItems(false);
    }

    @Override public void vectorChanged() {}
}
