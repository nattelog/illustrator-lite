package dev;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILAction
{
    private Canvas canvas;

    public ILAction(final Canvas canvas) {
	this.canvas = canvas;
    }

    public ActionListener drawCircle = new AbstractAction(){
	@Override public void actionPerformed(final ActionEvent e) {
	    Circle c = new Circle(10, 10, 100, Color.BLACK);
	    canvas.addShape(c);
	}
    };
}
