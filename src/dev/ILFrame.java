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

    private Canvas canvas;
    private ILAction action;
    private ILComponent component;
    private JLabel status;

    public ILFrame(final Canvas canvas) {
        super("Illustrator Lite - Test");
        // Creating a new frame
        //frame = new JFrame(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.canvas = canvas;
        createComponent();
        action = new ILAction(canvas);
        createToolBar();

        // Finalizing the frame
        pack();
        setVisible(true);
    }

    public static int getFrameWidth() {
        return FRAME_WIDTH;
    }

    public static int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    private void createComponent(){
        component = new ILComponent(canvas);
        System.out.println("createComponent: component = " + component);
        canvas.addCanvasListener(component);
        add(component, BorderLayout.CENTER);
    }

    private void createToolBar(){
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(action.drawCircle);

        toolBar.add(circleButton);
        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.NORTH);
    }

    private void createStatusBar(){
        status = new JLabel("Status: SELECT");
    }
}