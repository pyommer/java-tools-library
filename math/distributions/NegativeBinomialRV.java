package math.distributions;

import utilities.MathUtilities;

/**
 * The NegativeBinomialRV class implements a RandomVariable corresponding to
 * the functions defined by a negative binomial distribution.
 *
 * The distribution of a negative binomial random variable describes the sum
 * of independent geometrically distributed random variables, each of which
 * has the same probability of success.
 *
 * Discrete random variable.
 */
public class NegativeBinomialRV implements RandomVariable<Integer>
{
	/* -- member fields -- */
	private int    n;           // the number of geometric random variables
	private double p;           // the probability of a success for each RV

	/**
	 * The specified constructor for creating a negative binomial random
	 * variable for the specified number of independent geometrically
	 * distributed random variables with the specified probability of
	 * success for each geometric random variable.
	 *
	 * @param n The number of geometrically distributed random variables.
	 * @param p The probability of success for each geometric random variable.
	 */
	public NegativeBinomialRV(int n, double p)
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
		return new Integer[] {1, null};
	}

	/**
	 * This method returns the expectation of this random variable.
	 *
	 * @return  The expectation value of this random variable.
	 */
	public double expectation()
	{
		return (n/1.0)*(1.0 - p)/p;
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return expectation()/p;
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
		double x1 = MathUtilities.chooseIterative(x+n-1, n-1)/1.0;
		double x2 = Math.pow(p, n/1.0)*Math.pow(1.0 - p, x/1.0);
		return x1*x2;
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
		double x1 = 1.0 - (1.0 - p)*Math.exp(x/1.0);
		return Math.pow(p/x1, n/1.0);
	}
}
