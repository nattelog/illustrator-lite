package view;

import canvas.ILVectorException;
import net.miginfocom.swing.MigLayout;
import selection.ILSelection;
import selection.SelectionListener;
import vector.Text;
import vector.Vector;
import vector.VectorType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 *  A JToolBar consisting only of input-fields where the user can change
 *  properties of the selected vector.
 */
public class ILSelectionPropertyBar extends JToolBar implements SelectionListener
{
    private ILSelection selection;
    private JTextField xField, yField, wField, hField, x2Field, y2Field, sField;

    /* Views will toggle depending on what type of vector is selected. */
    private ILView coordinatesAndDimensionView, coordinatesAndFontSizeView;

    /* Used to synchronize read/write to the textfields. */
    private boolean allowUpdate;


    public ILSelectionPropertyBar(final ILSelection selection) {
        this.selection = selection;
        this.selection.addListener(this);

        setOrientation(HORIZONTAL);
        setFloatable(false);
        setRollover(true);

        setLayout(new MigLayout("insets 0"));
        setBackground(Color.WHITE);

        xField = new JTextField();
        yField = new JTextField();
        wField = new JTextField();
        hField = new JTextField();
        x2Field = new JTextField();
        y2Field = new JTextField();
        sField = new JTextField();

        /* This view will have input for coordinates (x and y) and dimension
        (width and height). */
        coordinatesAndDimensionView = new ILView("insets 0");
        coordinatesAndDimensionView.add(makeInput("X:", xField, new InputListenerMoveX()));
        coordinatesAndDimensionView.add(makeInput("Y:", yField, new InputListenerMoveY()));
        coordinatesAndDimensionView.add(makeInput("W:", wField, new InputListenerResizeWidth()));
        coordinatesAndDimensionView.add(makeInput("H:", hField, new InputListenerResizeHeight()));

        /* This view will have input for coordinates and font size. */
        coordinatesAndFontSizeView = new ILView("insets 0");
        coordinatesAndFontSizeView.add(makeInput("X:", x2Field, new InputListenerMoveX()));
        coordinatesAndFontSizeView.add(makeInput("Y:", y2Field, new InputListenerMoveY()));
        coordinatesAndFontSizeView.add(makeInput("S:", sField, new InputListenerResizeFont()));

        allowUpdate = true;
    }


    /**
     *  Creates a panel with a label and an input field that is restricted to
     *  input integers only.
     */
    private JPanel makeInput(final String name, JTextField field, final DocumentListener documentListener) {
    	ILView panel = new ILView("insets 0");
    	JLabel label = new JLabel(name);
    	Font font = new Font("Sans-serif", Font.PLAIN, 10);

    	/* Make sure only integers can be typed in the input. */
    	AbstractDocument document = (AbstractDocument) field.getDocument();
    	document.setDocumentFilter(new FieldInputFilter());
    	document.addDocumentListener(documentListener);

    	label.setFont(font);
    	field.setFont(font);

    	panel.add(label);
    	panel.add(field, "width 40");

    	return panel;
    }


    /**
     *  A filter that only allows integers as input.
     */
    private class FieldInputFilter extends DocumentFilter {
        final static int INPUT_MAX_SIZE = 4;

        @Override public void replace(final FilterBypass fb, final int offset, final int length, final String text,
                                      final AttributeSet attrs) throws BadLocationException
        {
            if (text.matches("\\d*") && fb.getDocument().getLength() <= INPUT_MAX_SIZE) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }


