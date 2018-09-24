package images;

import math.Matrix;

/**
 * The Pixel class is used for storing, manipulating, and converting image pixel
 * data.
 */
public class Pixel
{
	/* -- pixform pixel formats -- */
	private static final int TYPE_RGB  = 0; // standard RGB color space
	private static final int TYPE_sRGB = 1; // gamma-corrected RGB, [0,1]
	private static final int TYPE_XYZ  = 2; // CIE XYZ color space
	private static final int TYPE_D65  = 3; // scaled XYZ so Y of D65 is 1.0
	private static final int TYPE_xyY  = 4; // CIE color space with illumination
	private static final int TYPE_LMS  = 5; // eye color space (Long,Med,Short)
	private static final int TYPE_HSV  = 6; // hue, saturation, and value

	/* -- color space transformation matrices -- */
	// the CIE standardized RGB-to-XYZ transformation matrix
	private static final double[][] RGB_XYZ  = {{0.49000, 0.31000, 0.200000},
	                                            {0.17697, 0.81240, 0.010630},
	                                            {0.00000, 0.01000, 0.990000}};
	// the CIE standardized XYZ-to-RGB transformation matrix
	private static final double[][] XYZ_RGB  = {{0.41847000, -0.1586600, -0.082835},
	                                            {-0.09116900, 0.2524300, 0.015708},
	                                            {0.00092090, -0.0025498, 0.178600}};
	// the Hunt-Pointer-Estevez CIE XYZ-to-LMS transformation matrix
	private static final double[][] XYZ_LMS  = {{0.38971, 0.68898, -0.07868},
	                                            {-0.22981, 1.18340, 0.04641},
	                                            {0.00000, 0.00000, 1.00000}};
	// the D65 normalized Hunt-Pointer-Estevez transformation matrix
	private static final double[][] XYZ_D65  = {{0.4002, 0.7076, -0.0808},
	                                            {-0.2263, 1.1653, 0.0457},
	                                            {0.0000, 0.0000, 0.9182}};
	// sRGB-to-CIE xyY transformation matrix
	private static final double[][] sRGB_xyY = {{0.6400, 0.3000, 0.1500},
	                                            {0.3300, 0.6000, 0.0600},
	                                            {0.2126, 0.7152, 0.0722}};
	// RGB-to-CIE XYZ transformation matrix
	private static final double[][] RGB2XYZ  = {{3.2406, -1.5372, -0.4986},
	                                            {-0.9689, 1.8758, 0.0415},
	                                            {0.0557, -0.204, 1.057}};
	// CIE XYZ-to-RGB transformation matrix
	private static final double[][] XYZ2RGB  = {{0.4124, 0.3576, 0.1805},
	                                            {0.2126, 0.7152, 0.0722},
	                                            {0.0193, 0.1192, 0.9505}};

	private static final Matrix[] CSTM = new Matrix[]{new Matrix(RGB2XYZ),
	                                                  new Matrix(XYZ2RGB),
	                                                  new Matrix(RGB_XYZ),
	                                                  new Matrix(XYZ_RGB),
	                                                  new Matrix(XYZ_LMS),
	                                                  new Matrix(XYZ_D65),
	                                                  new Matrix(sRGB_xyY)};

	/* -- pixel member fields -- */
	private int    rgb;             // initial RGB integer value (1-byte each)
	private int    pixform;         // current pixel format (color space)
	private Matrix pixel;           // vector of current pixel channel values

	/**
	 * This method constructs a new pixel with channel values from the specified
	 * ARGB compacted integer value.
	 *
	 * @param rgb The initial ARGB integer value of the pixel.
	 */
	public Pixel(int rgb)
	{
		this.rgb = rgb;
		pixform = TYPE_RGB;

		double[] p = new double[3];
		for (int i = 0; i < 3; i++)
		{
			p[i] = getRGB(i + 1, rgb) / 255.0;
		}

		pixel = new Matrix(p);
	}

	/**
	 * This method returns the ARGB compacted integer form (1-byte each) of the
	 * current pixel channel values, maintaining the initial alpha value.
	 *
	 * @return The ARGB integer value of the pixel channel values.
	 */
	public int getRGB()
	{
		if (pixform != TYPE_RGB)
		{
			toRGB();
		}

		int a = rgb & 0xff000000;

		int r = (int) (pixel.getValue(0, 0) * 255);
		int g = (int) (pixel.getValue(1, 0) * 255);
		int b = (int) (pixel.getValue(2, 0) * 255);

		r = ((r & 0xff) << 16) & 0x00ff0000;
		g = ((g & 0xff) << 8) & 0x0000ff00;
		b = (b & 0xff) & 0x000000ff;

		return (a | r | g | b);
	}

