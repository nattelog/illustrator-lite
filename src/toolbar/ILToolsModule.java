package toolbar;

import canvas.ClickMode;
import canvas.ILCanvas;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nattelog on 15-09-12.
 */
public class ILToolsModule extends ILToolbarModule
{

    public ILToolsModule(final ILCanvas canvas, final int horizontalSpan) {
	super(canvas, horizontalSpan);
	setTitle("Tools");

	// Setting up one action per mode.
	Action selectMode = new AbstractAction("Select") {
	    @Override public void actionPerformed(final ActionEvent e) {
		canvas.setClickMode(ClickMode.SELECT);
	    }
	};

	Action ellipseMode = new AbstractAction("Ellipse")
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		canvas.setClickMode(ClickMode.ELLIPSE);
	    }
	};

	Action rectangleMode = new AbstractAction("Rectangle")
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		canvas.setClickMode(ClickMode.RECTANGLE);
	    }
	};

	Action textMode = new AbstractAction("Text")
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		canvas.setClickMode(ClickMode.TEXT);
	    }
	};

	ILButton selectButton = new ILButton("Select", selectMode, "V");
	ILButton ellipseButton = new ILButton("Ellipse", ellipseMode, "E");
	ILButton rectangleButton = new ILButton("Rectangle", rectangleMode, "R");
	ILButton textButton = new ILButton("Text", textMode, "T");

	add(selectButton, "width 100");
	add(rectangleButton, "width 100, wrap");
	add(ellipseButton, "width 100");
	add(textButton, "width 100");
    }
}
