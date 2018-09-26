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

    /**
     * This method returns a complex number with the value of the sum of this
     * complex number and the specified complex number.
     *
     * @param b The complex number to add to this complex number.
     * @return  The complex number of this+b.
     */
    public Complex add(Complex b)
    {
        return new Complex(re + b.getReal(), im + b.getImag());
    }

    /**
     * This method returns a complex number with the value of the difference
     * of this complex number and the specified complex number.
     *
     * @param b The complex number to subtract from this complex number.
     * @return  The complex number of this-b.
     */
    public Complex sub(Complex b)
    {
        return new Complex(re - b.getReal(), im - b.getImag());
    }

    /**
     * This method returns a complex number with the value of the product of
     * this complex number and the specified complex number.
     *
     * @param b The complex number to multiply this complex number by.
     * @return  The complex number of this*b.
     */
    public Complex multiply(Complex b)
    {
        double rr = re*b.getReal();
        double ii = im*b.getImag();
        return new Complex(rr - ii, rr + ii);
    }

    /**
     * This method returns a complex number with the value of the quotient of
     * this complex number and the specified complex number.
     *
     * @param b The complex number to divide this complex number by.
     * @return  The complex number of this/b.
     */
    public Complex divide(Complex b)
    {
        return multiply(b.reciprocal());
    }

    /**
     * This method returns a complex number with the value of this complex
     * number scaled by the specified value.
     *
     * @param a The value to scale this complex number by.
     * @return  The complex number of this*a.
     */
    public Complex scale(double a)
    {
        return new Complex(re*a, im*a);
    }

    /* -- complex operations -- */

    /**
     * This method returns a complex number with the value of the conjugate
     * of this complex number.
     *
     * @return  The complex number of this^*.
     */
    public Complex conjugate()
    {
        return new Complex(re, -1.0*im);
    }

    /**
     * This method returns a complex number with the value of the reciprocal
     * of this complex number.
     *
     * @return  The complex number of this^-1.
     */
    public Complex reciprocal()
    {
        double scale = re*re + im*im;
        return new Complex(re/scale, -1.0*im/scale);
    }

    /**
     * This method returns the absolute value of this complex number. Also
     * used for modulus and magnitude.
     *
     * @return  The absolute value of this complex number.
     */
    public double abs()
    {
        return Math.hypot(re, im);
    }

    /**
     * This method returns the phase value of this complex number. Also used
     * for angle and argument.
     *
     * @return  The phase value of this complex number.
     */
    public double phase()
    {
        return Math.atan2(im, re);
    }

    /**
     * This method returns the complex exponential of this complex number.
     *
     * @return  The complex exponential of this complex number.
     */
    public Complex exp()
    {
        double er = Math.exp(re);
        return new Complex(er*Math.cos(im), er*Math.sin(im));
    }

    /**
     * This method returns the complex sine of this complex number.
     *
     * @return  The complex sine of this complex number.
     */
    public Complex sin()
    {
        double sr = Math.sin(re);
        double cr = Math.cos(re);
        return new Complex(sr*Math.cosh(im), cr*Math.sinh(im));
    }

    /**
     * This method returns the complex cosine of this complex number.
     *
     * @return  The complex cosine of this complex number.
     */
    public Complex cos()
    {
        double sr = -1.0*Math.sin(re);
        double cr = Math.cos(re);
        return new Complex(cr*Math.cosh(im), sr*Math.sinh(im));
    }

    /**
     * This method returns the complex tangent of this complex number.
     *
     * @return  The complex tangent of this complex number.
     */
    public Complex tan()
    {
        return sin().divide(cos());
    }

    /* -- complex equality -- */

    /**
     * This method returns true if the specified complex number is equal to
     * this complex number.
     *
     * @param b The complex number to check equality to this complex number.
     * @return  true if the complex numbers are equal, false otherwise.
     */
    public Boolean equals(Complex b)
    {
        if (b == null)
            return false;
        return ((re == b.getReal()) && (im == b.getImag()));
    }


    /* -- member field setters -- */

    /**
     * This method sets the real part of the complex number.
     *
     * @param re  The value to set the real part to.
     */
    public void setReal(double re)
    {
        this.re = re;
    }

    /**
     * This method sets the imaginary part of the complex number.
     *
     * @param im  The value to set the imaginary part to.
     */
    public void setImag(double im)
    {
        this.im = im;
    }


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

    /* -- printers/strings -- */

    /**
     * This method prints the complex number.
     */
    public void print()
    {
        System.out.printf("C = %.2f + %.2fi\n", re, im);
    }

    /**
     * This method returns the complex number as a string in the format:
     * a + bi.
     * @return  The complex number as a string.
     */
    public String toString()
    {
        if (im == 0.0)
            return re + "";
        if (re == 0.0)
            return im + "i";
        if (im < 0.0)
            return re + " - " + (-1.0*im) + "i";
        return re + " + " + im + "i";
    }


    /* -- static operations -- */

    /**
     * This method returns the complex sum of the specified complex numbers.
     *
     * @param a The first complex number to add.
     * @param b The second complex number to add.
     * @return  The complex number of the sum a+b.
     */
    public static Complex add(Complex a, Complex b)
    {
        return a.add(b);
    }

    /**
     * This method returns the complex difference of the specified complex
     * numbers.
     *
     * @param a The first complex number to be subtracted.
     * @param b The second complex number to subtract by.
     * @return  The complex number of the sum a-b.
     */
    public static Complex sub(Complex a, Complex b)
    {
        return b.sub(b);
    }

    /**
     * This method returns the complex product of the specified complex
     * numbers.
     *
     * @param a The first complex number to multiply.
     * @param b The second complex number to multiply.
     * @return  The complex number of the sum a*b.
     */
    public static Complex multiply(Complex a, Complex b)
    {
        return a.multiply(b);
    }

    /**
     * This method returns the complex quotient of the specified complex
     * numbers.
     *
     * @param a The first complex number to be divided.
     * @param b The second complex number to divide by.
     * @return  The complex number of the quotient a/b.
     */
    public static Complex divide(Complex a, Complex b)
    {
        return a.divide(b);
    }


    /**
     * This method returns the complex scaled value of the specified complex
     * number and the specified scale value.
     *
     * @param a The complex number to scale.
     * @param b The value to scale by.
     * @return  The complex number of a*b.
     */
    public static Complex scale(Complex a, double b)
    {
        return a.scale(b);
    }

    /**
     * This method returns the complex conjugate of the specified complex
     * number.
     *
     * @param a The complex number to conjugate.
     * @return  The complex number of the conjugate a^*.
     */
    public static Complex conjugate(Complex a)
    {
        return a.conjugate();
    }

    /**
     * This method returns the complex reciprocal of the specified complex
     * number.
     *
     * @param a The complex number to reciprocate.
     * @return  The complex number of the reciprocal a^-1.
     */
    public static Complex reciprocal(Complex a)
    {
        return a.reciprocal();
    }


    /**
     * This method returns the complex exponential of the specified complex
     * number.
     *
     * @param a The complex number to exponential.
     * @return  The complex number of the exponential of a.
     */
    public static Complex exp(Complex a)
    {
        return a.exp();
    }

    /**
     * This method returns the complex sine of the specified complex number.
     *
     * @param a The complex number to sine.
     * @return  The complex number of the sine of a.
     */
    public static Complex sin(Complex a)
    {
        return a.sin();
    }

    /**
     * This method returns the complex cosine of the specified complex number.
     *
     * @param a The complex number to cosine.
     * @return  The complex number of the cosine of a.
     */
    public static Complex cos(Complex a)
    {
        return a.cos();
    }

    /**
     * This method returns the complex tangent of the specified complex
     * number.
     *
     * @param a The complex number to tangent.
     * @return  The complex number of the tangent of a.
     */
    public static Complex tan(Complex a)
    {
        return a.tan();
    }
}

