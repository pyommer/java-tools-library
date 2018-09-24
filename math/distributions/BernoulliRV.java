package math.distributions;

/**
 * The BernoulliRV class implements a RandomVariable corresponding to the
 * functions defined by a Bernoulli distribution.
 *
 * The distribution of a Bernoulli random variable describes the discrete
 * probability distribution of a single yes/no experiment which yeilds
 * success with a known probablity.
 *
 * Discrete random variable.
 */
public class BernoulliRV implements RandomVariable<Integer>
{
	/* -- member fields -- */
	private double p;           // the probability of a success

	/**
	 * The specified constructor for creating a Bernoulli random variable
	 * with the specified probability of success in a trial.
	 *
	 * @param p The probability of a trial success.
	 */
	public BernoulliRV(double p)
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
		return new Integer[] {0, 1};
	}

	/**
	 * This method returns the expectation of this random variable.
	 *
	 * @return  The expectation value of this random variable.
	 */
	public double expectation()
	{
		return p;
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return p*(1.0 - p);
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
		return Math.pow(1.0 - p, 1.0 - x/1.0);
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
		return Math.pow(p, x/1.0)*cdf(x);
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
		return 1.0 - p + p*Math.exp(x/1.0);
	}
}
