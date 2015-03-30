package dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by nattelog on 15-03-15.
 */
public class ILFrame extends JFrame implements MouseListener
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
        canvasComponent.addMouseListener(this);
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

    // Depending on its state, when a user clicks on the
    // the canvas an ILAction is called
    @Override public void mouseClicked(final MouseEvent e) {
        System.out.println("mouseclick: (" + e.getX() + ", " + e.getY() + ")");
        int x = e.getX();
        int y = e.getY();
        Color color = Color.BLACK;

        switch (canvas.getUserState()) {
            case CIRCLE:
                int radius = 10;
                canvas.addShape(new Circle(x, y, radius, color));
                break;

            case RECTANGLE:
                int width = 10;
                int height = 10;
                canvas.addShape(new Rectangle(x, y, width, height, color));
                break;

            default:
                System.out.println("The current state " +  canvas.getUserState() + " is not defined in mouseClicked.");
                break;
        }
    }

    @Override public void mousePressed(final MouseEvent e) {

    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }
}