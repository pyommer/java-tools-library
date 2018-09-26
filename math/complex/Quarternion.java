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
     * The specified quarternion number constructor.
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

    /**
     * This method returns a quarternion number with the value of the sum of
     * this quarternion number and the specified quarternion number.
     *
     * @param b The quarternion number to add to this quarternion number.
     * @return  The quarternion number of this+b.
     */
    public Quarternion add(Quarternion b)
    {
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
            imag[i] = im[i] + b.getImag(i);
        return new Quarternion(re + b.getReal(), imag);
    }

    /**
     * This method returns a quarternion number with the value of the difference
     * of this quarternion number and the specified quarternion number.
     *
     * @param b The quarternion number to subtract from this quarternion number.
     * @return  The quarternion number of this-b.
     */
    public Quarternion sub(Quarternion b)
    {
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
            imag[i] = im[i] - b.getImag(i);
        return new Quarternion(re - b.getReal(), imag);
    }

    /**
     * This method returns a quarternion number with the value of the product
     * of this quarternion number and the specified quarternion number.
     *
     * @param b The quarternion number to multiply this quarternion number by.
     * @return  The quarternion number of this*b.
     */
    public Quarternion multiply(Quarternion b)
    {
        double rr = re*b.getReal();
        for (int i=0; i<im.length; i++)
            rr -= im[i]*b.getImag(i);

        double[] ii = new double[im.length];
        ii[0] = re*b.getImag(0) + im[0]*b.getReal() + im[1]*b.getImag(2) - im[2]*b.getImag(1);
        ii[1] = re*b.getImag(1) - im[0]*b.getImag(2) + im[1]*b.getReal() + im[2]*b.getImag(0);
        ii[2] = re*b.getImag(2) + im[0]*b.getImag(1) - im[1]*b.getImag(0) + im[2]*b.getReal();

        return new Quarternion(rr, ii);
    }

    /**
     * This method returns a quarternion number with the value of the quotient
     * of this quarternion number and the specified quarternion number.
     *
     * @param b The quarternion number to divide this quarternion number by.
     * @return  The quarternion number of this/b.
     */
    public Quarternion divide(Quarternion b)
    {
        return multiply(b.reciprocal());
    }

    /**
     * This method returns a quarternion number with the value of this
     * quarternion number scaled by the specified value.
     *
     * @param a The value to scale this quarternion number by.
     * @return  The quarternion number of this*a.
     */
    public Quarternion scale(double a)
    {
        double[] ia = new double[im.length];
        for (int i=0; i<im.length; i++)
            ia[i] = im[i]*a;
        return new Quarternion(re*a, ia);
    }

    /* -- quarternion operations -- */

    /**
     * This method returns a quarternion number with the value of the
     * conjugate of this quarternion number.
     *
     * @return  The quarternion number of this^*.
     */
    public Quarternion conjugate()
    {
        double[] ia = new double[im.length];
        for (int i=0; i<im.length; i++)
            ia[i] = -1.0*im[i];
        return new Quarternion(re, ia);
    }

    /**
     * This method returns a quarternion number with the value of the
     * reciprocal of this quarternion number.
     *
     * @return  The quarternion number of this^-1.
     */
    public Quarternion reciprocal()
    {
        double value = re*re;
        for (double i : im)
            value += i*i;
        return conjugate().scale(1.0/value);
    }

    /**
     * This method returns the dot product of this quarternion with the
     * specified quarternion.
     *
     * @param b The quarternion to dot product with.
     * @return  The dot product this dot b.
     */
    public double dot(Quarternion b)
    {
        double value = re*b.getReal();
        for (int i=0; i<im.length; i++)
            value += im[i]*b.getImag(i);
        return value;
    }

    /**
     * This method returns the normalized quarternion.
     *
     * @return  The normalized quarternion of this quarternion.
     */
    public Quarternion normalize()
    {
        if (abs() == 0.0)
            return null;
        return scale(1.0/abs());
    }

    /**
     * This method returns the absolute value of this quarternion number. Also
     * used for modulus, magnitude, and norm.
     *
     * @return  The absolute value of this quarternion number.
     */
    public double abs()
    {
        double value = re*re;
        for (double i : im)
            value += i*i;
        return Math.sqrt(value);
    }

    /**
     * This method returns the phase value of this quarternion number. Also
     * used for angle and argument.
     *
     * @return  The phase value of this quarternion number.
     */
    public double phase()
    {
        return 0.0;
    }

    /**
     * This method returns the quarternion exponential of this quarternion
     * number.
     *
     * @return  The quarternion exponential of this quarternion number.
     */
    public Quarternion exp()
    {
        double er = Math.exp(re);
        double r = er;
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
        {
            r *= Math.cos(im[i]);
            imag[i] = er*Math.sin(im[i]);
        }
        return new Quarternion(r, imag);
    }

    /**
     * This method returns the quarternion sine of this quarternion number.
     *
     * @return  The quarternion sine of this quarternion number.
     */
    public Quarternion sin()
    {
        double sr = Math.sin(re);
        double cr = Math.cos(re);
        double r = sr;
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
        {
            r *= Math.cosh(im[i]);
            imag[i] = cr*Math.sinh(im[i]);
        }
        return new Quarternion(r, imag);
    }

    /**
     * This method returns the quarternion cosine of this quarternion number.
     *
     * @return  The quarternion cosine of this quarternion number.
     */
    public Quarternion cos()
    {
        double sr = -1.0*Math.sin(re);
        double cr = Math.cos(re);
        double r = cr;
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
        {
            r *= Math.cosh(im[i]);
            imag[i] = sr*Math.sinh(im[i]);
        }
        return new Quarternion(r, imag);
    }

    /**
     * This method returns the quarternion tangent of this quarternion number.
     *
     * @return  The quarternion tangent of this quarternion number.
     */
    public Quarternion tan()
    {
        return sin().divide(cos());
    }

    /* -- quarternion equality -- */

    /**
     * This method returns true if the specified quarternion number is equal
     * to this quarternion number.
     *
     * @param b The quarternion to check equality to this quarternion.
     * @return  true if the quarternion numbers are equal, false otherwise.
     */
    public Boolean equals(Quarternion b)
    {
        if (b == null)
            return false;
        if (re != b.getReal())
            return false;
        for (int i=0; i<im.length; i++)
            if (im[i] != b.getImag(i))
                return false;
        return true;
    }


    /* -- member field setters -- */

    /**
     * This method sets the real part of the quarternion number.
     *
     * @param re  The value to set the real part to.
     */
    public void setReal(double re)
    {
        this.re = re;
    }

    /**
     * This method sets the imaginary part of the quarternion number.
     *
     * @param im  The value to set the imaginary part to.
     */
    public void setImag(double[] im)
    {
        System.arraycopy(im, 0, this.im, 0, this.im.length);
    }

    /**
     * This method sets the imaginary part of the quarternion number for the
     * specified position.
     *
     * @param im  The value to set the imaginary part to.
     * @param n   The position of the imaginary part.
     */
    public void setImag(double im, int n)
    {
        if ((n >= 0) && (n < this.im.length))
        {
            this.im[n] = im;
        }
    }


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

    /* -- printers/strings -- */

    /**
     * This method prints the quarternion number.
     */
    public void print()
    {
        System.out.printf("Q = %.2f + %.2fi + %.2fj + %.2fk\n", re, im[0], im[1], im[2]);
    }

    /**
     * This method returns the string of this quarternion in the format:
     * a + bi + cj + dk.
     *
     * @return  The string of this quarternion.
     */
    public String toString()
    {
        String[] ijk = {"i", "j", "k"};
        StringBuilder sb;
        if (re != 0.0)
            sb = new StringBuilder(re + "");
        else
            sb = new StringBuilder("");
        for (int i=0; i<im.length; i++)
        {
            if (im[i] > 0.0)
                sb.append(" + " + im[i] + ijk[i]);
            else if (im[i] < 0.0)
                sb.append(" - " + -1.0*im[i] + ijk[i]);
        }
        return sb.toString();
    }
}

