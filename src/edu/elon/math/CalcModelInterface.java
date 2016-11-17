package edu.elon.math;

import java.util.Observer;

/**
 * CalcModelInterface is responsible 
 * for all the Calculator logic. It has no knowledge
 * of the View or Controller. 
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public interface CalcModelInterface {
	
	/**
	 * initalize() is called when the CalcModel is first
	 * created. It initializes the ArrayList and sets the 
	 * equation to an empty string.
	 */
	void initialize();
	
	/**
	 * setNewEquation() sets the old equation to a 
	 * new String. It then adds that to the ArrayList.
	 * 
	 * @param anEquation
	 */
	void setNewEquation(String anEquation);
	
	/**
	 * getEq() returns the current total equation contained 
	 * in the Calculator
	 * 
	 * @return equation
	 */
	String getEq();
	
	/**
	 * addOperator() will add a numeric operators to the ArrayList, which
	 * contains all the numbers and operators of the equation. It then 
	 * will set the equation back to empty.
	 * 
	 * @param operator
	 * @param currentEq
	 */
	void addOperator(String operator, String currentEq); 
	
	/**
	 * addEquation() will add more numbers or decimals to 
	 * the current String, and call dataChanged() so the observer
	 * can update.
	 * 
	 * @param moreEq
	 */
	void addEquation(String moreEq);
	
	/**
	 * getAnswer() will solve the current equation that is 
	 * stored in the ArrayList. It calls dataChanged after
	 * to the update the Observers.
	 */
	void getAnswer();
	
  /**
   * registerObserver() will register a new observer with
   * the observable.
   * 
   * @param Observer o
   */
	void registerObserver(Observer o);
	
  /**
   * removeObserver() will delete an observer with
   * the observable.
   * 
   * @param Observer o
   */
	void removeObserver(Observer o);

	

}
