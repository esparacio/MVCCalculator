package edu.elon.math;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Should have an interface? 
 * */

public class CalcModel extends Observable implements CalcModelInterface {
	
	String equation;
	ArrayList<String> totalEq;

	public void initialize() {
		totalEq = new ArrayList<String>();
		equation = "";
	}

	public void setNewEquation(String anEquation) {
		equation = anEquation;
		totalEq.add(equation);
		equation = "";
	}
	
	public String getEq(){
		return equation;
	}

	public void addOperator(String operator, String currentEq) {
		totalEq.add(currentEq);
		totalEq.add(operator);
		equation = "";		
	}

	public void addEquation(String moreEq) {
		String aDot = ".";
		if(moreEq.equals(aDot)&&(equation.indexOf(aDot)!=-1)){
				return; 
		}
		equation = equation + moreEq;
		dataChanged();
	}

	public void getAnswer() {
		try{
			double answer = Double.parseDouble(totalEq.get(0));
			for(int i= 0; i< totalEq.size(); i++){
				String currentNum = totalEq.get(i);
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
			totalEq = new ArrayList<String>();
			equation = Double.toString(answer);
		  dataChanged();
			return;
			}
			catch(Exception e){
				System.out.println("You have probably incorrectly entered an equation. Try again");
				totalEq = new ArrayList<String>();
			}
			totalEq = new ArrayList<String>();

	}
	
  /**
   * Notifies observers when the data changes
   */
  public void dataChanged() {
    setChanged();
    notifyObservers();
  }


	@Override
	public void registerObserver(Observer o) {
		this.addObserver(o);
	}


	@Override
	public void removeObserver(Observer o) {
		this.deleteObserver(o);
	}



}
