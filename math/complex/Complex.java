package math.complex;

/**
 * The Complex class is used for storing and performing operations on
 * complex numbers comprised of primitive double type real and imaginary
 * parts.
 *
 * Complex numbers have the form:
 *
 *      a + b*i,
 *
 * where i is the imaginary constant.
 */
public class Complex
{
    /* -- complex member fields -- */
    private double re;      // the real part
    private double im;      // the imaginary part

    /**
     * The default complex number constructor.
     */
    public Complex()
    {}

    /**
     * The specified complex number constructor.
     *
     * @param re The real part.
     * @param im The imaginary part.
     */
    public Complex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }


    /* -- complex algebra -- */


    /* -- complex operations -- */


    /* -- member field getters -- */

    /**
     * This method returns the real part of the complex number.
     *
     * @return  The real part.
     */
    public double getReal()
    {
        return re;
    }

    /**
     * This method returns the imaginary part of the complex number.
     *
     * @return  The imaginary part.
     */
    public double getImag()
    {
        return im;
    }

    /* -- printers -- */

    /**
     * This method prints the complex number.
     */
    public void print()
    {
        System.out.printf("C = %.2f + %.2fi\n", re, im);
    }
}
