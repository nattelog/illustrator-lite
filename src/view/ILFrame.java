package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    public ILFrame(final String title) throws HeadlessException {
	super(title);

	setLayout(new MigLayout("insets 0"));
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	setVisible(true);
    }
}
