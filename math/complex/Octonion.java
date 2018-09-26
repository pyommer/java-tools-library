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
     * The specified octonion number constructor.
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

    /**
     * This method returns a octonion number with the value of the sum of
     * this octonion number and the specified octonion number.
     *
     * @param b The octonion number to add to this octonion number.
     * @return  The octonion number of this+b.
     */
    public Octonion add(Octonion b)
    {
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
            imag[i] = im[i] + b.getImag(i);
        return new Octonion(re + b.getReal(), imag);
    }

    /**
     * This method returns a octonion number with the value of the difference
     * of this octonion number and the specified octonion number.
     *
     * @param b The octonion number to subtract from this octonion number.
     * @return  The octonion number of this-b.
     */
    public Octonion sub(Octonion b)
    {
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
            imag[i] = im[i] - b.getImag(i);
        return new Octonion(re - b.getReal(), imag);
    }

    /**
     * This method returns a octonion number with the value of the product
     * of this octonion number and the specified octonion number.
     *
     * @param b The octonion number to multiply this octonion number by.
     * @return  The octonion number of this*b.
     */
    public Octonion multiply(Octonion b)
    {
        double[] a1 = new double[] {im[0], im[1], im[2]};
        double[] a2 = new double[] {im[4], im[5], im[6]};
        double[] b1 = new double[] {b.getImag(0), b.getImag(1), b.getImag(2)};
        double[] b2 = new double[] {b.getImag(4), b.getImag(5), b.getImag(6)};
        Quarternion x1 = new Quarternion(re, a1);
        Quarternion x2 = new Quarternion(im[3], a2);
        Quarternion y1 = new Quarternion(b.getReal(), b1);
        Quarternion y2 = new Quarternion(b.getImag(3), b2);

        Quarternion z1 = x1.multiply(y1).sub(y2.multiply(x2.conjugate()));
        Quarternion z2 = x1.conjugate().multiply(y2).add(y1.multiply(x2));

        int len = z1.getImag().length;
        double[] imag = new double[im.length];
        for (int i=0; i<len; i++)
        {
            imag[i] = z1.getImag(i);
        }
        imag[len] = z2.getReal();
        for (int i=0; i<len; i++)
        {
            imag[len+i+1] = z2.getImag(i);
        }
        return new Octonion(z1.getReal(), imag);
    }

    /**
     * This method returns a octonion number with the value of the quotient
     * of this octonion number and the specified octonion number.
     *
     * @param b The octonion number to divide this octonion number by.
     * @return  The octonion number of this/b.
     */
    public Octonion divide(Octonion b)
    {
        return multiply(b.reciprocal());
    }

    /**
     * This method returns a octonion number with the value of this
     * octonion number scaled by the specified value.
     *
     * @param a The value to scale this octonion number by.
     * @return  The octonion number of this*a.
     */
    public Octonion scale(double a)
    {
        double[] ia = new double[im.length];
        for (int i=0; i<im.length; i++)
            ia[i] = im[i]*a;
        return new Octonion(re*a, ia);
    }

    /* -- octonion operations -- */

    /**
     * This method returns a octonion number with the value of the
     * conjugate of this octonion number.
     *
     * @return  The octonion number of this^*.
     */
    public Octonion conjugate()
    {
        double[] ia = new double[im.length];
        for (int i=0; i<im.length; i++)
            ia[i] = -1.0*im[i];
        return new Octonion(re, ia);
    }

    /**
     * This method returns a octonion number with the value of the
     * reciprocal of this octonion number.
     *
     * @return  The octonion number of this^-1.
     */
    public Octonion reciprocal()
    {
        double value = re*re;
        for (double i : im)
            value += i*i;
        return conjugate().scale(1.0/value);
    }

    /**
     * This method returns the dot product of this octonion with the
     * specified octonion.
     *
     * @param b The octonion to dot product with.
     * @return  The dot product this dot b.
     */
    public double dot(Octonion b)
    {
        double value = re*b.getReal();
        for (int i=0; i<im.length; i++)
            value += im[i]*b.getImag(i);
        return value;
    }

    /**
     * This method returns the normalized octonion.
     *
     * @return  The normalized octonion of this octonion.
     */
    public Octonion normalize()
    {
        if (abs() == 0.0)
            return null;
        return scale(1.0/abs());
    }

    /**
     * This method returns the absolute value of this octonion number. Also
     * used for modulus, magnitude, and norm.
     *
     * @return  The absolute value of this octonion number.
     */
    public double abs()
    {
        double value = re*re;
        for (double i : im)
            value += i*i;
        return Math.sqrt(value);
    }

    /**
     * This method returns the phase value of this octonion number. Also
     * used for angle and argument.
     *
     * @return  The phase value of this octonion number.
     */
    public double phase()
    {
        return 0.0;
    }

    /**
     * This method returns the octonion exponential of this octonion
     * number.
     *
     * @return  The octonion exponential of this octonion number.
     */
    public Octonion exp()
    {
        double er = Math.exp(re);
        double r = er;
        double[] imag = new double[im.length];
        for (int i=0; i<im.length; i++)
        {
            r *= Math.cos(im[i]);
            imag[i] = er*Math.sin(im[i]);
        }
        return new Octonion(r, imag);
    }

    /**
     * This method returns the octonion sine of this octonion number.
     *
     * @return  The octonion sine of this octonion number.
     */
    public Octonion sin()
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
        return new Octonion(r, imag);
    }

    /**
     * This method returns the octonion cosine of this octonion number.
     *
     * @return  The octonion cosine of this octonion number.
     */
    public Octonion cos()
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
        return new Octonion(r, imag);
    }

    /**
     * This method returns the octonion tangent of this octonion number.
     *
     * @return  The octonion tangent of this octonion number.
     */
    public Octonion tan()
    {
        return sin().divide(cos());
    }

    /* -- octonion equality -- */

    /**
     * This method returns true if the specified octonion number is equal to
     * this octonion number.
     *
     * @param b The octonion to check equality to this octonion.
     * @return  true if the octonion numbers are equal, false otherwise.
     */
    public Boolean equals(Octonion b)
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
     * This method sets the real part of the octonion number.
     *
     * @param re  The value to set the real part to.
     */
    public void setReal(double re)
    {
        this.re = re;
    }

    /**
     * This method sets the imaginary part of the octonion number.
     *
     * @param im  The value to set the imaginary part to.
     */
    public void setImag(double[] im)
    {
        System.arraycopy(im, 0, this.im, 0, this.im.length);
    }

    /**
     * This method sets the imaginary part of the octonion number for the
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

    /* -- printers/strings -- */

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

    /**
     * This method returns the string of this octonion in the format:
     * a + be1 + ce2 + de3 +fe4 + ge5 + he6 + ie7.
     *
     * @return  The string of this quarternion.
     */
    public String toString()
    {
        String[] e = {"e1", "e2", "e3", "e4", "e5", "e6", "e7"};
        StringBuilder sb;
        if (re != 0.0)
            sb = new StringBuilder(re + "");
        else
            sb = new StringBuilder("");
        for (int i=0; i<im.length; i++)
        {
            if (im[i] > 0.0)
                sb.append(" + " + im[i] + e[i]);
            else if (im[i] < 0.0)
                sb.append(" - " + -1.0*im[i] + e[i]);
        }
        return sb.toString();
    }
}

