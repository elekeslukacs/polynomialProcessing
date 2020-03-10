package polinom;

/**The Polynomial class represents a polynomial and implements operations on polynomials*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
	
	/**Represents the set of Monoms which are in the polynomial*/
	private ArrayList<Monom> elements;
	
	/**Constructor for polynomials which creates the list of Monoms*/
	public Polynomial () {
		this.elements = new ArrayList<Monom>();
	}
	
	/**Constructor which creates a shallow copy of another polynomial.
	 * @param p The source polynomial.*/
	public Polynomial (Polynomial p) {
		this.elements = new ArrayList<Monom>(p.elements);
	}
	
	/**Method for adding new monomials to this Polynomial*/
	public void addMonom(Monom m) {
		this.elements.add(m);
	}
	
	/** Method for addition of polynomials. Firstly this polynomial is copied to result, then terms with similar
	 * exponents are checked in the polynomial to be added, addition on level of Monoms is performed, then the rest
	 * of terms are added.
	 * @param p Polynomial to be added to this.
	 * @return Polynomial containing the sum of this and p. */
	public Polynomial add(Polynomial p) {
		Polynomial result = new Polynomial(this);
		boolean changedCoeff = false;
		
		//For every element in p we check if there already is an element in result with the same exponent.
		for (Monom m1 : p.elements) {
			changedCoeff = false;
			for(Monom m2 : result.elements) {
				if (m1.getExp() == m2.getExp()) {
					int index = result.elements.indexOf(m2);
					m2 = m2.add(m1);
					result.elements.set(index, m2);
					changedCoeff = true;
					break;
				}
			}
			if (!changedCoeff) {
				result.addMonom(m1);
			}
		}
		
		Collections.sort(result.elements, Collections.reverseOrder());
		
		result.removeZero();
		
		return result;
	}
	
	/** Method for subtraction of polynomials. Firstly this polynomial is copied to result, then terms with similar
	 * exponents are checked in the polynomial to be added, subtraction on level of Monoms is performed, then the rest
	 * of terms are subtracted.
	 * @param p Polynomial to be subtracted from this.
	 * @return Polynomial containing the result from this - p. */
	public Polynomial substract(Polynomial p) {
		Polynomial result = new Polynomial(this);
		boolean changedCoeff = false;
		
		for (Monom m1 : p.elements) {
			changedCoeff = false;
			for(Monom m2 : result.elements) {
				if (m1.getExp() == m2.getExp()) {
					int index = result.elements.indexOf(m2);
					m2 = m2.substract(m1);
					result.elements.set(index, m2);
					changedCoeff = true;
					break;
				}
			}
			if (!changedCoeff) {
				m1.setCoeff(-m1.getCoeff());
				result.addMonom(m1);
			}
		}
		
		Collections.sort(result.elements, Collections.reverseOrder());
		
		result.removeZero();
		
		return result;
	}
	
	
	/**Method the gets the coefficients and exponents for monoms from a string and adds monoms to the polynomial. */
	public void getPolynomial(String input) {
		//https://stackoverflow.com/questions/28859919/java-regex-separate-degree-coeff-of-polynomial
		Pattern p = Pattern.compile( "(-?\\b\\d+)[xX]\\^(-?\\d+\\b)" );
		Matcher m = p.matcher( input );
		while (m.find()) {
			double coeff = Double.parseDouble(m.group(1));
			int exp = Integer.parseInt(m.group(2));
			this.addMonom(new Monom(coeff, exp));
		}
	}
	
	/** Method for multiplication of polynomials. First the terms resulted from multiplication of two polynomials is
	 * stored, then terms with equal exponents are added.
	 * @param p Polynomial to be multiplied with this.
	 * @return Polynomial containing the result from this * p. */
	public Polynomial multiply(Polynomial p) {
		Polynomial temp = new Polynomial();
		for(Monom m1 : this.elements) {
			for(Monom m2 : p.elements) {
				temp.addMonom(m1.multiply(m2));
			}
		}
		
		Polynomial result = new Polynomial();
		boolean changedCoeff = false;
		
		//Add every element to an empty polynomial, so equal exponents are checked.
		for (Monom m1 : temp.elements) {
			changedCoeff = false;
			for(Monom m2 : result.elements) {
				if (m1.getExp() == m2.getExp()) {
					int index = result.elements.indexOf(m2);
					m2 = m2.add(m1);
					result.elements.set(index, m2);
					changedCoeff = true;
					break;
				}
			}
			if (!changedCoeff) {
				result.addMonom(m1);
			}
		}
		return result;
	}
	
	/**Derivates this polynomial. An iterator is used, if a term is a constant, it is removed, else derivation on level of monom is performed.*/
	public void derivate() {
		Iterator<Monom> i = this.elements.iterator();
		while(i.hasNext()) {
			Monom m =(Monom) i.next();
			if (m.getExp() == 0) {
				i.remove();
			}
			else {
				m.derivate();
			}
		}
	}
	
	/**Integrates this polynomial. Integrates every monom of the polynomial.*/
	public void integrate() {
		for (Monom m1 : this.elements) {
			m1.integrate();
		}
	}

	/**Divides this to another polynomial.
	 * @param p Divider polynomial.
	 * @return Polynomial containing the quotient from this / p.*/
	public Polynomial divide (Polynomial p) {
		Collections.sort(this.elements, Collections.reverseOrder());
		Collections.sort(p.elements, Collections.reverseOrder());
		
		Polynomial result = new Polynomial();
		Polynomial rest = this;
		Polynomial aux = new Polynomial();	
		Monom temp = new Monom();
		Monom a = new Monom();
		Monom b = new Monom();
		
		while(rest.elements.size() != 0 && p.getFirstTerm().getExp() <= rest.getFirstTerm().getExp()) {
			a = rest.getFirstTerm();
			b = p.getFirstTerm();			
			temp = a.divide(b);
			result.addMonom(temp);
			
			aux.elements.clear();
			aux.addMonom(temp);
			aux = aux.multiply(p);
			rest = rest.substract(aux);
		}
		
		return result;
	}
	
	/**Returns the remainder of a division. Uses the same algorithm as polynomial division, but the remainder is returned.
	 * @param p Divider polynomial.
	 * @return Polynomial containing result from the division this / p.*/
	public Polynomial getRest (Polynomial p) {
		Collections.sort(this.elements, Collections.reverseOrder());
		Collections.sort(p.elements, Collections.reverseOrder());
		
		Polynomial result = new Polynomial();
		Polynomial rest = this;
		Polynomial aux = new Polynomial();	
		Monom temp = new Monom();
		Monom a = new Monom();
		Monom b = new Monom();
		
		while(rest.elements.size() != 0 && p.getFirstTerm().getExp() <= rest.getFirstTerm().getExp()) {
			a = rest.getFirstTerm();
			b = p.getFirstTerm();
			temp = a.divide(b);
			result.addMonom(temp);
			
			aux.elements.clear();
			aux.addMonom(temp);
			aux = aux.multiply(p);
			rest = rest.substract(aux);
		}
		
		return rest;
	}
	
	/**Returns the first monom of a polynomial.*/
	public Monom getFirstTerm() {
		Monom result = new Monom();
		result = this.elements.get(0);
		return result;
	}
	
	/**Removes monoms with coefficient 0. Removes the zeros.*/
	public void removeZero() {
		Iterator<Monom> i = this.elements.iterator();
		while(i.hasNext()) {
			Monom m =(Monom) i.next();
			if (m.getCoeff() == 0) {
				i.remove();
			}
		}
	}
	
	/**Returns a string representation of a Polynomial object.*/
	public String toString() {
		String output = "";
		if(this.elements.size() == 0) {
			output = "0";
		}
		else {
			for(Monom m :  this.elements) {
				output += m.toString();
			}
		}
		return output;
	}
	
//	public void printPolynomial() {
//		for(Monom p : this.elements) {
//			System.out.print(p.getCoeff() + "x^" + p.getExp() + " + ");
//		}
//	}
	
	
}