	/**
	 * This method returns the red, green, or blue color value, specified by the
	 * mode, of the pixel.
	 *
	 * @param mode The specifier for color value (1=red, 2=green, 3=blue).
	 * @return The color value of the pixel.
	 */
	public int getRGB(int mode)
	{
		int oft = (mode == 1) ? 16 : ((mode == 2) ? 8 : 0);
		return (getRGB() >> oft) & 0xff;
	}

	/**
	 * This method returns the red, green, or blue color value, specified by the
	 * mode, of the specified pixel integer value.
	 *
	 * @param mode  The specifier for color value (1=red, 2=green, 3=blue).
	 * @param value The pixel color value to use.
	 * @return The color value of the pixel value.
	 */
	public static int getRGB(int mode, int value)
	{
		int oft = (mode == 1) ? 16 : ((mode == 2) ? 8 : 0);
		return (value >> oft) & 0xff;
	}

	/**
	 * This method applies the specified filter matrix to the pixel to recolor
	 * the pixel by filtering the pixel channel values.
	 *
	 * @param filter The filter matrix to use for recoloring the pixel.
	 */
	public void recolorPixel(Matrix filter)
	{
		if (filter.getCols() == 3)
		{
			Matrix T = getFilter(new Matrix[]{CSTM[0], filter, CSTM[1]});
			pixel = T.cross(pixel);
		}
		else
		{
			System.out.println("Recoloring based off copunctal points.");
			/*
			Matrix X = CSTM[0].cross(pixel);
			double y = X.getValue(0,0) - filter.getValue(0,0);
			double x = X.getValue(1,0) - filter.getValue(1,0);
			*/
		}
	}

	/**
	 * This method returns the transformation filter matrix of the combined
	 * filter matrices in the specified array.
	 *
	 * @param filter The array of filter matrices to combine.
	 * @return	The transformation matrix of the combined filters in the array.
	 */
	public Matrix getFilter(Matrix[] filter)
	{
		Matrix T = filter[filter.length - 1];
		for (int i = filter.length - 2; i >= 0; i--)
		{
			T = T.cross(filter[i]);
		}
		return T;
	}

	/**
	 * This method filters the pixel by the specified filter matrix by
	 * performing the cross product: filter x pixel.
	 *
	 * @param filter The filter matrix to use for filtering the pixel.
	 */
	public void filter(Matrix filter)
	{
		pixel = filter.cross(pixel);
	}

	/**
	 * This method filters the pixel by the specified filter matrix by
	 * performing the cross product: filter x pixel.
	 *
	 * @param filter The filter matrix to use for filtering the pixel.
	 */
	public void filter(Matrix[] filter)
	{
		Matrix T = getFilter(filter);
		filter(T);
	}

	/**
	 * This method converts the pixel to the RGB color space.
	 */
	public void toRGB()
	{
		if (pixform == TYPE_RGB)
		{
			return;
		}

		if (pixform == TYPE_XYZ)
		{
			pixel = CSTM[1].cross(pixel);
			pixform = TYPE_RGB;
		}
	}

	/**
	 * This method converts the pixel to the CIE-XYZ color space.
	 */
	public void toXYZ()
	{
		if (pixform == TYPE_XYZ)
		{
			return;
		}

		if (pixform == TYPE_RGB)
		{
			pixel = CSTM[0].cross(pixel);
			pixform = TYPE_XYZ;
		}
	}

	/**
	 * This method applies the specified filter matrix to the pixel to recolor
	 * the pixel by filtering the pixel channel values.
	 *
	 * @param filter The filter matrix to use for recoloring the pixel.
	 */
	public void recolorPixel(double[][] filter)
	{
		Matrix X = new Matrix(RGB2XYZ);     // convert pixel to CIE-XYZ space
		Matrix F = new Matrix(filter);      // apply the filter
		Matrix R = new Matrix(XYZ2RGB);     // convert pixel back to RGB space

		Matrix T = R.cross(F.cross(X));

		filter(T.getMatrix());
	}

	/**
	 * This method filters the pixel by the specified filter matrix by
	 * performing the cross product: filter x pixel.
	 *
	 * @param filter The filter matrix to use for filtering the pixel.
	 */
	public void filter(double[][] filter)
	{
		double[] pix = new double[3];

		// compute the filtered pixel channel values (cross product)
		for (int i = 0; i < 3; i++)
		{
			pix[i] = 0.0;

			for (int j = 0; j < 3; j++)
			{
				pix[i] += (filter[i][j] * pixel.getValue(j, 0));
			}
		}

		// set the filtered pixel channel values
		pixel.setMatrix(pix);
	}
}
