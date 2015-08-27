package controller;

import modelview.ILDebug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.State.*;

/**
 * Created by nattelog on 15-06-26.
 */
public class ILController
{
    private State state;
    private Boolean debug;
    private ILDebug debugController;
    private int inputWidth, inputHeight, inputRadius;
    private String inputText;
    private Color inputColor;

    public ILController() {
        this.state = SELECT;
        this.debug = false;
        this.debugController = ILDebug.getInstance();

        this.inputWidth = 0;
        this.inputHeight = 0;
        this.inputRadius = 0;
        this.inputText = "";
        this.inputColor = Color.BLACK;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
        debugController.msg(this.state);
    }

    public ActionListener setUserState(final State state){
    	return new AbstractAction(){
    	    @Override public void actionPerformed(final ActionEvent e) {
                setState(state);
    	    }
    	};
    }

    public void debugMode(final Boolean value) {
        this.debug = value;
    }

    public Boolean debugOn() {
        return debug;
    }

    public int getInputWidth() {
        return inputWidth;
    }

    public int getInputHeight() {
        return inputHeight;
    }

    public int getInputRadius() {
        return inputRadius;
    }

    public String getInputText() {
        return inputText;
    }

    public Color getInputColor() {
        return inputColor;
    }

    public void setInputWidth(final int inputWidth) {
        this.inputWidth = inputWidth;
    }

    public void setInputHeight(final int inputHeight) {
        this.inputHeight = inputHeight;
    }

    public void setInputRadius(final int inputRadius) {
        this.inputRadius = inputRadius;
    }

    public void setInputText(final String inputText) {
        this.inputText = inputText;
    }

    public void setInputColor(final Color inputColor) {
        this.inputColor = inputColor;
    }
}
