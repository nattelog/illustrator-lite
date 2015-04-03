package dev;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


/**
 * Created by nattelog on 15-03-19.
 */
public class Canvas
{

    private ArrayList<Shape> shapes;
    private List<CanvasListener> canvasListeners;

    // The canvas can be in several userstates
    private UserState userState;

    public Canvas() {
	shapes = new ArrayList<>();
	canvasListeners = new ArrayList<>();
        userState = UserState.SELECT;
    }

    public Iterable<Shape> getShapes(){
	return shapes;
    }

    public void addShape(Shape shape){
        Console.msg("adding " + shape);
	shapes.add(shape);
	Console.msg("Canvas: " + shapes.size() + " shapes & " + canvasListeners.size() + " listeners");
	notifyListeners();
    }

    public void addCanvasListener(CanvasListener cl){
	canvasListeners.add(cl);
        Console.msg("Canvas: " + shapes.size() + " shapes & " + canvasListeners.size() + " listeners");
    }

    private void notifyListeners(){
	canvasListeners.forEach(CanvasListener::canvasChanged);
    }

    public void setUserState(final UserState state){
        userState = state;
        Console.msg(userState);
    }

    public UserState getUserState(){
        return userState;
    }
}
