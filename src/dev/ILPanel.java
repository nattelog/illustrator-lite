package dev;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-04-03.
 */
public class ILPanel extends JPanel
{

    public ILPanel(final int row, final int column) {
	setLayout(new GridLayout(row, column));
    }

    public void add(ILInput item){
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(1, 2));
	panel.add(new JLabel(item.getDescription() + ":"));
	panel.add(item.getValueField());
	add(panel);
    }

    public Boolean isValidInput(){
	for (Component c : getComponents()) {
	    final ILInput ilInput = (ILInput) c;
	}
    }
}
