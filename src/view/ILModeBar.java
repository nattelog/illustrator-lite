package view;

import canvas.ILCanvas;
import canvas.actionlistener.CanvasActionEllipseMode;
import canvas.actionlistener.CanvasActionRectangleMode;
import canvas.actionlistener.CanvasActionSelectMode;
import canvas.actionlistener.CanvasActionTextMode;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 2016-01-02.
 */
public class ILModeBar extends JToolBar
{
    public ILModeBar(final ILCanvas canvas) {
	setOrientation(HORIZONTAL);
	setFloatable(false);
	setRollover(true);

	setLayout(new MigLayout("insets 0"));
	setBackground(Color.WHITE);

	JButton selectButton = new JButton("Select");
	JButton rectangleButton = new JButton("Rectangle");
	JButton ellipseButton = new JButton("Ellipse");
	JButton textButton = new JButton("Text");

	selectButton.addActionListener(new CanvasActionSelectMode(canvas));
	rectangleButton.addActionListener(new CanvasActionRectangleMode(canvas));
	ellipseButton.addActionListener(new CanvasActionEllipseMode(canvas));
	textButton.addActionListener(new CanvasActionTextMode(canvas));

	add(selectButton, "width 65");
	add(rectangleButton, "width 65, wrap");
	add(ellipseButton, "width 65");
	add(textButton, "width 65");
    }
}
