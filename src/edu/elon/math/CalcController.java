package edu.elon.math;

public class CalcController implements CalcControllerInterface {

	CalcModelInterface model;
	CalcView view;
	
	public CalcController(CalcModelInterface aModel){
		this.model = aModel;
		view = new CalcView(this, model);
		view.createView();
		model.initialize();
	}

	public void answerEquals(String anEquation){
		
		model.setNewEquation(anEquation);
    model.getAnswer();

	}
	
	public void addEquation(String anEquation){
		model.addEquation(anEquation);
	}
	
	public void addOperator(String anOperator, String currentEq){
		model.addOperator(anOperator, currentEq);
	}
	
}
