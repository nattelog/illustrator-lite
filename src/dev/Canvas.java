package dev;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


/**
 * Created by nattelog on 15-03-19.
 */
public class Canvas
{
    static final int FRAME_WIDTH = 640;
    static final int FRAME_HEIGHT = 400;

    private ArrayList<Shape> shapes;
    private List<CanvasListener> canvasListeners;

    // The canvas can be in several userstates
    private UserState userState;

    public Canvas() {
	shapes = new ArrayList<Shape>();
	canvasListeners = new ArrayList<>();
        userState = UserState.SELECT;
    }

    public Iterable<Shape> getShapes(){
	return shapes;
    }

    public void addShape(Shape shape){
	shapes.add(shape);
	System.out.println("Canvas: " + shapes.size() + " shapes & " + canvasListeners.size() + " listeners");
	notifyListeners();
    }

    public void addCanvasListener(CanvasListener cl){
	canvasListeners.add(cl);
        System.out.println("Canvas: " + shapes.size() + " shapes & " + canvasListeners.size() + " listeners");
    }

    private void notifyListeners(){
	canvasListeners.forEach(CanvasListener::canvasChanged);
    }

    public void setUserState(final UserState state){
        userState = state;
        System.out.println("userState: " + userState);
    }

    public UserState getUserState(){
        return userState;
    }
}
