package controller;

/**Controller class makes connection between model and view. Every buttons ActionListener takes information from the
 * user input, calls the methods from the model and updates the view. There is no Polynomial from the model among the
 * attributes, in every method new local polynomials are created.*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import polinom.Polynomial;
import view.PolynomialView;

public class PolynomialController {
	//private Polynomial polinomP;
	//private Polynomial polinomQ;
	private PolynomialView view;
	
	public PolynomialController(PolynomialView v) {
		
		//this.polinomP = new Polynomial();
		//this.polinomQ = new Polynomial();
		this.view = v;
		
		ActionListener add = new AddListener();
		ActionListener sub = new SubListener();
		ActionListener mul = new MulListener();
		ActionListener div = new DivListener();
		ActionListener der = new DerListener();
		ActionListener integrate = new IntegrateListener();
		
		view.addListeners(add, sub, mul, div, der, integrate);
	}
	
	
	class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			view.setResult("");
			Polynomial p = new Polynomial();
			Polynomial q = new Polynomial();
			
			p.getPolynomial(view.getInputOne());
			q.getPolynomial(view.getInputTwo());
			
			Polynomial result = p.add(q);
			view.setResult(result.toString());
		
	}
	}
	
	class SubListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			view.setResult("");
			Polynomial p = new Polynomial();
			Polynomial q = new Polynomial();
			
			p.getPolynomial(view.getInputOne());
			q.getPolynomial(view.getInputTwo());
			
			Polynomial result = p.substract(q);
			view.setResult(result.toString());
			
		}
		
	}
	
	class MulListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			view.setResult("");
			Polynomial p = new Polynomial();
			Polynomial q = new Polynomial();
			
			p.getPolynomial(view.getInputOne());
			q.getPolynomial(view.getInputTwo());
			
			Polynomial result = p.multiply(q);
			view.setResult(result.toString());
			
		}
		
	}
	
	class DivListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			view.setResult("");
			Polynomial p = new Polynomial();
			Polynomial q = new Polynomial();
			
			if(view.getInputTwo().equals("")) {
				view.setResult("Please introduce a polynomial, division by 0 is not possible.");
				return;
			}
			
			p.getPolynomial(view.getInputOne());
			q.getPolynomial(view.getInputTwo());
			
			
			Polynomial result = p.divide(q);
			Polynomial remainder = p.getRest(q);
			view.setResult("Q:" + result.toString() + "         R:" + remainder.toString());
		}
		
	}
	
	class DerListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			view.setResult("");
			Polynomial p = new Polynomial();
			p.getPolynomial(view.getInputOne());
			p.derivate();
			view.setResult(p.toString());
			
		}
		
	}
	
	class IntegrateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			view.setResult("");
			Polynomial p = new Polynomial();
			p.getPolynomial(view.getInputOne());
			p.integrate();
			view.setResult(p.toString());
			
		}
		
	}
	
	

	
	
}
