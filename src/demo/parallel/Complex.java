package demo.parallel;

/**
 * A complex number is a number that can be expressed in the form a + b * i, where
 * a and b are real numbers and i is the imaginary unit, which satisfies the
 * equation i ^ 2 = -1. a is the real part and b is the imaginary part of the
 * complex number.
 * <p><i>
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.</i>
 * @author Alexander Kouznetsov, Tristan Yan
 */
public class Complex {

    private double re;   // the real part
    private double im;   // the imaginary part

    /**
     * create a new object with the given real and imaginary parts
     *
     * @param real a complex number real part
     * @param imag a complex number imaginary part
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * Add operation.
     * @param b summand
     * @return this Complex object whose value is (this + b)
     */
    public Complex plus(Complex b) {
        re += b.re;
        im += b.im;
        return this;
    }

    /**
     * Multiply operation.
     * @param  b multiplier
     * @return this Complex object whose value is this * b
     */
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Cube operation (z³).
     * @return this Complex object whose value is this³
     */
    public Complex cube() {
        // (a + bi)³ = a³ + 3a²bi + 3a(bi)² + (bi)³
        // = a³ + 3a²bi - 3ab² - b³i
        // = (a³ - 3ab²) + (3a²b - b³)i
        double real = re * re * re - 3 * re * im * im;
        double imag = 3 * re * re * im - im * im * im;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Fractal equation yc(z) = z³ + c
     * @param c complex constant
     * @return this Complex object whose value is z³ + c
     */
    public Complex fractalEquation(Complex c) {
        return this.cube().plus(c);
    }

    /**
     * Square of Complex object's length, we're using square of length to
     * eliminate the computation of square root
     * @return square of length
     */
    public double lengthSQ() {
        return re * re + im * im;
    }
}