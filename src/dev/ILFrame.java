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
    private Canvas canvas;
    private ILAction action;
    private ILComponent component;

    public ILFrame(final Canvas canvas) {

        // Creating a new frame
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Creating a new canvas
        this.canvas = canvas;

        createComponent();

        action = new ILAction(canvas);

        createToolBar();

        // Finalizing the frame
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    private void createToolBar(){
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(action.drawCircle);

        toolBar.add(circleButton);
        toolBar.setFloatable(false);
        frame.add(toolBar);
    }

    private void createComponent(){
        component = new ILComponent(canvas);
        canvas.addCanvasListener(component);
        frame.add(component, BorderLayout.CENTER);
    }
}
