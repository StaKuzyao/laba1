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
     * Get real part
     * @return real part
     */
    public double getRe() {
        return re;
    }

    /**
     * Get imaginary part
     * @return imaginary part
     */
    public double getIm() {
        return im;
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
     * Subtract operation.
     * @param b subtrahend
     * @return this Complex object whose value is (this - b)
     */
    public Complex minus(Complex b) {
        re -= b.re;
        im -= b.im;
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
     * Divide operation.
     * @param  b divisor
     * @return this Complex object whose value is this / b
     */
    public Complex dividedBy(Complex b) {
        double denominator = b.re * b.re + b.im * b.im;
        if (Math.abs(denominator) < 1e-15) {
            throw new ArithmeticException("Division by zero");
        }
        double real = (re * b.re + im * b.im) / denominator;
        double imag = (im * b.re - re * b.im) / denominator;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Square operation (z²).
     * @return this Complex object whose value is this²
     */
    public Complex square() {
        double real = re * re - im * im;
        double imag = 2 * re * im;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Cube operation (z³).
     * @return this Complex object whose value is this³
     */
    public Complex cube() {
        double real = re * re * re - 3 * re * im * im;
        double imag = 3 * re * re * im - im * im * im;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Fourth power operation (z⁴).
     * @return this Complex object whose value is this⁴
     */
    public Complex pow4() {
        double real = re * re * re * re - 6 * re * re * im * im + im * im * im * im;
        double imag = 4 * re * re * re * im - 4 * re * im * im * im;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Exponential operation (e^z).
     * @return this Complex object whose value is e^z
     */
    public Complex exp() {
        double magnitude = Math.exp(re);
        double real = magnitude * Math.cos(im);
        double imag = magnitude * Math.sin(im);
        re = real;
        im = imag;
        return this;
    }

    /**
     * Sine operation (sin(z)).
     * @return this Complex object whose value is sin(z)
     */
    public Complex sin() {
        double real = Math.sin(re) * Math.cosh(im);
        double imag = Math.cos(re) * Math.sinh(im);
        re = real;
        im = imag;
        return this;
    }

    /**
     * Cosine operation (cos(z)).
     * @return this Complex object whose value is cos(z)
     */
    public Complex cos() {
        double real = Math.cos(re) * Math.cosh(im);
        double imag = -Math.sin(re) * Math.sinh(im);
        re = real;
        im = imag;
        return this;
    }

    /**
     * Natural logarithm operation (ln(z)).
     * @return this Complex object whose value is ln(z)
     */
    public Complex ln() {
        if (Math.abs(re) < 1e-15 && Math.abs(im) < 1e-15) {
            throw new ArithmeticException("Logarithm of zero");
        }
        double magnitude = Math.log(Math.sqrt(re * re + im * im));
        double angle = Math.atan2(im, re);
        re = magnitude;
        im = angle;
        return this;
    }

    /**
     * Conjugate operation.
     * @return this Complex object whose value is the complex conjugate
     */
    public Complex conjugate() {
        im = -im;
        return this;
    }

    /**
     * Reciprocal operation (1/z).
     * @return this Complex object whose value is 1/this
     */
    public Complex reciprocal() {
        double denominator = re * re + im * im;
        if (Math.abs(denominator) < 1e-15) {
            throw new ArithmeticException("Division by zero");
        }
        double real = re / denominator;
        double imag = -im / denominator;
        re = real;
        im = imag;
        return this;
    }

    /**
     * Square of Complex object's length.
     * @return square of length
     */
    public double lengthSQ() {
        return re * re + im * im;
    }

    /**
     * Actual length (magnitude) of complex number.
     * @return length
     */
    public double length() {
        return Math.sqrt(lengthSQ());
    }

    /**
     * Angle (argument) of complex number in radians.
     * @return angle in radians
     */
    public double angle() {
        return Math.atan2(im, re);
    }

    // ============ ФРАКТАЛЬНЫЕ УРАВНЕНИЯ ============

    /**
     * Классическое уравнение Мандельброта: z = z² + c
     * @param c complex constant
     * @return this Complex object whose value is z² + c
     */
    public Complex mandelbrot(Complex c) {
        return this.square().plus(c);
    }

    /**
     * Кубическое уравнение: z = z³ + c
     * @param c complex constant
     * @return this Complex object whose value is z³ + c
     */
    public Complex cubicMandelbrot(Complex c) {
        return this.cube().plus(c);
    }

    /**
     * Уравнение 4-й степени: z = z⁴ + c
     * @param c complex constant
     * @return this Complex object whose value is z⁴ + c
     */
    public Complex quarticMandelbrot(Complex c) {
        return this.pow4().plus(c);
    }

    /**
     * Уравнение с экспонентой: z = e^z + c
     * @param c complex constant
     * @return this Complex object whose value is e^z + c
     */
    public Complex exponentialFractal(Complex c) {
        return this.exp().plus(c);
    }

    /**
     * Уравнение с синусом: z = sin(z) + c
     * @param c complex constant
     * @return this Complex object whose value is sin(z) + c
     */
    public Complex sineFractal(Complex c) {
        return this.sin().plus(c);
    }

    /**
     * Уравнение с косинусом: z = cos(z) + c
     * @param c complex constant
     * @return this Complex object whose value is cos(z) + c
     */
    public Complex cosineFractal(Complex c) {
        return this.cos().plus(c);
    }

    /**
     * Уравнение с логарифмом: z = ln(z) + c
     * @param c complex constant
     * @return this Complex object whose value is ln(z) + c
     */
    public Complex logarithmicFractal(Complex c) {
        return this.ln().plus(c);
    }

    /**
     * Комбинированное уравнение: z = z² * c + z
     * @param c complex constant
     * @return this Complex object whose value is z² * c + z
     */
    public Complex combinedFractal1(Complex c) {
        // Сохраняем оригинальное значение z
        double originalRe = this.re;
        double originalIm = this.im;

        // Вычисляем z² * c
        this.square().times(c);

        // Прибавляем оригинальное z
        this.re += originalRe;
        this.im += originalIm;

        return this;
    }

    /**
     * Комбинированное уравнение: z = sin(z²) + cos(z) * c
     * @param c complex constant
     * @return this Complex object whose value is sin(z²) + cos(z) * c
     */
    public Complex combinedFractal2(Complex c) {
        // Сохраняем оригинальное значение z
        Complex original = this.copy();

        // Вычисляем sin(z²)
        this.square().sin();

        // Вычисляем cos(original) * c
        Complex cosPart = original.copy().cos().times(c);

        // Складываем результаты
        return this.plus(cosPart);
    }

    /**
     * Уравнение с обратной величиной: z = 1/z + c
     * @param c complex constant
     * @return this Complex object whose value is 1/z + c
     */
    public Complex reciprocalFractal(Complex c) {
        return this.reciprocal().plus(c);
    }

    /**
     * Создает копию комплексного числа
     * @return копия объекта
     */
    public Complex copy() {
        return new Complex(re, im);
    }

    @Override
    public String toString() {
        if (im >= 0) {
            return re + " + " + im + "i";
        } else {
            return re + " - " + (-im) + "i";
        }
    }
}