package controller;

import javax.swing.event.MouseInputAdapter;

/**
 * Created by nattelog on 15-07-04.
 */
public abstract class ILMouseAdapter extends MouseInputAdapter
{
    protected ILController controller;

    public ILMouseAdapter(final ILController controller) {
	this.controller = controller;
    }
}
