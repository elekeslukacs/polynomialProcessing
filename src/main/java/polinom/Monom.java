package polinom;

/**The Monom class represents a monomial and implements operations on monomials*/

public class Monom implements Comparable<Monom> {
	
	/**Attributes for the class Monom*/
	private double coeff;
	private int exp;
	
	/**Constructor
	 * @param coeff Contains the value of the monomial's coefficient
	 * @param exp Contains the value of the monomial's exponential value
	 * */
	public Monom (double coeff, int exp) {
		this.coeff = coeff;
		this.exp = exp;
	}
	
	/**Default constructor
	 * creates the monomial 0x^0.*/
	public Monom() {
		this.coeff = 0;
		this.exp = 0;
	}
	
	public double getCoeff() {
		return this.coeff;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	/** Addition of two monomials
	 * @param m A monomial which will be added to this monomial
	 * @return A monomial representing the sum of this and m.*/
	public Monom add(Monom m) {
		Monom result = new Monom();
		if (this.exp == m.exp) {
			result.coeff = this.coeff + m.coeff;
			result.exp = this.exp;
			return result;
		}
		else {
			return null;
		}
		
	}
	
	
	/**Subtraction of two monomials
	 * @param m m A monomial which will be subtracted from this monomial
	 * @return A monomial containing this - m.*/
	public Monom substract(Monom m) {
		Monom result = new Monom();
		if (this.exp == m.exp) {
			result.coeff = this.coeff - m.coeff;
			result.exp = this.exp;
			return result;
		}
		else {
			return null;
		}
	}
	
	/**Derivates this monomial, this monomial will be modified.*/
	public void derivate() {
		if (this.exp == 0) {
			this.coeff = 0;
		}
		else {
			this.coeff *= this.exp;
			this.exp--;
		}
	}
	
	/**Integrates this monomial, this monomial will be modified.*/
	public void integrate() {
		this.coeff /= (this.exp + 1);
		this.exp++;
	}
	
	/**Multiplies two monomials
	 * @param m Monomial which will be multiplied with this monomial
	 * @return A monomial containing this * m.*/
	public Monom multiply(Monom m) {
		Monom result = new Monom();
		result.setCoeff(this.coeff * m.coeff);
		result.setExp(this.exp + m.exp);
		return result;
	}
	
	/**Divides two monomials
	 * @param m This will be divided to m
	 * @return A monomial containing the quotient of this/m.*/
	public Monom divide (Monom m) {
		Monom result = new Monom();
		result.setCoeff(this.coeff / m.coeff);
		result.setExp(this.exp - m.exp);
		return result;
	}
	
	/**Returns a string representation of a Monom object, like coeffx^exp.*/
	public String toString() {
		String output = "";
		if (this.coeff > 0)
			output = output + " + " + this.coeff + "x^" + this.exp;
		else output = output + " - " + (-this.coeff) + "x^" + this.exp;
		return output;
	}

	
	public int compareTo(Monom m) {
		return (this.getExp() - m.getExp());
	}

}
