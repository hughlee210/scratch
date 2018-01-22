package com.hlee.scratch;

import java.math.BigInteger;
import java.util.Objects;

public class Fraction {

    private int numer;
    private int denom;

    public Fraction() {
        this(0);
    }

    public Fraction(int numer) {
        this(numer, 1);
    }

    public Fraction(int numer, int denom) {
        this.numer = numer;
        this.denom = denom;
    }

    public Fraction add(Fraction f) {
        if (denom == f.denom) {
            return new Fraction(numer + f.numer, denom);
        }
        return new Fraction(this.numer * f.denom + this.denom * f.numer, this.denom * f.denom);
    }

    public Fraction subtract(Fraction f) {
        if (denom == f.denom)
            return new Fraction(numer - f.numer, denom);
        return new Fraction(this.numer * f.denom - this.denom * f.numer, this.denom * f.denom);
    }

    public Fraction multiply(Fraction f) {
        return new Fraction(this.numer * f.numer, this.denom * f.denom);
    }

    public Fraction divide(Fraction f) {
        return new Fraction(this.numer * f.denom, this.denom * f.numer);
    }

    private static int gcd(int a, int b) {
        // base case 
        if (b == 0)
            return a;
        if (a < 0)
            a = -a;
        if (b < 0)
            b = -b;
        return gcd(b, a % b);
    }

    private static int gcdIter(int a, int b) {
        int r;
        while (b > 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    @Override
    public String toString() {
        int gcd = gcd(numer, denom);
        return numer / gcd + "/" + denom / gcd;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numer, denom);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Fraction other = (Fraction) obj;
        return Objects.equals(numer, other.numer) &&
                        Objects.equals(denom, other.denom);
    }

    public static void main(String[] args) {

        testFraction();
        testFraction2();
    }

    static void testFraction() {
        /* Test all three contructors and toString. */
        Fraction f0 = new Fraction();
        Fraction f1 = new Fraction(3);
        Fraction f2 = new Fraction(12, 20);

        System.out.println("\nTesting constructors (and toString):");
        System.out.println("The fraction f0 is " + f0.toString());
        System.out.println("The fraction f1 is " + f1); // toString is implicit
        System.out.println("The fraction f2 is " + f2);

        Fraction sumOfTwo = f1.add(f2);
        Fraction sumOfThree = f0.add(f1).add(f2);

        System.out.println("The sum of " + f1 + " and " + f2 + " is " + sumOfTwo);
        System.out.println("The sum of " + f0 + ", " + f1 + " and " + f2 + " is " + sumOfThree);

        /* Test gcd function (static method). */
        System.out.println("\nTesting gcd:");
        System.out.println("The gcd of 2 and 10 is: " + gcd(2, 10));
        System.out.println("The gcd of 15 and 5 is: " + gcd(15, 5));
        System.out.println("The gcd of 24 and 18 is: " + gcd(24, 18));
        System.out.println("The gcd of 10 and 10 is: " + gcd(10, 10));
        System.out.println("The gcd of 21 and 400 is: " + gcd(21, 400));
        System.out.println("===========================================================\n");
    }

    static void testFraction2() {
        /* Test all three contructors and toString. */
        Fraction2 f0 = new Fraction2();
        Fraction2 f1 = new Fraction2(new BigInteger("3"));
        Fraction2 f2 = new Fraction2(new BigInteger("12"), new BigInteger("20"));

        System.out.println("\nTesting constructors (and toString):");
        System.out.println("The Fraction f0 is " + f0.toString());
        System.out.println("The Fraction f1 is " + f1); // toString is implicit
        System.out.println("The Fraction f2 is " + f2);

        Fraction2 sumOfTwo = f1.add(f2);
        Fraction2 sumOfThree = f0.add(f1).add(f2);

        System.out.println("The sum of " + f1 + " and " + f2 + " is " + sumOfTwo);
        System.out.println("The sum of " + f0 + ", " + f1 + " and " + f2 + " is " + sumOfThree);

        /* Test gcd function (static method). */
        System.out.println("\nTesting gcd:");
        System.out.println("The gcd of 2 and 10 is: " + gcd(2, 10));
        System.out.println("The gcd of 15 and 5 is: " + gcd(15, 5));
        System.out.println("The gcd of 24 and 18 is: " + gcd(24, 18));
        System.out.println("The gcd of 10 and 10 is: " + gcd(10, 10));
        System.out.println("The gcd of 21 and 400 is: " + gcd(21, 400));
    }
}

class Fraction2 {

    private BigInteger numerator;
    private BigInteger denominator;

    public Fraction2() {
        this(BigInteger.ZERO);
    }

    public Fraction2(BigInteger numer) {
        this(numer, BigInteger.ONE);
    }

    public Fraction2(BigInteger numer, BigInteger denom) {
        if (numer == null || denom == null)
            throw new IllegalArgumentException("numerator / denominator must not be null");
        if (denom.equals(BigInteger.ZERO))
            throw new IllegalArgumentException("denominator must not be zero");

        this.numerator = numer;
        this.denominator = denom;
    }

    public Fraction2 add(Fraction2 f) {
        if (f == null)
            throw new IllegalArgumentException("argument f must not be null");
        return new Fraction2(this.numerator.multiply(f.denominator).add(f.numerator.multiply(this.denominator)), this.denominator.multiply(f.denominator));
    }

    public Fraction2 subtract(Fraction2 f) {
        if (f == null)
            throw new IllegalArgumentException("argument f must not be null");
        return new Fraction2(this.numerator.multiply(f.denominator).subtract(f.numerator.multiply(this.denominator)), this.denominator.multiply(f.denominator));
    }

    public Fraction2 multiply(Fraction2 f) {
        if (f == null)
            throw new IllegalArgumentException("argument f must not be null");
        return new Fraction2(this.numerator.multiply(f.numerator), this.denominator.multiply(f.denominator));
    }

    public Fraction2 divide(Fraction2 f) {
        if (f == null)
            throw new IllegalArgumentException("argument f must not be null");
        return new Fraction2(this.numerator.multiply(f.denominator), this.denominator.multiply(f.numerator));
    }

    @Override
    public String toString() {
        BigInteger gcd = numerator.gcd(denominator);
        return numerator.divide(gcd) + "/" + denominator.divide(gcd);
    }
}
