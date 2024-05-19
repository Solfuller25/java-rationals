package com.cooksys.ftd.assignments.objects;

import com.cooksys.ftd.assignments.objects.util.MissingImplementationException;

public class SimplifiedRational implements IRational {
	
	private int numerator;
	private int denominator;
    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int a, int b) throws IllegalArgumentException {
    	if (a <= 0 || b < 0) {
    		throw new IllegalArgumentException();
    	} else if (b == 0) {
    		return a;
    	}
    	
    	int remainder = Math.max(a, b) % Math.min(a, b);
    	if (remainder == 0) {
    		return b;
    	} else {
    		return gcd(Math.min(a, b), remainder);
    	}
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	int[] simpleRat = new int[2];
    	if (numerator == 0) { // Do not simplify if the numerator is 0
    		simpleRat[0] = numerator;
    		simpleRat[1] = 1;
    		return simpleRat;
    	}
    	
    	// Figure out greatest common divisor between both values
    	int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
    	System.out.println(gcd);
    	return new int[] { (numerator / gcd), (denominator / gcd) };
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (numerator == 0) {
        	this.numerator = numerator;
        	this.denominator = denominator;
    	} else {
        	int[] simpleRat = simplify(numerator, denominator);
        	this.numerator = simpleRat[0];
        	this.denominator = simpleRat[1];
    	}
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
    	if (obj == null)
    		return false;
    	if (this.getClass() != obj.getClass())
    		return false;
    	SimplifiedRational other = (SimplifiedRational) obj;
    	
    	if (this.numerator != other.numerator) {
    		return false;
    	} else if (this.denominator != other.denominator) {
    		return false;
    	}
    	return true;
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
    	if ((this.numerator < 0 && this.denominator < 0) || ((this.numerator > 0 && this.denominator > 0))) {
    		// This value is positive because both numerator and denominator are the same sign
    		return String.format("%d/%d", Math.abs(this.numerator), Math.abs(this.denominator));
    	} else {
    		// In this case, only the value is negative is negative
    		return String.format("-%d/%d", Math.abs(this.numerator), Math.abs(this.denominator));
    	}
    }
}
