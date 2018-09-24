package images;

import math.Matrix;
import utilities.MathUtilities;

/**
 * The Image class is used for reading, manipulating, and writing images.
 */
public class Image
{
    /* -- image transformation matrices -- */

    /* -- image member fields -- */
    private String filename;        // the image filename
    private int    height;          // the image height
    private int    width;           // the image width
    private Pixmap pixmap;          // the pixmap of the image

    /**
     * The specified image constructor for creating an image object from the
     * image with the specified filename.
     *
     * @param filename The filename of the image to read.
     */
    public Image(String filename)
    {
        this.filename = filename;
        pixmap = new Pixmap(filename);
        height = pixmap.getHeight();
        width = pixmap.getWidth();
    }

    /**
     * This method writes the pixmap pixel data as a '.png' image with the
     * specified filename.
     *
     * @param filename The filename of the image to be written.
     */
    public void writeImage(String filename)
    {
        pixmap.writeImage(filename);
    }

    /**
     * This method filters the image color by applying the specified filter
     * matrix to each pixel color value in the pixmap.
     *
     * @param f The filter matrix to apply to each pixel color value.
     */
    public void filter(Matrix f)
    {
        pixmap.filter(f);
    }

    /**
     * This method transforms the image by applying the specified transformation
     * matrix to each pixel location in the pixmap.
     *
     * @param t The transformation matrix to apply to each pixel location.
     */
    public void transform(Matrix t)
    {
        // image position matrices
        Matrix p = new Matrix(new double[]{0.0, 0.0, 1.0});    // original
        Matrix q;                                               // transformed

        // determine transformed image bounds
        int[] x = new int[4];
        int[] y = new int[4];
        q = t.cross(p);
        x[0] = (int) q.getValue(0, 0);
        y[0] = (int) q.getValue(1, 0);

        p.setValue(0, 0, width / 1.0);
        p.setValue(1, 0, 0.0);
        q = t.cross(p);
        x[1] = (int) q.getValue(0, 0);
        y[1] = (int) q.getValue(1, 0);

        p.setValue(0, 0, 0.0);
        p.setValue(1, 0, height / 1.0);
        q = t.cross(p);
        x[2] = (int) q.getValue(0, 0);
        y[2] = (int) q.getValue(1, 0);

        p.setValue(0, 0, width / 1.0);
        p.setValue(1, 0, height / 1.0);
        q = t.cross(p);
        x[3] = (int) q.getValue(0, 0);
        y[3] = (int) q.getValue(1, 0);

        int h = MathUtilities.max(y) - MathUtilities.min(y);
        int w = MathUtilities.max(x) - MathUtilities.min(x);

        t.print();
        System.out.println();

        System.out.println("Old bounds: {w=" + width + ", h=" + height + "}");
        System.out.println("New bounds: {w=" + w + ", h=" + h + "}");
        System.out.println();

        int u;  // f1(x,y)
        int v;  // f2(x,y)

        // create transformed pixmap
        Pixmap n = new Pixmap(h, w);

        // compute new pixel locations
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                p.setValue(0, 0, j);        // x
                p.setValue(1, 0, i);        // y

                q = t.cross(p);                     // f(x,y)

                u = (int) q.getValue(1, 0);
                v = (int) q.getValue(0, 0);

                n.setPixel(u, v, pixmap.getPixel(i, j));
            }
        }

        // inverse transform to original pixmap
        t = t.inverse();

        t.print();
        System.out.println();

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                p.setValue(0, 0, j);        // x
                p.setValue(1, 0, i);        // y

                q = t.cross(p);                     // f(x,y)

                u = (int) q.getValue(1, 0);
                v = (int) q.getValue(0, 0);

                n.setPixel(i, j, pixmap.getPixel(u, v));
            }
        }

        // update this pixmap
        pixmap = n;
    }
}

