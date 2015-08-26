package modelview;

import controller.Controller;

/**
 * Created by nattelog on 15-06-24.
 */
public interface ModelView
{
    public void attachController(final Controller controller);
    public Controller getController();
}
