package view;

import net.miginfocom.swing.MigLayout;
import selection.ILSelection;
import selection.SelectionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 2016-01-18.
 */
public class ILSelectionBar extends JToolBar implements SelectionListener
{
    private JButton duplicateButton, deleteButton, colorButton;

    public ILSelectionBar(final ILSelection selection) {
	setFloatable(false);
	setLayout(new MigLayout("insets 0"));
	setBackground(Color.white);

	selection.addListener(this);

	duplicateButton = new JButton("Duplicate");
	deleteButton = new JButton("Delete");
	colorButton = new JButton("Color");

	add(duplicateButton, "width 65");
	add(deleteButton, "width 65, wrap");
	add(colorButton, "width 65");

	enableButtons(false);
    }

    private void enableButtons(final boolean visible) {
	duplicateButton.setVisible(visible);
	deleteButton.setVisible(visible);
	colorButton.setVisible(visible);
    }

    @Override public void selectionActivated() {
	enableButtons(true);
    }

    @Override public void selectionDeactivated() {
	enableButtons(false);
    }

    @Override public void vectorChanged() {

    }
}
