package toolbar;

import canvas.ClickMode;
import canvas.ILCanvas;
import net.miginfocom.swing.MigLayout;

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

	JButton selectButton = new JButton("Select");
	JButton ellipseButton = new JButton("Ellipse");
	JButton rectangleButton = new JButton("Rectangle");
	JButton textButton = new JButton("Text");

	selectButton.addActionListener(setClickMode(ClickMode.SELECT));
	ellipseButton.addActionListener(setClickMode(ClickMode.ELLIPSE));
	rectangleButton.addActionListener(setClickMode(ClickMode.RECTANGLE));
	textButton.addActionListener(setClickMode(ClickMode.TEXT));

	add(selectButton, "width 100");
	add(rectangleButton, "width 100, wrap");
	add(ellipseButton, "width 100");
	add(textButton, "width 100");
    }

    private ActionListener setClickMode(final ClickMode clickMode){
    	return new AbstractAction(){
    	    @Override public void actionPerformed(final ActionEvent e) {
    		canvas.setClickMode(clickMode);
    	    }
    	};
    }
}
