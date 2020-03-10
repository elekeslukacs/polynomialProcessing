package test;

import org.junit.Test;

import polinom.Polynomial;

import static org.junit.Assert.*;


public class TestAssertions {
	
	@Test
	public void testAssertions() {
		//Addition and division
		Polynomial p = new Polynomial();
		Polynomial q = new Polynomial();
		p.getPolynomial("1x^3-2x^1+3x^0");
		q.getPolynomial("2x^2-1x^0");
		Polynomial result = q.add(p);
		//Subtraction
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		p1.getPolynomial("1x^3+2x^1+3x^0");
		p2.getPolynomial("2x^2-1x^0");
		Polynomial sub = p1.substract(p2);
		
		//Differentiation
		Polynomial der = new Polynomial();
		der.getPolynomial("1x^3-2x^1+3x^0");
		der.derivate();
		//Integration
		Polynomial integ = new Polynomial();
		integ.getPolynomial("3x^2+1x^1+3x^0");
		integ.integrate();
		//Multiplication
		Polynomial x1 = new Polynomial();
		Polynomial x2 = new Polynomial();
		x1.getPolynomial("1x^1+1x^0");
		x2.getPolynomial("1x^1+2x^0");
		
		assertEquals(" + 1.0x^3 + 2.0x^2 - 2.0x^1 + 2.0x^0", result.toString());	
		assertEquals(" + 1.0x^3 - 2.0x^2 + 2.0x^1 + 4.0x^0", sub.toString());
		assertEquals(" + 0.5x^1", p.divide(q).toString());
		assertEquals(" - 1.5x^1 + 3.0x^0", p.getRest(q).toString());
		assertEquals(" + 3.0x^2 - 2.0x^0", der.toString() );
		assertEquals(" + 1.0x^3 + 0.5x^2 + 3.0x^1", integ.toString());
		assertEquals(" + 1.0x^2 + 3.0x^1 + 2.0x^0", x1.multiply(x2).toString());
		
		
	}

}
