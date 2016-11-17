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

import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalcView implements Observer, ActionListener {
	
	CalcModelInterface model;
	CalcControllerInterface controller;
	
	JFrame frame; 
	JTextField answer;
	Button equalsButton;
	
	String currentEq;
	
	
	public CalcView(CalcControllerInterface aController, CalcModelInterface aModel){
		this.controller = aController;
		this.model = aModel;
		model.registerObserver(this);
	}
	
	public void createView(){
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		answer = new JTextField();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		frame.add(answer,c);
		
		c.ipady = 40;      
		c.gridwidth = 1;
		c.gridx = 0;
	
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
		
	  equalsButton = new Button("=");
	  equalsButton.setBackground(Color.magenta);
	  equalsButton.addActionListener(this);
	  
		frame.add(equalsButton,c);	
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


	@Override
	public void update(Observable o, Object arg) {
		
		if (o instanceof CalcModelInterface) {
			CalcModelInterface aModel = (CalcModelInterface) o;
			answer.setText(aModel.getEq());

		}

		
	}

	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource()==equalsButton){
			String currentAnswer = answer.getText();
			controller.answerEquals(currentAnswer);
		} 
		
	}


}
