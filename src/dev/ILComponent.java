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

    @SuppressWarnings("RefusedBequest") @Override public Dimension getPreferredSize() {
    	return new Dimension(ILFrame.getFrameWidth(), ILFrame.getFrameHeight());
    }

    @Override protected void paintComponent(final Graphics g){
	super.paintComponent(g);
	for (Shape shape : canvas.getShapes()) {
	    shape.draw(g);
	}
     }

    @Override public void canvasChanged() {
	this.repaint();
    }
}
