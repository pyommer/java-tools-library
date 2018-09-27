package math.distributions;

import utilities.MathUtilities;

/**
 * The PoissonRV class implements a RandomVariable corresponding to the
 * functions defined by a Poisson distribution.
 *
 * The distribution of a Poisson random variable describes the probability
 * of a number of events occurring in a fixed period of time if the events
 * occur with a known average rate and independently of the time since the
 * last event.
 *
 * Discrete random variable.
 */
public class PoissonRV implements RandomVariable<Integer>
{
    /* -- member fields -- */
    private double l;           // the mean rate (lambda) of event occurrence

    /**
     * The specified constructor for creating a Poisson random variable
     * with the specified mean rate of which the event occurs.
     *
     * @param l The mean rate (lambda) of which the event occurs.
     */
    public PoissonRV(double l)
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
    public Integer[] bounds()
    {
        return new Integer[] {0, null};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return l;
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return l;
    }

    /**
     * This method returns the cumulative distribution function of this
     * random variable for the specified value.
     *
     * @param x The value of the random variable to get the CDF for.
     * @return  The CDF of this random variable.
     */
    public double cdf(Integer x)
    {
        double x1 = Math.exp(-1.0*l);
        double x2 = 0.0;
        for (int i=0; i<=x; i++)
            x2 += ((Math.pow(l, i/1.0))/(MathUtilities.factIterative(i)/1.0));
        return x1*x2;
    }

    /**
     * This method returns the probability density function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the PDF for.
     * @return  The PDF of this random variable.
     */
    public double pdf(Integer x)
    {
        double x1 = MathUtilities.factIterative(x)/1.0;
        return Math.exp(-1.0*l)*Math.pow(l, x/1.0)/x1;
    }

    /**
     * This method returns the moment generating function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the MGF for.
     * @return  The MGF of this random variable.
     */
    public double mgf(Integer x)
    {
        return Math.exp(l*(Math.exp(x/1.0) - 1.0));
    }
}
