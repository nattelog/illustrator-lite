package dev;

import javax.swing.*;

/**
 * Created by nattelog on 15-03-15.
 */
public class ILFrame extends JFrame
{
    private JFrame frame;
    private int width;
    private int height;

    public ILFrame(final String title, final int width, final int height) {
	super(title);
	this.width = width;
	this.height = height;
	frame = new JFrame(title);
    }
}
