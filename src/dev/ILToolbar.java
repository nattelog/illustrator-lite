package dev;

import javax.swing.*;
import java.awt.*;

import static dev.UserState.*;

/**
 * Created by nattelog on 15-03-30.
 */
public class ILToolbar extends JToolBar
{
    private JButton circleButton;
    private JButton rectangleButton;
    private ILAction action;

    public ILToolbar(final ILAction action) {
	super(JToolBar.VERTICAL);
	this.action = action;

	addButtons();
	setListeners();

	setFloatable(false);
	setBackground(Color.WHITE);
    }

    private void addButtons(){
	circleButton = new JButton("Circle");
	rectangleButton = new JButton("Rectangle");
	add(circleButton);
	add(rectangleButton);
    }

    // Adding listeners so when the user clicks these buttons they
    // will set the user state.
    private void setListeners(){
	circleButton.addActionListener(action.setUserState(CIRCLE));
	rectangleButton.addActionListener(action.setUserState(RECTANGLE));
    }
}
