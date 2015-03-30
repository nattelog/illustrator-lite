package dev;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILAction
{
    private Canvas canvas;

    public ILAction(final Canvas canvas) {
	this.canvas = canvas;
    }

    public ActionListener setUserState(final UserState state){
	return new AbstractAction(){
	    @Override public void actionPerformed(final ActionEvent e) {
		canvas.setUserState(state);
	    }
	};
    }
}
