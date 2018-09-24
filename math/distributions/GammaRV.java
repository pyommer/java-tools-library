package math.distributions;

/**
 * The GammaRV class implements a RandomVariable corresponding to the
 * functions defined by a gamma distribution.
 *
 * The distribution of a gamma random variable describes the sum of
 * independent exponentially distributed random variables, each of which
 * has a mean equivalent to a rate parameter of the inverse.
 *
 * Continuous random variable.
 */
public class GammaRV implements RandomVariable<Double>
{
	/* -- member fields -- */
	private int    k;           // the number of exponential random variables
	private double t;           // the mean (theta) for each exponential RV

	/**
	 * The specified constructor for creating a gamma random variable for
	 * the specified number of independent exponentially distributed random
	 * variables with the specified mean value of each exponential random
	 * variable.
	 *
	 * @param k The number of exponentially distributed random variables.
	 * @param t The mean (theta) of each exponential random variable.
	 */
	public GammaRV(int k, double t)
	{
		this.k = k;
		this.t = t;
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
		return t*(k/1.0);
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return expectation()*t;
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
		return 0.0;
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
		if (x <= 0)
			return 0.0;
		double x1 = Math.pow(t, k/1.0)*Math.pow(x/1.0, (k-1)/1.0);
		double x2 = Math.exp(-1.0*t*(x/1.0));
		double x3 = 1.0;    // gamma function
		return x1*x2/x3;
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
		if (t*(x/1.0) >= 1.0)
			return 0.0;

		return Math.pow(1.0 - t*(x/1.0), -1.0*k);
	}
}
