package edu.elon.math;

/**
 * CalcController implements the CalcControllerInterface
 * and is responsible for controlling communication 
 * between the View and the Model. 
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public class CalcController implements CalcControllerInterface {

	/**
	 * Variables to hold the model and the view
	 */
	CalcModelInterface model;
	CalcView view;
	
	/**
	 * Constructor that sets the model and view variables and
	 * creates a GUI for the user to interact with.
	 * 
	 * @param aModel
	 */
	public CalcController(CalcModelInterface aModel){
		this.model = aModel;
		view = new CalcView(this, model);
		view.createView();
		model.initialize();
	}

	/**
	 * answerEquals() is a method that is called by the view
	 * and then passed onto the model when the equals button
	 * is pressed. The model gets the answer from the 
	 * equation. 
	 * 
	 * @param anEquation 
	 */
	public void answerEquals(String anEquation){
		
		model.setNewEquation(anEquation);
    model.getAnswer();

	}
	/**
	 * addEquation() will add any additional strings input
	 * by the user to the current Equation, held in the model.
	 * 
	 * @param anEquation
	 */
	public void addEquation(String anEquation){
		model.addEquation(anEquation);
	}
	
	/**
	 * addOperator() will add a numerical operator
	 * to the equation, such as "+"
	 * 
	 * @param anOperator
	 * @param currentEq
	 */
	public void addOperator(String anOperator, String currentEq){
		model.addOperator(anOperator, currentEq);
	}
	
	/**
	 * getEquation returns a String for the current
	 * equation on the calculator.
	 * 
	 * @return String current equation
	 */
	public String getEquation(){
		return model.getEq();
	}
	
}
