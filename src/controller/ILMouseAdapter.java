package controller;

import controller.Controller;

import javax.swing.event.MouseInputAdapter;

/**
 * Created by nattelog on 15-07-04.
 */
public abstract class ILMouseAdapter extends MouseInputAdapter
{
    protected Controller controller;

    public ILMouseAdapter(final Controller controller) {
	this.controller = controller;
    }
}
