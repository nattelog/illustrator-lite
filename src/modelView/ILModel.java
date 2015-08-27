package modelview;

import controller.ILController;

/**
 * Created by nattelog on 15-06-24.
 */
public abstract class ILModel implements ModelView
{
    protected ILController controller;

    public ILModel() {
	this.controller = null;
    }

    @Override public void attachController(final ILController controller) {
        this.controller = controller;
    }

    @Override public ILController getController() {
        return controller;
    }
}
