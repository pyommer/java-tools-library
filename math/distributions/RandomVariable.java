package math.distributions;

/**
 * The RandomVariable interface defines functions for use with different
 * probability distributions.
 *
 * @param <T>   The type of data stored in the random variable.
 */
public interface RandomVariable<T>
{
    /**
     * This method returns the lower and upper bounds of this random
     * variable.
     *
     * @return  The min and max possible values of this random variable.
     */
    T[] bounds();

    /**
     * This method returns the expectation of this random variable.
     *
     * @return  The expectation value of this random variable.
     */
    double expectation();

    /**
     * This method returns the variance of this random variable.
     *
     * @return  The variance value of this random variable.
     */
    double variance();

    /**
     * This method returns the cumulative distribution function of this
     * random variable for the specified value.
     *
     * @param x The value of the random variable to get the CDF for.
     * @return  The CDF of this random variable.
     */
    double cdf(T x);

    /**
     * This method returns the probability density function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the PDF for.
     * @return  The PDF of this random variable.
     */
    double pdf(T x);

    /**
     * This method returns the moment generating function of this random
     * variable for the specified value.
     *
     * @param x The value of the random variable to get the MGF for.
     * @return  The MGF of this random variable.
     */
    double mgf(T x);
}
