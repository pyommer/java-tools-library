package math.complex;

/**
 * The Octonion class is used for storing and performing operations on
 * octonion numbers comprised of primitive double type real and imaginary
 * parts.
 *
 * Octonion numbers have the form:
 *
 *  a_0 + a_1*e_1 + a_2*e_2 + a_3*e_3 + a_4*e_4 + a_5*e_5 + a_6*e_6 + a_7*e_7,
 *
 * where {e_1, e_2, ..., e_7} are imaginary constants.
 *
 * Octonion numbers are non-associative and non-communitive.
 */
public class Octonion
{
    /* -- octonion member fields -- */
    private double   re;    // the real part
    private double[] im;    // the array imaginary values {e_1, ..., e_7}

    /**
     * The default octonion number constructor.
     */
    public Octonion()
    {}

    /**
     * The specified complex number constructor.
     *
     * @param re The real part.
     * @param im The imaginary part.
     */
    public Octonion(double re, double[] im)
    {
        this.re = re;
        this.im = new double[7];
        System.arraycopy(im, 0, this.im, 0, 7);
    }


    /* -- octonion algebra -- */


    /* -- octonion operations -- */


    /* -- member field getters -- */

    /**
     * This method returns the real part of the octonion number.
     *
     * @return  The real part.
     */
    public double getReal()
    {
        return re;
    }

    /**
     * This method returns the imaginary part of the octonion number.
     *
     * @return  The imaginary part.
     */
    public double[] getImag()
    {
        return im;
    }

    /**
     * This method returns the imaginary part of the octonion number
     * at the specified index.
     *
     * @param n The index of the imaginary part to get.
     * @return  The imaginary part.
     */
    public double getImag(int n)
    {
        if ((n < 0) || (n >= im.length))
        {
            System.out.println("ERROR! Invalid imaginary part in octonion: " + n);
            System.out.println("Returning 0.0 instead.");
            return 0.0;
        }
        return im[n];
    }

    /* -- printers -- */

    /**
     * This method prints the octonion number.
     */
    public void print()
    {
        System.out.printf("O = %.2f", re);
        for (int i=0; i<im.length; i++)
            System.out.printf(" + %.2fe_%d", im[i], i+1);
        System.out.println();
    }
}
