package math.complex;

/**
 * The Quarternion class is used for storing and performing operations on
 * quarternion numbers comprised of primitive double type real and imaginary
 * parts.
 *
 * Quarternion numbers have the form:
 *
 *      a_0 + a_1*i + a_2*j + a_3*k,
 *
 * where i, j, and k are imaginary constants.
 *
 * Quarternion numbers are non-associative.
 */
public class Quarternion
{
    /* -- quarternion member fields -- */
    private double   re;    // the real part
    private double[] im;    // the array of imaginary values {i, j, k}

    /**
     * The default quarternion number constructor.
     */
    public Quarternion()
    {}

    /**
     * The specified complex number constructor.
     *
     * @param re The real part.
     * @param im The imaginary part.
     */
    public Quarternion(double re, double[] im)
    {
        this.re = re;
        this.im = new double[3];
        System.arraycopy(im, 0, this.im, 0, 3);
    }


    /* -- quarternion algebra -- */


    /* -- quarternion operations -- */


    /* -- member field getters -- */

    /**
     * This method returns the real part of the quarternion number.
     *
     * @return  The real part.
     */
    public double getReal()
    {
        return re;
    }

    /**
     * This method returns the imaginary part of the quarternion number.
     *
     * @return  The imaginary part.
     */
    public double[] getImag()
    {
        return im;
    }

    /**
     * This method returns the imaginary part of the quarternion number
     * at the specified index.
     *
     * @param n The index of the imaginary part to get.
     * @return  The imaginary part.
     */
    public double getImag(int n)
    {
        if ((n < 0) || (n >= im.length))
        {
            System.out.println("ERROR! Invalid imaginary part in quarternion: " + n);
            System.out.println("Returning 0.0 instead.");
            return 0.0;
        }
        return im[n];
    }

    /* -- printers -- */

    /**
     * This method prints the quarternion number.
     */
    public void print()
    {
        System.out.printf("Q = %.2f + %.2fi + %.2fj + %.2fk\n", re, im[0], im[1], im[2]);
    }
}
