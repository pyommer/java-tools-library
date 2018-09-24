package math.distributions;

/**
 * The UniformDRV class implements a RandomVariable corresponding to the
 * functions defined by a discrete uniform distribution.
 *
 * The distribution of a uniform random variable has the property that all
 * intervals of the same length on the distribution's support are equally
 * probable.
 *
 * Discrete random variable.
 */
public class UniformDRV implements RandomVariable<Integer>
{
    /* -- member fields -- */
    private int n;              // the number of intervals
    private int a;              // the lower bound
    private int b;              // the upper bound

    /**
     * The specified constructor for creating a uniform random variable with
     * a specified number of possible values.
     *
     * @param n The number of intervals in this uniform random variable.
     */
    public UniformDRV(int n)
    {
        this.n = n;
        a = 0;
        b = n;
    }

    /**
     * The specified constructor for creating a uniform random variable with
     * a specified upper and lower bound of possible values.
     *
     * @param a The lower bound of this uniform random variable.
     * @param b The upper bound of this uniform random variable.
     */
    public UniformDRV(int a, int b)
    {
        this.a = a;
        this.b = b;
        n = b - a + 1;
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
        return new Integer[] {a, b};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return (a + b)/2.0;
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return (n*n - 1)/12.0;
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
        return (x/1.0)/(n/1.0);
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
        return 1.0/n;
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
        double x1 = Math.exp((a*x)/1.0);
        double x2 = Math.exp(-1.0*(b + 1)*(x/1.0));
        double x3 = (x/1.0)*((b - a)/1.0);
        return (x1 - x2)/x3;
    }
}
