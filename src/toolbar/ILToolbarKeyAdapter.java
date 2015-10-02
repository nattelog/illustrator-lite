package toolbar;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by nattelog on 15-09-12.
 */
public class ILToolbarKeyAdapter extends KeyAdapter
{
    private JTextField textField;
    private String oldContent;
    private ToolbarInputListener inputListener;

    public ILToolbarKeyAdapter(final JTextField textField, final ToolbarInputListener inputListener) {
        this.textField = textField;
        this.oldContent = textField.getText();
        this.inputListener = inputListener;
    }

    @Override public void keyPressed(final KeyEvent e) {
        super.keyPressed(e);
        oldContent = textField.getText();
    }

    @Override public void keyReleased(final KeyEvent e) {
        super.keyReleased(e);
        String newContent = textField.getText();

        if (!newContent.isEmpty()) {
            if (!isvalid(newContent))
                textField.setText(oldContent);
            notifyListeners();
        }
    }

    private Boolean isvalid(final String content){
        try {
            Integer.parseInt(content);
            return true;
        }
        catch(NumberFormatException e){
            System.out.print(e.getMessage() + "\n");
            return false;
        }
    }

    private void notifyListeners(){
        inputListener.toolbarInputChanged();
    }
}