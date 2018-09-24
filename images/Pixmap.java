package images;

import math.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 * The Pixmap class is used for reading, manipulating, and writing images as
 * two-dimensional arrays (matrices) of Pixel objects.
 */
public class Pixmap
{
	// colorblind copunctal points in the CIE XYZ color space
	private static final double PROTAN1[] = {0.747, 0.253, 0.0};
	private static final double DEUTAN1[] = {1.08, -0.8, 0.0};
	private static final double TRITAN1[] = {0.171, 0.0, 0.0};

	// colorblind filter matrices
	private static final double PROTAN3[][] = {{0.567, 0.433, 0.000},
	                                           {0.558, 0.442, 0.000},
	                                           {0.000, 0.242, 0.478}};
	private static final double DEUTAN3[][] = {{0.625, 0.375, 0.000},
	                                           {0.700, 0.300, 0.000},
	                                           {0.000, 0.300, 0.700}};
	private static final double TRITAN3[][] = {{0.950, 0.050, 0.000},
	                                           {0.000, 0.433, 0.567},
	                                           {0.000, 0.475, 0.525}};

	public static final Matrix[] CBFM = new Matrix[]{new Matrix(PROTAN3),
	                                                  new Matrix(DEUTAN3),
	                                                  new Matrix(TRITAN3),
	                                                  new Matrix(PROTAN1),
	                                                  new Matrix(DEUTAN1),
	                                                  new Matrix(TRITAN1)};

	// recoloring modes for the pixmap
	private static final String[] MODE = {"p", "d", "t"};

	/* -- pixmap member fields -- */
	private String    filename;         // the image filename
	private int       height;           // the image height
	private int       width;            // the image width
	private int       type;             // the initial image pixel format
	private Pixel[][] pixmap;           // the pixmap as a matrix of pixels

	private HashSet<Integer> colors;    // set of distinct colors in the pixmap

