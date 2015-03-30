package dev;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-03-15.
 */
public class ILFrame extends JFrame
{

    // The size of the frame
    static final int FRAME_WIDTH = 640;
    static final int FRAME_HEIGHT = 400;
    private static final String TITLE = "Illustrator Lite - Test";

    private Canvas canvas;
    private ILAction action;
    private ILComponent canvasComponent;

    public ILFrame(final Canvas canvas) {
        super(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.canvas = canvas;
        action = new ILAction(canvas);

        // Adding toolbar
        add(new ILToolbar(action), BorderLayout.WEST);

        // Adding canvasComponent
        canvasComponent = new ILComponent(canvas);
        canvas.addCanvasListener(canvasComponent);
        canvasComponent.addMouseListener(action);
        add(canvasComponent, BorderLayout.CENTER);

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
}