package controller;

import java.awt.event.ActionListener;

/**
 * Created by nattelog on 15-06-24.
 */
public interface Controller
{
    State getState();
    public void setState(final State state);
    ActionListener setUserState(final State select);
    public void debugMode(final Boolean value);
    public Boolean debugOn();
}
