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
    private JLabel status;

    public ILFrame(final Canvas canvas) {
        super(TITLE);

        // Creating a new frame
        //frame = new JFrame(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.canvas = canvas;
        action = new ILAction(canvas);

        createCanvasComponent();
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

    private void createCanvasComponent(){
        canvasComponent = new ILComponent(canvas);
        System.out.println("createComponent: component = " + canvasComponent);
        canvas.addCanvasListener(canvasComponent);
        canvasComponent.addMouseListener(this);
        add(canvasComponent, BorderLayout.CENTER);
    }

    private void createToolBar(){
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(action.setUserState(UserState.CIRCLE));

        toolBar.add(circleButton);
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.WHITE);
        add(toolBar, BorderLayout.WEST);
    }

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