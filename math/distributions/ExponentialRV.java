package math.distributions;

/**
 * The ExponentialRV class implements a RandomVariable corresponding to the
 * functions defined by an exponential distribution.
 *
 * The distribution of an exponential random variable describes the amount
 * of time until some specific event occurs, starting from now, being
 * memoryless.
 *
 * Continuous random variable.
 */
public class ExponentialRV implements RandomVariable<Double>
{
    /* -- member fields -- */
    private double l;           // the mean probability (lambda) of the event

    /**
     * The specified constructor for creating an exponential random variable
     * with the specified mean probability of an event occurring.
     *
     * @param l The mean probability (lambda) of the event occurring.
     */
    public ExponentialRV(double l)
    {
        this.l = l;
    }

    /* -- implemented functions -- */

    /**
     * This method returns the lower and upper bounds of this random
     * variable.
     *
     * @return  The min and max possible values of this random variable.
     */
    public Double[] bounds()
    {
        return new Double[] {0.0, null};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return 1.0/l;
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return expectation()/l;
    }

    /**
     * This method returns the cumulative distribution function of this
     * random variable for the specified value.
     *
     * @param x The value of the random variable to get the CDF for.
     * @return  The CDF of this random variable.
     */
    public double cdf(Double x)
    {
        if (x < 0.0)
            return 0.0;
        return 1.0 - Math.exp(-1.0*l*x);
    }

    /**
     * This method returns the probability density function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the PDF for.
     * @return  The PDF of this random variable.
     */
    public double pdf(Double x)
    {
        if (x < 0.0)
            return 0.0;
        return l*Math.exp(-1.0*l*x);
    }

    /**
     * This method returns the moment generating function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the MGF for.
     * @return  The MGF of this random variable.
     */
    public double mgf(Double x)
    {
        return l/(l - x);
    }
}
