package dev;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nattelog on 15-03-19.
 */
public class Canvas
{
    static final int FRAME_WIDTH = 640;
    static final int FRAME_HEIGHT = 400;

    private ArrayList<Shape> shapes;
    private List<CanvasListener> canvasListeners;

    public Canvas() {
	shapes = new ArrayList<Shape>();
	canvasListeners = new ArrayList<CanvasListener>();
    }

    public ArrayList<Shape> getShapes(){
	return shapes;
    }

    public void addShape(Shape shape){
	shapes.add(shape);
	System.out.println("Added shape: " + shape);
	System.out.println("Canvas now have " + shapes.size() + " shapes");
	notifyListeners();
    }

    public void addCanvasListener(CanvasListener cl){
	canvasListeners.add(cl);
	System.out.println("canvasListeners now have " + canvasListeners.size() + " listeners");
    }

    private void notifyListeners(){
	canvasListeners.forEach(CanvasListener::canvasChanged);
    }
}
