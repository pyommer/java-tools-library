package math.distributions;

/**
 * The NormalRV class implements a RandomVariable corresponding to the
 * functions defined by a normal distribution.
 *
 * The distribution of a normal random variable describes data that cluster
 * around the mean.
 *
 * Continuous random variable.
 */
public class NormalRV implements RandomVariable<Double>
{
	/* -- member fields -- */
	private double m;           // the mean (mu)
	private double v;           // the variance (rho^2)

	/**
	 * The specified constructor for creating a normal random variable
	 * with the specified mean and variance values.
	 *
	 * @param m The mean (mu) of this normal random variable.
	 * @param v The variance (rho^2) of this normal random variable.
	 */
	public NormalRV(double m, double v)
	{
		if (v <= 0)
		{
			System.out.println("ERROR! Invalid variance of normal random variable: " + v);
			System.out.println("Using a standard normal random variable instead.");
			this.m = 0.0;
			this.v = 1.0;
		}
		else
		{
			this.m = m;
			this.v = v;
		}
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
		return new Double[] {m-v, m+v};
	}

	/**
	 * This method returns the expectation of this random variable.
	 *
	 * @return  The expectation value of this random variable.
	 */
	public double expectation()
	{
		return m;
	}

	/**
	 * This method returns the variance of this random variable.
	 *
	 * @return  The variance value of this random variable.
	 */
	public double variance()
	{
		return v;
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
		return Math.exp(-1.0*(((x-m)*(x-m))/(2.0*v)))/Math.sqrt(2.0*Math.PI*v);
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
		return Math.exp(m*x + (v*x*x/2.0));
	}
}
