package controller;

/**Main class for testing the application, where model, view and controller objects are created.*/

import controller.PolynomialController;
import polinom.Polynomial;
import view.PolynomialView;

public class Main {

	public static void main(String[] args) {

		PolynomialView view = new PolynomialView();
		PolynomialController controller = new PolynomialController(view);
		
//		Polynomial p = new Polynomial();
//		Polynomial q = new Polynomial();
		
//		p.getPolynomial("1x^3-2x^1+3x^0");
//		q.getPolynomial("2x^2-1x^0");
//		p.substract(q);
//		System.out.println(p.toString());
//		System.out.println(q.toString());
	}

}
