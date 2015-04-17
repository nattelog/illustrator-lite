package dev;

import javax.naming.NotContextException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-04-03.
 */
public class ILPanel extends JPanel
{
    private List<ILInput> list;
    private Boolean errorReported;

    public ILPanel(final int row, final int column) {
	list = new ArrayList<>();
	errorReported = false;
	setLayout(new GridLayout(row, column));
    }

    public void add(ILInput item){
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(1, 2));
	panel.add(new JLabel(item.getDescription() + ":"));
	panel.add(item.getValueField());
	add(panel);
	list.add(item);
    }

    public Boolean setPosNumInput(){
	for (ILInput item : list)
	    if (!item.setPosNumValue())
		return false;
	return true;
    }

    public void addErrorLabel(final String errorMsg){
	if (!errorReported) {
	    add(new JLabel(errorMsg));
	    errorReported = true;
	}
    }

    public Object getInputValue(final String description) throws NotContextException {
	for (ILInput item : list)
	    if (item.getDescription() == description)
		return item.getValue();
	throw new NotContextException();
    }
}
