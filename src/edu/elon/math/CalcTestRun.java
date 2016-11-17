package edu.elon.math;

public class CalcTestRun {

	
	/**
	 * Needs to use Strategy and Observer pattern!
	 * */
	
	public static void main(String[] args) {
		
		CalcModelInterface model = new CalcModel(); 
		CalcControllerInterface controller = new CalcController(model);
		
	}
	
}
