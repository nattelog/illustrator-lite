package modelview;

import controller.*;

import javax.swing.*;

/**
 * Created by nattelog on 15-06-24.
 */
public abstract class ILView extends JComponent implements ModelView
{
    protected Controller controller;

    public ILView() {
	this.controller = null;
    }

    @Override public void attachController(final Controller controller) {
	this.controller = controller;
    }

    @Override public Controller getController() {
        return controller;
    }
}
