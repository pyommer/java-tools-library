package math.distributions;

import utilities.MathUtilities;

/**
 * The BinomialRV class implements a RandomVariable corresponding to the
 * functions defined by a binomial distribution.
 *
 * The distribution of a binomial random variable describes the discrete
 * probability distribution of the number of successes in a sequence of
 * independent yes/no experiments, each of which yeilds success with the
 * same probablity.
 *
 * Discrete random variable.
 */
public class BinomialRV implements RandomVariable<Integer>
{
    /* -- member fields -- */
    private int    n;           // the number of events
    private double p;           // the probability of a success

    /**
     * The specified constructor for creating a binomial random variable
     * for a specified number of events, all with a specified probability
     * of success.
     *
     * @param n The number of events in this binomial random variable.
     * @param p The probability of success of each event.
     */
    public BinomialRV(int n, double p)
    {
        this.n = n;
        this.p = p;
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
        return new Integer[] {0, n};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return p*(n/1.0);
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return expectation()*(1.0 - p);
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
        double x1 = 0.0;
        for (int i=0; i<=x; i++)
            x1 += pdf(i);
        return x1;
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
        double x1 = MathUtilities.chooseIterative(n, x)/1.0;
        double x2 = Math.pow(p, x/1.0);
        double x3 = Math.pow(1.0 - p, (n - x)/1.0);
        return x1*x2*x3;
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
        double x1 = p*Math.exp(x/1.0);
        double x2 = 1.0 - p;
        return Math.pow(x1 + x2, n/1.0);
    }
}
