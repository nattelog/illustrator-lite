package dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILAction implements MouseListener
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

    @Override public void mouseClicked(final MouseEvent e) {
	System.out.println("mouseclick: (" + e.getX() + ", " + e.getY() + ")");

	        int x = e.getX();
	        int y = e.getY();
	        Color color = Color.BLACK;

	        switch (canvas.getUserState()) {
	            case CIRCLE:
	                int radius = 10;
	                canvas.addShape(new Circle(x, y, radius, color));
	                break;

	            case RECTANGLE:
	                int width = 10;
	                int height = 10;
	                canvas.addShape(new Rectangle(x, y, width, height, color));
	                break;

	            default:
	                System.out.println("The current state " +  canvas.getUserState() + " is not defined in mouseClicked.");
	                break;
	        }
    }

    @Override public void mousePressed(final MouseEvent e) {

    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }
}
