package demo.parallel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {

    private Complex a;
    private Complex b;
    private Complex zero;
    private Complex one;
    private Complex i;

    @BeforeEach
    public void setUp() {
        a = new Complex(3.0, 4.0);   // 3 + 4i
        b = new Complex(1.0, 2.0);   // 1 + 2i
        zero = new Complex(0.0, 0.0); // 0 + 0i
        one = new Complex(1.0, 0.0);  // 1 + 0i
        i = new Complex(0.0, 1.0);    // 0 + 1i
    }

    // Базовые операции
    @Test
    public void testPlus() {
        Complex result = a.copy().plus(b);
        assertEquals(4.0, result.getRe(), 1e-10);
        assertEquals(6.0, result.getIm(), 1e-10);
    }

    @Test
    public void testMinus() {
        Complex result = a.copy().minus(b);
        assertEquals(2.0, result.getRe(), 1e-10);
        assertEquals(2.0, result.getIm(), 1e-10);
    }

    @Test
    public void testTimes() {
        Complex result = a.copy().times(b);
        // (3+4i)*(1+2i) = 3*1 + 3*2i + 4i*1 + 4i*2i = 3 + 6i + 4i + 8i² = 3 + 10i - 8 = -5 + 10i
        assertEquals(-5.0, result.getRe(), 1e-10);
        assertEquals(10.0, result.getIm(), 1e-10);
    }

    @Test
    public void testDividedBy() {
        Complex result = a.copy().dividedBy(b);
        // (3+4i)/(1+2i) = (3+4i)(1-2i)/(1+4) = (3-6i+4i-8i²)/5 = (11-2i)/5 = 2.2 - 0.4i
        assertEquals(2.2, result.getRe(), 1e-10);
        assertEquals(-0.4, result.getIm(), 1e-10);
    }

    // Степенные функции
    @Test
    public void testSquare() {
        Complex result = b.copy().square();
        // (1+2i)² = 1 + 4i + 4i² = 1 + 4i - 4 = -3 + 4i
        assertEquals(-3.0, result.getRe(), 1e-10);
        assertEquals(4.0, result.getIm(), 1e-10);
    }

    @Test
    public void testCube() {
        Complex result = b.copy().cube();
        // (1+2i)³ = (1+2i)*(1+2i)² = (1+2i)*(-3+4i) = -3 + 4i -6i + 8i² = -3 -2i -8 = -11 -2i
        assertEquals(-11.0, result.getRe(), 1e-10);
        assertEquals(-2.0, result.getIm(), 1e-10);
    }

    @Test
    public void testPow4() {
        Complex result = b.copy().pow4();
        // (1+2i)⁴ = ((1+2i)²)² = (-3+4i)² = 9 -24i +16i² = 9 -24i -16 = -7 -24i
        assertEquals(-7.0, result.getRe(), 1e-10);
        assertEquals(-24.0, result.getIm(), 1e-10);
    }

    // Экспоненциальные и логарифмические функции
    @Test
    public void testExp() {
        Complex result = new Complex(1.0, Math.PI).exp();
        // e^(1+πi) = e * e^(πi) = e * (-1) = -e
        assertEquals(-Math.E, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testSin() {
        Complex result = new Complex(0.0, 1.0).sin();
        // sin(i) = i * sinh(1) = 0 + i*sinh(1)
        assertEquals(0.0, result.getRe(), 1e-10);
        assertEquals(Math.sinh(1.0), result.getIm(), 1e-10);
    }

    @Test
    public void testCos() {
        Complex result = new Complex(0.0, 1.0).cos();
        // cos(i) = cosh(1) = cosh(1) + 0i
        assertEquals(Math.cosh(1.0), result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testLn() {
        Complex result = new Complex(1.0, 0.0).ln();
        // ln(1) = 0 + 0i
        assertEquals(0.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    // Вспомогательные операции
    @Test
    public void testConjugate() {
        Complex result = a.copy().conjugate();
        assertEquals(3.0, result.getRe(), 1e-10);
        assertEquals(-4.0, result.getIm(), 1e-10);
    }

    @Test
    public void testReciprocal() {
        Complex result = a.copy().reciprocal();
        // 1/(3+4i) = (3-4i)/(9+16) = (3-4i)/25 = 0.12 - 0.16i
        assertEquals(0.12, result.getRe(), 1e-10);
        assertEquals(-0.16, result.getIm(), 1e-10);
    }

    @Test
    public void testLengthSQ() {
        double result = a.lengthSQ();
        assertEquals(25.0, result, 1e-10);
    }

    @Test
    public void testLength() {
        double result = a.length();
        assertEquals(5.0, result, 1e-10);
    }

    @Test
    public void testAngle() {
        double result = new Complex(1.0, 1.0).angle();
        assertEquals(Math.PI/4, result, 1e-10);
    }

    // Тесты фрактальных уравнений
    @Test
    public void testMandelbrot() {
        Complex result = a.copy().mandelbrot(b);
        // z² + c = (3+4i)² + (1+2i) = (-7+24i) + (1+2i) = -6 + 26i
        assertEquals(-6.0, result.getRe(), 1e-10);
        assertEquals(26.0, result.getIm(), 1e-10);
    }

    @Test
    public void testCubicMandelbrot() {
        Complex result = b.copy().cubicMandelbrot(b);
        // z³ + c = (1+2i)³ + (1+2i) = (-11-2i) + (1+2i) = -10 + 0i
        assertEquals(-10.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testQuarticMandelbrot() {
        Complex result = b.copy().quarticMandelbrot(b);
        // z⁴ + c = (1+2i)⁴ + (1+2i) = (-7-24i) + (1+2i) = -6 -22i
        assertEquals(-6.0, result.getRe(), 1e-10);
        assertEquals(-22.0, result.getIm(), 1e-10);
    }

    @Test
    public void testExponentialFractal() {
        Complex result = zero.copy().exponentialFractal(one);
        // e^z + c = e^0 + 1 = 1 + 1 = 2 + 0i
        assertEquals(2.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testSineFractal() {
        Complex result = zero.copy().sineFractal(one);
        // sin(z) + c = sin(0) + 1 = 0 + 1 = 1 + 0i
        assertEquals(1.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testCosineFractal() {
        Complex result = zero.copy().cosineFractal(one);
        // cos(z) + c = cos(0) + 1 = 1 + 1 = 2 + 0i
        assertEquals(2.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testCombinedFractal1() {
        Complex result = b.copy().combinedFractal1(b);
        // z² * c + z = (1+2i)² * (1+2i) + (1+2i) = (-3+4i)*(1+2i) + (1+2i)
        // = (-3-6i+4i+8i²) + (1+2i) = (-11-2i) + (1+2i) = -10 + 0i
        assertEquals(-10.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    @Test
    public void testReciprocalFractal() {
        Complex result = one.copy().reciprocalFractal(one);
        // 1/z + c = 1/1 + 1 = 1 + 1 = 2 + 0i
        assertEquals(2.0, result.getRe(), 1e-10);
        assertEquals(0.0, result.getIm(), 1e-10);
    }

    // Тесты граничных случаев
    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            a.copy().dividedBy(zero);
        });
    }

    @Test
    public void testReciprocalOfZero() {
        assertThrows(ArithmeticException.class, () -> {
            zero.copy().reciprocal();
        });
    }

    @Test
    public void testLnOfZero() {
        assertThrows(ArithmeticException.class, () -> {
            zero.copy().ln();
        });
    }

    // Тест копирования
    @Test
    public void testCopy() {
        Complex original = new Complex(5.0, 6.0);
        Complex copy = original.copy();

        assertEquals(original.getRe(), copy.getRe(), 1e-10);
        assertEquals(original.getIm(), copy.getIm(), 1e-10);

        // Проверяем, что это разные объекты
        original.plus(one);
        assertNotEquals(original.getRe(), copy.getRe(), 1e-10);
    }

    // Тест toString
    @Test
    public void testToString() {
        assertEquals("3.0 + 4.0i", a.toString());
        assertEquals("1.0 + 2.0i", b.toString());
        assertEquals("0.0 + 0.0i", zero.toString());
    }
}