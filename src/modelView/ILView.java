package modelview;

import controller.*;

import javax.swing.*;

/**
 * Created by nattelog on 15-06-24.
 */
public abstract class ILView extends JComponent implements ModelView
{
    protected ILController controller;

    public ILView() {
	this.controller = null;
    }

    @Override public void attachController(final ILController controller) {
	this.controller = controller;
    }

    @Override public ILController getController() {
        return controller;
    }
}
