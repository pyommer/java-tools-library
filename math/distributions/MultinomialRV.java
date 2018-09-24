package math.distributions;

/**
 * The MultinomialRV class implements a RandomVariable corresponding to
 * the functions defined by a multinomial distribution.
 *
 * The distribution of a multinomial random variable describes drawing balls
 * from urns with replacement
 *
 * Discrete random variable.
 */
public class MultinomialRV implements RandomVariable<Integer>
{
	/* -- member fields -- */
	private int      n;         // the number of events
	private double[] p;         // the array of success probabilities

	/**
	 * The specified constructor for creating a multinomial random variable
	 * with the specified number of trials being conducted and the specified
	 * array of probabilities corresponding to a trial success in an array
	 * of possible trials.
	 *
	 * @param n The number of trials to conduct.
	 * @param p The array of probabilities corresponding to a trial success.
	 */
	public MultinomialRV(int n, double[] p)
	{
		this.n = n;
		this.p = new double[p.length];
		for (int i=0; i<p.length; i++)
			this.p[i] = p[i];
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
		return 0.0;
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return 0.0;
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
		return 0.0;
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
		return 0.0;
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
		double x1 = 0.0;
		for (int i=0; i<p.length; i++)
			x1 += p[i]*Math.exp(x/1.0);
		return Math.pow(x1, n/1.0);
	}
}
