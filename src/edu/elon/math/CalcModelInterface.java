package edu.elon.math;

import java.util.Observer;

public interface CalcModelInterface {
	
	void initialize();
	
	void setNewEquation(String anEquation);
	
	String getEq();
	
	void addOperator(String operator, String currentEq); 
	
	void addEquation(String moreEq);
	
	void getAnswer();
	
	void registerObserver(Observer o);
	
	void removeObserver(Observer o);

	

}