    /**
     *   Updates the values of the textfields.
     */
    private void updateTextFields() {
        try {
            Vector vector = selection.getVector();

            xField.setText(String.valueOf(vector.getX()));
            yField.setText(String.valueOf(vector.getY()));
            x2Field.setText(String.valueOf(vector.getX()));
            y2Field.setText(String.valueOf(vector.getY()));

            if (vector.getType() == VectorType.TEXT) {
                Text text = (Text) vector;
                sField.setText(String.valueOf((int) text.getSize()));

            } else {
                wField.setText(String.valueOf(vector.getWidth()));
                hField.setText(String.valueOf(vector.getHeight()));
            }

        } catch (ILVectorException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     *  This listener will trigger every time something changes in the parent
     *  textfield, and when it does it will move the x-component of the vector.
     *  However, the user can drag the vector with the mouse and that will
     *  update the textfield as well, and in that case we don't want this
     *  listener to call @selection.move(), since the vector was moved
     *  manually. Therefore, @allowUpdate is used to make sure these
     *  things can't happen at the same time.
     *  This goes for all listener-classes following this one.
     */
    private class InputListenerMoveX implements DocumentListener {

        private void moveX(final Document document) {
            try {
                if (allowUpdate) {
                    allowUpdate = false;
                    int x = parseInt(document);
                    selection.move(x, selection.getVector().getY());
                    allowUpdate = true;
                }

            } catch (ILVectorException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override public void insertUpdate(final DocumentEvent e) {
            moveX(e.getDocument());
        }

        @Override public void removeUpdate(final DocumentEvent e) {
            moveX(e.getDocument());
        }

        @Override public void changedUpdate(final DocumentEvent e) {
            moveX(e.getDocument());
        }
    }


    /**
     *  Moves the y-component of @selection.
     */
    private class InputListenerMoveY implements DocumentListener {

        private void moveY(final Document document) {
            try {
                if (allowUpdate) {
                    allowUpdate = false;
                    int y = parseInt(document);
                    selection.move(selection.getVector().getX(), y);
                    allowUpdate = true;
                }

            } catch (ILVectorException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override public void insertUpdate(final DocumentEvent e) {
            moveY(e.getDocument());
        }

        @Override public void removeUpdate(final DocumentEvent e) {
            moveY(e.getDocument());
        }

        @Override public void changedUpdate(final DocumentEvent e) {
            moveY(e.getDocument());
        }
    }


    /**
     *   Resizes the width-component of @selection.
     */
    private class InputListenerResizeWidth implements DocumentListener {

        private void resizeWidth(final Document document) {
            try {
                if (allowUpdate) {
                    allowUpdate = false;
                    int width = parseInt(document);
                    selection.resize(width, selection.getVector().getHeight());
                    allowUpdate = true;
                }

            } catch (ILVectorException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override public void insertUpdate(final DocumentEvent e) {
            resizeWidth(e.getDocument());
        }

        @Override public void removeUpdate(final DocumentEvent e) {
            resizeWidth(e.getDocument());
        }

        @Override public void changedUpdate(final DocumentEvent e) {
            resizeWidth(e.getDocument());
        }
    }


    /**
     *   Resizes the height-component of @selection.
     */
    private class InputListenerResizeHeight implements DocumentListener {

        private void resizeHeight(final Document document) {
            try {
                if (allowUpdate) {
                    allowUpdate = false;
                    int height = parseInt(document);
                    selection.resize(selection.getVector().getWidth(), height);
                    allowUpdate = true;
                }

            } catch (ILVectorException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override public void insertUpdate(final DocumentEvent e) {
            resizeHeight(e.getDocument());
        }

        @Override public void removeUpdate(final DocumentEvent e) {
            resizeHeight(e.getDocument());
        }

        @Override public void changedUpdate(final DocumentEvent e) {
            resizeHeight(e.getDocument());
        }
    }


    /**
     *   Resizes the fontsize-component of @selection.
     */
    private class InputListenerResizeFont implements DocumentListener {

        private void resizeFont(final Document document) {
            try {
                if (allowUpdate) {
                    allowUpdate = false;
                    int size = parseInt(document);
                    selection.resize(size);
                    allowUpdate = true;
                }

            } catch (ILVectorException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override public void insertUpdate(final DocumentEvent e) {
            resizeFont(e.getDocument());
        }

        @Override public void removeUpdate(final DocumentEvent e) {
            resizeFont(e.getDocument());
        }

        @Override public void changedUpdate(final DocumentEvent e) {
            resizeFont(e.getDocument());
        }
    }


    /**
     *  Tries to parse int found in @document. If it didn't go well, returns
     *  zero.
     */
    private int parseInt(final Document document) {
        try {
            return Integer.parseInt(document.getText(0, document.getLength()));

        } catch (NumberFormatException|BadLocationException e) {
            e.getMessage();
            return 0;
        }
    }


    /**
     *  When @selection gets activated the textfields are updated with the
     *  properties of @selection.
     */
    @Override public void selectionActivated() {
        removeAll();

        try {
            /* Toggle view depending on what kind of vector is selected. */
            if (selection.getVector().getType() == VectorType.TEXT) {
                add(coordinatesAndFontSizeView);

            } else {
                add(coordinatesAndDimensionView);

            }

            revalidate();

        } catch (ILVectorException e) {
            System.out.println(e.getMessage());
        }

        updateTextFields();
    }


    /**
     *  When @selection gets deactivated all propertyviews are removed.
     */
    @Override public void selectionDeactivated() {
        removeAll();
        revalidate();
    }


    /**
     *  When @selection gets changed, the user is either moving the selected
     *  vector manually with the mouse, or changed values in the property-text-
     *  fields. Mainly for the first case we need @allowUpdate so the
     *  documentlisteners doesn't try to update the vector at the same time as
     *  the user is.
     */
    @Override public void vectorChanged() {
        if (allowUpdate) {
            allowUpdate = false;
            updateTextFields();
            allowUpdate = true;
        }
    }
}
