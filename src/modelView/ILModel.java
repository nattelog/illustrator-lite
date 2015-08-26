package modelview;

import controller.Controller;

/**
 * Created by nattelog on 15-06-24.
 */
public abstract class ILModel implements ModelView
{
    protected Controller controller;

    public ILModel() {
	this.controller = null;
    }

    @Override public void attachController(final Controller controller) {
        this.controller = controller;
    }

    @Override public Controller getController() {
        return controller;
    }
}
