package edu.elon.math;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * CalcView is the GUI for the application. It receives
 * input from the user and communicates with the controller.
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public class CalcView implements Observer, ActionListener {
	
	/** 
	 * Variables for the model and controller interfaces
	 */
	CalcModelInterface model;
	CalcControllerInterface controller;
	
	/**
	 * Variables for certain elements of the GUI
	 */
	JFrame frame; 
	JTextField answer;
	Button equalsButton;
	Button clearButton;
	
	/**
	 * Variable for the current string the user input
	 */
	String currentEq;
	
	/**
	 * Constructor that gives values to the model and controller
	 * and registers the view as an observer of the model. 
	 * 
	 * @param aController
	 * @param aModel
	 */
	public CalcView(CalcControllerInterface aController, CalcModelInterface aModel){
		this.controller = aController;
		this.model = aModel;
		model.registerObserver(this);
	}
	
	/**
	 * createView() creates the entire calculator GUI
	 */
	public void createView(){
		
		//create a frame to hold Swing components
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		//create "answer" - the field that will hold the numbers and answer, etc
		answer = new JTextField();
		frame.setLayout(new GridBagLayout());
		//create a grid bag layout 
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		frame.add(answer,c);
		
		//set dimensions for the grid bag layout
		c.ipady = 40;      
		c.gridwidth = 1;
		c.gridx = 0;
	
		//add all number buttons and operator buttons 
		addButton("0",c,1);
		addButton("3",c,2);
		addButton("6",c,3);
		addButton("9",c,4);
		c.gridx = 1;
		addButton("1",c,1);
		addButton("4",c,2);
		addButton("7",c,3);
		addButton(".",c,4);
		c.gridx = 2;
		addButton("2",c,1);
		addButton("5",c,2);
		addButton("8",c,3);
		c.gridx = 3;
	  eqButton("+",c,1);
	  eqButton("-",c,2);
	  eqButton("/",c,3);
	  eqButton("*",c,4);
	  
		c.gridx = 2;
		c.gridy = 4;
		
		//add equals button
	  equalsButton = new Button("=");
	  equalsButton.setBackground(Color.magenta);
	  equalsButton.addActionListener(this);
		frame.add(equalsButton,c);	
		
		//add clear button
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 5;
		c.ipady = 20;      
		clearButton = new Button("CLEAR");
		clearButton.setBackground(Color.magenta);
	  clearButton.addActionListener(this);
		frame.add(clearButton,c);
		
	  //finish setting the frame up and set it as visible
		frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		
		//remove observer upon closing the window
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				model.removeObserver(CalcView.this);
			}
		});

	}
	
	/**
	 * eqButton creates a button after changing the grid bag layout
	 * appropriately. It also adds an action listener to the button to 
	 * call the controller to add an operator whenever clicked. 
	 * This is for the numeric operators.
	 * 
	 * @param op
	 * @param c
	 * @param yVal
	 */
	public void eqButton(String op, GridBagConstraints c, int yVal) {
		Button eqButton = new Button(op);
		
		eqButton.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String currentEq = answer.getText();
				controller.addOperator(op, currentEq);
			}
		}));
		
		c.gridy = yVal;
	  eqButton.setBackground(Color.magenta);
		frame.add(eqButton,c);
	}
	
	/** 
	 * addButton() will add a numeric button (i.e. 1, 2, 3, etc) 
	 * and add an action listener to it that calls the method in the
	 * controller to add more to the equation. 
	 * 
	 * @param number
	 * @param c
	 * @param yVal
	 */
	public void addButton(String number, GridBagConstraints c, int yVal) {
		
		Button newButton = new Button(number);
		newButton.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.addEquation(number);
			}
		}));
		c.gridy = yVal;
	  newButton.setBackground(Color.magenta);
		frame.add(newButton,c);
	}

	/**
	 * update() is called whenever the Observable's data changes.
	 * It will display the current equation. 
	 * 
	 * @param o
	 * @param arg
	 */
	
	@Override
	public void update(Observable o, Object arg) {
			answer.setText(controller.getEquation());
	}

	/**
	 * actionPerformed() will create the corresponding
	 * events after a touch event on a button. 
	 * 
	 * @param event
	 */
	public void actionPerformed(ActionEvent event) {
		
		//if the user clicks the equals button, get the answer 
		if(event.getSource()==equalsButton){
			String currentAnswer = answer.getText();
			controller.answerEquals(currentAnswer);
		} else if(event.getSource()==clearButton){
			controller.setClear();
		}
		
	}


}
