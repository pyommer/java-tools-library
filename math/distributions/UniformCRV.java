package math.distributions;

/**
 * The UniformCRV class implements a RandomVariable corresponding to the
 * functions defined by a continuous uniform distribution.
 *
 * The distribution of a uniform random variable has the property that all
 * intervals of the same length on the distribution's support are equally
 * probable.
 *
 * Continuous random variable.
 */
public class UniformCRV implements RandomVariable<Double>
{
    /* -- member fields -- */
    private int    n;           // the number of intervals
    private double a;           // the lower bound
    private double b;           // the upper bound

    /**
     * The specified constructor for creating a uniform random variable with
     * a specified upper and lower bound of possible values.
     *
     * @param a The lower bound of this uniform random variable.
     * @param b The upper bound of this uniform random variable.
     */
    public UniformCRV(double a, double b)
    {
        this.a = a;
        this.b = b;
        n = (int) (b - a);
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
        return new Double[] {a, b};
    }

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    public double expectation()
    {
        return (a + b)/2;
    }

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    public double variance()
    {
        return (n*n)/12.0;
    }

    /**
     * This method returns the cumulative distribution function of this
     * random variable.
     *
     * @return  The CDF of this random variable.
     */
    public double cdf(Double x)
    {
        return (x/1.0)/(n/1.0);
    }

    /**
     * This method returns the probability distribution function of this
     * random variable.
     *
     * @return  The PDF of this random variable.
     */
    public double pdf(Double x)
    {
        return 1.0/n;
    }

    /**
     * This method returns the moment generating function of this random
     * variable.
     *
     * @return  The MGF of this random variable.
     */
    public double mgf(Double x)
    {
        double x1 = Math.exp(x*a);
        double x2 = Math.exp(-1.0*(b + 1)*x);
        double x3 = x*(b - a);
        return (x1 - x2)/x3;
    }
}
