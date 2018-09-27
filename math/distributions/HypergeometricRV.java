package math.distributions;

import utilities.MathUtilities;

/**
 * The HypergeometricRV class implements a RandomVariable corresponding to
 * the functions defined by a hypergeometric distribution.
 *
 * The distribution of a hypergeometric random variable describes drawing
 * balls from urns without replacement.
 *
 * Discrete random variable.
 */
public class HypergeometricRV implements RandomVariable<Integer>
{
    /* -- member fields -- */
    private int r;              // the total number of success states
    private int m;              // the size of each draw
    private int n;              // the size of the population

    /**
     * The specified constructor for creating a hypergeometric random
     * variable with the specified number of success states and draw size
     * from a population of the specified size.
     *
     * @param r The number of success states in the population.
     * @param m The number of states (size) in each draw.
     * @param n The number of states (size) in the population.
     */
    public HypergeometricRV(int r, int m, int n)
    {
        System.out.printf("Random variable for %d possible success states with draws of size %d from a population of size %d.\n\n", r, m, n);
        this.r = r;
        this.m = m;
        this.n = n;
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
        return new Integer[] {0, r};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return (r*m/(n/1.0));
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return (expectation()/(n*(n-1)/1.0))*((n - r)/1.0)*((n - m)/1.0);
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
        double x1 = x/1.0 - expectation();
        double x2 = Math.sqrt(expectation()*(1.0 - expectation()/n));
        return x1/x2;
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
        double x1 = MathUtilities.chooseIterative(m, x)/1.0;
        double x2 = MathUtilities.chooseIterative(m - x, r - x)/1.0;
        double x3 = MathUtilities.chooseIterative(n, x)/1.0;
        return x1*x2/x3;
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
        return 0.0;
    }
}
