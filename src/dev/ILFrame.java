package dev;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by nattelog on 15-03-15.
 */
public class ILFrame extends JFrame
{

    // The size of the frame
    static final int FRAME_WIDTH = 640;
    static final int FRAME_HEIGHT = 400;
    private final String TITLE = "Illustrator Lite - Test";

    private JFrame frame;

    public ILFrame() {

        // Creating a new frame
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        createToolBar();

        // Finalizing the frame
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    private void createToolBar(){
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        JButton circleButton = new JButton("Circle");

        toolBar.add(circleButton);
        toolBar.setFloatable(false);
        frame.add(toolBar);
    }
}
