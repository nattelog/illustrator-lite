package view.menu;

import canvas.ILCanvas;
import canvas.actionlistener.CanvasActionEllipseMode;
import canvas.actionlistener.CanvasActionRectangleMode;
import canvas.actionlistener.CanvasActionSelectMode;
import canvas.actionlistener.CanvasActionTextMode;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class ILCanvasMenu extends JMenu
{
    public ILCanvasMenu(final ILCanvas canvas) {
	setText("Canvas");
	setMnemonic(KeyEvent.VK_C);

	JMenuItem selectMode = new JMenuItem("Select");
	JMenuItem rectangleMode = new JMenuItem("Rectangle");
	JMenuItem ellipseMode = new JMenuItem("Ellipse");
	JMenuItem textMode = new JMenuItem("Text");

	selectMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0));
	selectMode.addActionListener(new CanvasActionSelectMode(canvas));

	rectangleMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));
	rectangleMode.addActionListener(new CanvasActionRectangleMode(canvas));

	ellipseMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));
	ellipseMode.addActionListener(new CanvasActionEllipseMode(canvas));

	textMode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0));
	textMode.addActionListener(new CanvasActionTextMode(canvas));

	add(selectMode);
	add(rectangleMode);
	add(ellipseMode);
	add(textMode);
    }
}
