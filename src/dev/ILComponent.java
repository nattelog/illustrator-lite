package dev;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILComponent extends JComponent implements CanvasListener
{
    Canvas canvas;

    public ILComponent(final Canvas canvas){
	this.canvas = canvas;
    }

     @Override public void paintComponent(final Graphics g){
 	super.paintComponent(g);
 	for (Shape shape : canvas.getShapes())
 	    shape.draw(g);
     }

    @Override public void canvasChanged() {
	this.repaint();
    }
}
