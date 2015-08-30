package controller;

import modelview.ILDebug;
import modelview.canvas.ILCanvasVectorList;
import modelview.vector.Vector;

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
    private Color inputStrokeColor, inputFillColor;

    private ILCanvasVectorList vectors;

    public ILController() {
        this.state = SELECT;
        this.debug = false;
        this.debugController = ILDebug.getInstance();

        this.inputWidth = 0;
        this.inputHeight = 0;
        this.inputRadius = 0;
        this.inputText = "";
        this.inputStrokeColor = Color.BLACK;
        this.inputFillColor = Color.BLACK;

        this.vectors = new ILCanvasVectorList();
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

    public ILCanvasVectorList getVectors() {
        return vectors;
    }

    public ActionListener showVectors(){
        return new AbstractAction(){
            @Override public void actionPerformed(final ActionEvent e) {
                vectors.debugVectorList();
            }
        };
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

    public Color getInputStrokeColor() {
        return inputStrokeColor;
    }

    public Color getInputFillColor() {
        return inputFillColor;
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

    public void setInputStrokeColor(final Color inputColor) {
        this.inputStrokeColor = inputColor;
    }

    public void setInputFillColor(final Color inputFillColor) {
        this.inputFillColor = inputFillColor;
    }
}
