package dev;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nattelog on 15-03-19.
 */
public class Canvas
{
    private ArrayList<Shape> shapes;
    private List<CanvasListener> canvasListeners;

    public Canvas() {
	shapes = new ArrayList<Shape>();
	canvasListeners = new ArrayList<>();
    }

    public ArrayList<Shape> getShapes(){
	return shapes;
    }

    public void addShape(Shape shape){
	shapes.add(shape);
	notifyListeners();
    }

    public void addCanvasListener(CanvasListener cl){
	canvasListeners.add(cl);
    }

    private void notifyListeners(){
	canvasListeners.forEach(CanvasListener::canvasChanged);
    }
}
