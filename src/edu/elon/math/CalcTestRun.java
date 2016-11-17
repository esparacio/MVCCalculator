package edu.elon.math;

/**
 * CalcTestRun runs the calculator program and tests it. 
 * 
 * @author Elena Sparacio and Jamie Utt
 * @copyright 2016
 * 
 * */
public class CalcTestRun {

	/**
	 * main() creates the model and the controller, which
	 * starts up a GUI for the calculator.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		CalcModelInterface model = new CalcModel(); 
		CalcControllerInterface controller = new CalcController(model);
		
	}
	
}
