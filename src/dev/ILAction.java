package dev;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILAction extends ILFrame
{
    public ActionListener drawCircle = new AbstractAction(){
	@Override public void actionPerformed(final ActionEvent e) {
	    Circle c = new Circle(0, 0, 100, Color.BLACK);
	    addShape(c);
	}
    };
}
