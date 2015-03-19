package dev;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-03-19.
 */
public class ILComponent extends JComponent implements CanvasListener
{
    private Canvas canvas;

    public ILComponent(final Canvas canvas){
	this.canvas = canvas;
    }

    @Override public void paintComponent(final Graphics g){
	super.paintComponent(g);
	System.out.println("paintComponent: before loop");
	for (Shape shape : canvas.getShapes()) {
	    System.out.println("paintComponent: calling draw() with shape: " + shape);
	    shape.draw(g);
	}
     }

    @Override public void canvasChanged() {
	System.out.println("canvasChanged: trying to repaint..");
	this.repaint();
    }
}
