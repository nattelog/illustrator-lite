package controller;

import modelview.ILDebug;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.State.*;

/**
 * Created by nattelog on 15-06-26.
 */
public class ILController implements Controller
{
    private State state;
    private Boolean debug;
    private ILDebug debugController;

    public ILController() {
        this.state = SELECT;
        this.debug = false;
        this.debugController = ILDebug.getInstance();
    }

    @Override public State getState() {
        return state;
    }

    @Override public void setState(final State state) {
        this.state = state;
        debugController.msg(this.state);
    }

    public ActionListener setUserState(final State state){
    	return new AbstractAction(){
    	    @Override public void actionPerformed(final ActionEvent e) {
                setState(state);
    	    }
    	};
    }

    @Override public void debugMode(final Boolean value) {
        this.debug = value;
    }

    @Override public Boolean debugOn() {
        return debug;
    }


}
