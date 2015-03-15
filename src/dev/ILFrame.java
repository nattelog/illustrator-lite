package dev;

import javax.swing.*;

/**
 * Created by nattelog on 15-03-15.
 */
public class ILFrame extends JFrame
{

    // The size of the frame
    final int frameWidth = 640;
    final int frameHeight = 400;

    private JFrame frame;

    public ILFrame(final String title) {
	super(title);
        frame = new JFrame(title);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }
}
