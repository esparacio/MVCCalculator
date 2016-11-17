package edu.elon.math;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * CalcModel implements the CalcModelInterface
 * and extends Observable. It is responsible 
 * for all the Calculator logic. It has no knowledge
 * of the View or Controller. 
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public class CalcModel extends Observable implements CalcModelInterface {
	/**
	 * Variables to hold the equation before computation
	 */
	String equation;
	ArrayList<String> totalEq;

	/**
	 * initalize() is called when the CalcModel is first
	 * created. It initializes the ArrayList and sets the 
	 * equation to an empty string.
	 */
	public void initialize() {
		totalEq = new ArrayList<String>();
		equation = "";
		dataChanged();
	}

	/**
	 * setNewEquation() sets the old equation to a 
	 * new String. It then adds that to the ArrayList.
	 * 
	 * @param anEquation
	 */
	public void setNewEquation(String anEquation) {
		equation = anEquation;
		totalEq.add(equation);
		equation = "";
	}
	
	/**
	 * getEq() returns the current total equation contained 
	 * in the Calculator
	 * 
	 * @return equation
	 */
	public String getEq(){
		return equation;
	}

	/**
	 * addOperator() will add a numeric operators to the ArrayList, which
	 * contains all the numbers and operators of the equation. It then 
	 * will set the equation back to empty.
	 * 
	 * @param operator
	 * @param currentEq
	 */
	public void addOperator(String operator, String currentEq) {
		totalEq.add(currentEq);
		totalEq.add(operator);
		equation = "";		
	}

	/**
	 * addEquation() will add more numbers or decimals to 
	 * the current String, and call dataChanged() so the observer
	 * can update.
	 * 
	 * @param moreEq
	 */
	public void addEquation(String moreEq) {
		
		//if the user tries to input two decimals,
		//don't let that fiend ruin your equation!
		String aDot = ".";
		if(moreEq.equals(aDot)&&(equation.indexOf(aDot)!=-1)){
				return; 
		}
		equation = equation + moreEq;
		dataChanged();
	}

	/**
	 * getAnswer() will solve the current equation that is 
	 * stored in the ArrayList. It calls dataChanged after
	 * to the update the Observers.
	 */
	public void getAnswer() {
		
		//in a try-catch, in case a user tries to enter invalid characters
		try{
			//get the first number in the list
			double answer = Double.parseDouble(totalEq.get(0));
			//go through the ArrayList
			for(int i= 0; i< totalEq.size(); i++){
				//get the current number
				String currentNum = totalEq.get(i);
				//if the current number is an operator, do the operation
				//with the number ahead of it. Then, skip past that number.
				if(currentNum.equals("+")){
					double nextNum = Double.parseDouble(totalEq.get(i+1));
					answer = answer + nextNum;
					i++;
				} else if(currentNum.equals("-")){
					double nextNum = Double.parseDouble(totalEq.get(i+1));
					answer = answer - nextNum;
					i++;
				} else if(currentNum.equals("/")){
					double nextNum = Double.parseDouble(totalEq.get(i+1));
					answer = answer / nextNum;
					i++;
				} else if(currentNum.equals("*")){
					double nextNum = Double.parseDouble(totalEq.get(i+1));
					answer = answer * nextNum;
					i++;
				}
			}
			//reset the ArrayList and set the equation to the answer
			//so the Observers receive it and update accordingly. 
			totalEq = new ArrayList<String>();
			equation = Double.toString(answer);
		  dataChanged();
			return;
			}
			catch(Exception e){
				System.out.println("You have probably incorrectly entered an equation. Try again");
				totalEq = new ArrayList<String>();
			}
		  //reset the ArrayList no matter what
			totalEq = new ArrayList<String>();

	}
	
  /**
   * dataChanged () notifies observers when the data changes
   */
  public void dataChanged() {
    setChanged();
    notifyObservers();
  }

  /**
   * registerObserver() will register a new observer with
   * the observable.
   * 
   * @param Observer o
   */
	@Override
	public void registerObserver(Observer o) {
		this.addObserver(o);
	}

  /**
   * removeObserver() will delete an observer with
   * the observable.
   * 
   * @param Observer o
   */
	@Override
	public void removeObserver(Observer o) {
		this.deleteObserver(o);
	}



}
