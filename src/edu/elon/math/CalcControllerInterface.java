package edu.elon.math;

/**
 * CalcControllerInterface holds methods that allow
 * for communication between the view and the model. 
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public interface CalcControllerInterface {
	
	/**
	 * answerEquals() is a method that is called by the view
	 * and then passed onto the model when the equals button
	 * is pressed. The model gets the answer from the 
	 * equation. 
	 * 
	 * @param anEquation 
	 */
	void answerEquals(String anEquation);
	
	/**
	 * addEquation() will add any additional strings input
	 * by the user to the current Equation, held in the model.
	 * 
	 * @param anEquation
	 */
	void addEquation(String anEquation);
	
	/**
	 * addOperator() will add a numerical operator
	 * to the equation, such as "+"
	 * 
	 * @param anOperator
	 * @param currentEq
	 */
	void addOperator(String anOperator, String currentEq);
	
	/**
	 * getEquation returns a String for the current
	 * equation on the calculator.
	 * 
	 * @return String current equation
	 */
	String getEquation();
	
}