	/**
	 * This method constructs a new pixmap of the specified size and
	 * initializes the pixels to zero (0x00000000).
	 *
	 * @param height  The height of the pixmap.
	 * @param width   The width of the pixmap.
	 */
	public Pixmap(int height, int width)
	{
		this.height = height;
		this.width = width;
		filename = null;
		type = BufferedImage.TYPE_INT_ARGB;
		pixmap = new Pixel[height][width];
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				pixmap[i][j] = new Pixel(0);
			}
		}
	}

	/**
	 * This method constructs a new pixmap from the image with the specified
	 * filename.
	 *
	 * @param filename The filename of the image to read.
	 */
	public Pixmap(String filename)
	{
		this.filename = filename;
		colors = null;
		readImage();
	}

	/**
	 * This method reads the image and stores the pixel values in the pixel
	 * matrix.
	 */
	public void readImage()
	{
		// open image file
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(filename));
		}
		catch (IOException e)
		{
			System.out.println("ERROR! Failed to read image: " + filename);
			return;
		}

		// initialize pixmap fields
		height = image.getHeight();
		width = image.getWidth();
		type = image.getType();
		pixmap = new Pixel[height][width];

		// verify pixels are in ARGB format
		if (type != BufferedImage.TYPE_INT_ARGB)
		{
			//System.out.println("Pixels not in ARGB format, using ARGB instead.");
			type = BufferedImage.TYPE_INT_ARGB;
		}

		// read image pixels into pixmap
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				pixmap[i][j] = new Pixel(image.getRGB(j, i));
			}
		}
	}

	/**
	 * This method returns the set of all color values, as compressed integers,
	 * contained in the pixmap.
	 *
	 * @return The set of distinct colors in the pixmap.
	 */
	public HashSet<Integer> getColors()
	{
		if (colors != null)
		{
			return colors;
		}
		colors = new HashSet<>();
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				colors.add(pixmap[i][j].getRGB());
			}
		}
		return colors;
	}

	/**
	 * This method returns the color value of the color with the largest pixel
	 * area in the pixmap.
	 *
	 * @return The color code with the largest area.
	 */
	public int getMaxColorCount()
	{
		int color = 0;
		int max   = 0;
		colors = getColors();
		for (Integer c : colors)
		{
			int x = getColorCount(c);
			if (x > max)
			{
				color = c;
			}
		}
		return color;
	}

	/**
	 * This method returns the number of times the specified color value appears
	 * in the pixmap.
	 *
	 * @param color The color value to count.
	 * @return The count of color in the pixmap.
	 */
	public int getColorCount(int color)
	{
		colors = getColors();
		int result = 0;
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if (pixmap[i][j].getRGB() == color)
				{
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * This method returns a two-dimensional array of coordinates in the pixmap
	 * of pixels that have the specified color value.
	 *
	 * @param color The color value to count.
	 * @return The coordinates of pixels with the color.
	 */
	public HashSet<int[]> getColorArea(int color)
	{
		colors = getColors();
		if (!colors.contains(color))
		{
			return null;
		}
		HashSet<int[]> result = new HashSet<>();
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if (pixmap[i][j].getRGB() == color)
				{
					result.add(new int[]{i, j});
				}
			}
		}
		return result;
	}

	/**
	 * This method returns the number of pixel colors in the pixmap and the
	 * minimum and maximum color values in an integer array.
	 *
	 * @return An array of the total count, minimum color, and maximum color.
	 */
	public int[] countColors()
	{
		colors = getColors();

		int[] result = new int[]{colors.size(), ((255 << 16) | (255 << 8) | 255), 0};
		int   n, n1, n2;

		// find max and min pixel values
		for (Integer c : colors)
		{
			n = 0;
			n1 = 0;
			n2 = 0;

			// sum pixel channel values
			for (int i = 0; i < 3; i++)
			{
				n += Pixel.getRGB(i + 1, c);
				n1 += Pixel.getRGB(i + 1, result[1]);
				n2 += Pixel.getRGB(i + 1, result[2]);
			}

			// update max and min pixel values
			if (n < n1)
			{
				result[1] = c;
			}
			if (n > n2)
			{
				result[2] = c;
			}
		}
		return result;
	}

	/**
	 * This method returns the number of red, green, or blue colored pixels,
	 * specified by the mode, and the minimum and maximum values of the color in
	 * an integer array.
	 *
	 * @param mode The specifier for color to count (1=red, 2=green, 3=blue).
	 * @return An array of the total count, minimum value, and maximum value.
	 */
	public int[] countColors(int mode)
	{
		colors = getColors();

		int[] result = new int[]{0, 255, 0};
		int   val;

		// sum pixels with color and find max and min channel values
		for (Integer c : colors)
		{
			val = Pixel.getRGB(mode, c);

			if (val > 0)
			{
				result[0]++;
			}
			if (val < result[1])
			{
				result[1] = val;
			}
			if (val > result[2])
			{
				result[2] = val;
			}
		}
		return result;
	}

	/**
	 * This method writes the pixels in the pixel matrix as a '.png' image with
	 * the specified filename.
	 *
	 * @param filename The filename of the image to be written.
	 */
	public void writeImage(String filename)
	{
		// set local array of pixmap data
		int[] local = new int[height * width];
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
			    if (pixmap[i][j] == null)
                {
                    System.out.println("ERROR! Pixmap location (" + i + ", " + j + ") is null.");
                    j = width-1;
                    i = height;
                }
				local[i * width + j] = pixmap[i][j].getRGB();
			}
		}

		// create output image
		BufferedImage image = new BufferedImage(width, height, type);
		image.setRGB(0, 0, width, height, local, 0, width);

		// write output image
		try
		{
			ImageIO.write(image, "png", new File(filename));
		}
		catch (IOException e)
		{
			System.out.println("ERROR! Failed to write image: " + filename);
		}
	}

	/**
	 * This method applies the colorblind filter matrix to each pixel in the
	 * pixel matrix corresponding to the specified mode.
	 *
	 * @param mode The mode used to determine the recoloring filter.
	 */
	public void recolorImage(String mode)
	{
		// verify mode
		if (mode == null)
		{
			mode = MODE[0];
		}

		// determine filter for mode
		Matrix filter = CBFM[0];
		for (int i = 0; i < MODE.length; i++)
		{
			if (mode.equals(MODE[i]))
			{
				filter = CBFM[i];
				break;
			}
		}

		// recolor each pixel
		filter(filter);
	}

	/**
	 * This method applies the specified recoloring filter to each pixel in
	 * the pixmap.
	 *
	 * @param filter The filter matrix to each pixel color value.
	 */
	public void filter(Matrix filter)
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				pixmap[i][j].recolorPixel(filter);
			}
		}
	}

	/* -- member field getters -- */

	/**
	 * This method returns the height of the pixmap.
	 *
	 * @return The pixmap height.
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * This method returns the width of the pixmap.
	 *
	 * @return The pixmap width.
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * This method returns the pixel format integer of the pixmap.
	 *
	 * @return The pixel format type.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * This method returns the two-dimensional array of pixels representing
	 * this pixmap.
	 *
	 * @return  The pixmap array.
	 */
	public Pixel[][] getPixmap()
	{
		return pixmap;
	}

	/**
	 * This method returns the pixel at the specified location in the pixmap.
	 *
	 * @param i The row of the desired pixel.
	 * @param j The column of the desired pixel.
	 * @return  The pixel at pixmap[i][j].
	 */
	public Pixel getPixel(int i, int j)
	{
		if ((pixmap == null) || ((i<0) || (i>=height)) || ((j<0) || (j>=width)))
			return null;

		return pixmap[i][j];
	}

	/**
	 * This method sets the pixel at the specified pixmap location to the
	 * specified pixel value.
	 *
	 * @param i The row of the desired pixel.
	 * @param j The column of the desired pixel.
	 * @param p The new pixel value.
	 */
	public void setPixel(int i, int j, Pixel p)
	{
		if ((pixmap == null) || ((i<0) || (i>=height)) || ((j<0) || (j>=width)))
			return;

		pixmap[i][j] = p;
	}
}
