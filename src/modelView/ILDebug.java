package modelview;

import modelview.canvas.ILCanvasVectorList;
import modelview.component.ILDialog;
import modelview.vector.Vector;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-04-03.
 */
public final class ILDebug
{
    public static final ILDebug INSTANCE = new ILDebug();

    private StackTraceElement[] stack;
    private Object message;
    private List<LabelListener> labels;

    private ILDebug() {
        stack = null;
        message = null;
        labels = new ArrayList<>();
    }

    public static ILDebug getInstance(){
        return INSTANCE;
    }

    // Returns the name of the method of the caller
    private String callerInfo(){
	stack = Thread.currentThread().getStackTrace();

	// Returns the name of caller of whoever called this
	// method, i.e. index 3
	return stack[3].getMethodName();
    }

    public Object getMessage(){
        return message;
    }

    public void msg(final Object message){
        this.message = callerInfo() + ": " + message;
        notifyListeners();
    }

    public void listVectors(final ILCanvasVectorList vectors){
        ILDialog dialog = new ILDialog();
        JPanel panel = new JPanel();

        panel.setLayout(new MigLayout());

        dialog.openDialog(panel, "Drawn vectors");
    }

    public void addLabelListener(final LabelListener listener){
        labels.add(listener);
    }

    private void notifyListeners(){
	labels.forEach(LabelListener::labelChanged);
    }
}
