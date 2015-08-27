package modelview;

import controller.ILController;

/**
 * Created by nattelog on 15-06-24.
 */
public interface ModelView
{
    public void attachController(final ILController controller);
    public ILController getController();
}
