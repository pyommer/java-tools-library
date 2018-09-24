package math.distributions;

/**
 * The GeometricRV class implements a RandomVariable corresponding to the
 * functions defined by a geometric distribution.
 *
 * The distribution of a geometric random variable describes the number of
 * Bernoulli trials needed to get one success and has the property of
 * being memoryless.
 *
 * Discrete random variable.
 */
public class GeometricRV implements RandomVariable<Integer>
{
	/* -- member fields -- */
	private double p;           // the probability of a success

	/**
	 * The specified constructor for creating a geometric random variable
	 * with the specified probability of success in each Bernoulli trial.
	 *
	 * @param p The probability of success for each Bernoulli trial.
	 */
	public GeometricRV(double p)
	{
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
		return new Integer[] {1, null};
	}

	/**
	 * This method returns the expectation of this random variable.
	 *
	 * @return  The expectation value of this random variable.
	 */
	public double expectation()
	{
		return 1.0/p;
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return (1.0 - p)/(p*p);
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
		return 1.0 - Math.pow(1.0 - p, x/1.0);
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
		return p*Math.pow(1.0 - p, (x - 1)/1.0);
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
		double x1 = Math.exp(x/1.0);
		double x2 = 1.0 - ((1.0 - p)*x1);
		return p*x1/x2;
	}
}
