package math.distributions;

/**
 * The StandardNormalRV class implements a RandomVariable corresponding to
 * the functions defined by a standard normal distribution.
 *
 * The distribution of a standard normal random variable has the property
 * of a normal random variable with a mean of zero and variance of one.
 *
 * Continuous random variable.
 */
public class StandardNormalRV implements RandomVariable<Double>
{
	/* -- member fields -- */
	private NormalRV rv;        // the normal random variable (mean=0, var=1)

	/**
	 * The default constructor for creating a standard normal random
	 * variable.
	 */
	public StandardNormalRV()
	{
		rv = new NormalRV(0.0, 1.0);
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
		return rv.bounds();
	}

	/**
	 * This method returns the expectation of this random variable.
	 *
	 * @return  The expectation value of this random variable.
	 */
	public double expectation()
	{
		return rv.expectation();
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return rv.variance();
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
		return rv.cdf(x);
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
		return rv.pdf(x);
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
		return rv.mgf(x);
	}
}
