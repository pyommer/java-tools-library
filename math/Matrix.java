package math;

/**
 * The Matrix class is used for storing and performing matrix operations
 * on matrices comprised of primitive double type values.
 */
public class Matrix
{
    private static final double SIGMA = 0.001;  // element equality threshold

    /* -- matrix member fields -- */
    private int rows;
    private int cols;
    private double[][] matrix;

    /**
     * The default matrix constructor.
     */
    public Matrix()
    {
        rows = 0;
        cols = 0;
        matrix = null;
    }

    /**
     * The default, size-specified matrix constructor. Creates a new
     * [rows x cols] zeroed matrix.
     *
     * @param rows  The number of rows of the matrix.
     * @param cols  The number of columns of the matrix.
     */
    public Matrix(int rows, int cols)
    {
        setMatrix(rows, cols);
    }

    /**
     * The default, size-specified matrix constructor. Creates a new
     * [n x n] identity matrix.
     *
     * @param n The number of rows and columns of the matrix.
     */
    public Matrix(int n)
    {
        setMatrix(n);
    }

    /**
     * The specified matrix constructor.
     *
     * @param matrix  The matrix array to copy.
     */
    public Matrix(double[][] matrix)
    {
        setMatrix(matrix);
    }

    /**
     * The specified matrix vector constructor.
     *
     * @param vector  The matrix array to copy.
     */
    public Matrix(double[] vector)
    {
        setMatrix(vector);
    }

    /**
     * The specified matrix object constructor.
     *
     * @param matrix  The matrix object to copy.
     */
    public Matrix(Matrix matrix)
    {
        setMatrix(matrix);
    }

    /**
     * This method creates a new [rows x cols] zeroed matrix.
     *
     * @param rows  The number of rows of the matrix.
     * @param cols  The number of columns of the matrix.
     */
    public void setMatrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
    }

    /**
     * This method creates a new [n x n] identity matrix.
     *
     * @param n The number of rows and columns of the matrix.
     */
    public void setMatrix(int n)
    {
        rows = n;
        cols = n;
        matrix = new double[rows][cols];
        for (int i=0; i<n; i++)
            matrix[i][i] = 1.0;
    }

    /**
     * This method sets the matrix to the values of the corresponding
     * elements in the specified matrix array.
     *
     * @param matrix  The array of matrix elements.
     */
    public void setMatrix(double[][] matrix)
    {
        rows = matrix.length;
        cols = matrix[0].length;
        this.matrix = new double[rows][cols];
        for (int i=0; i<rows; i++)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, cols);
    }

    /**
     * This method sets the matrix, as a vector matrix, to the values of
     * the corresponding elements in the specified vector array.
     *
     * @param vector  The array of matrix elements.
     */
    public void setMatrix(double[] vector)
    {
        rows = vector.length;
        cols = 1;
        this.matrix = new double[rows][cols];
        for (int i=0; i<rows; i++)
            matrix[i][0] = vector[i];
    }

    /**
     * This method sets the matrix to the values of the corresponding
     * elements in the specified matrix object.
     *
     * @param matrix  The matrix object.
     */
    public void setMatrix(Matrix matrix)
    {
        rows = matrix.getRows();
        cols = matrix.getCols();
        this.matrix = new double[rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                this.matrix[i][j] = matrix.getValue(i, j);
    }


    /* -- matrix algebra -- */

    /**
     * This method adds the value to each matrix element.
     *
     * @param value The offset to add to the matrix.
     */
    public void add(double value)
    {
        if (value == 0.0)
            return;

        // add the value to each element
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                matrix[i][j] += value;
    }

    /**
     * This method divides the matrix by the specified value.
     *
     * @param value The divisor to divide the matrix by.
     */
    public void divide(double value)
    {
        // verify non-zero
        if (value == 0.0)
        {
            System.out.println("ERROR! Attempting to divide by zero, ignoring.");
            return;
        }
        if (value == 1.0)
            return;

        // divide each element by the value
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                matrix[i][j] /= value;
    }

    /**
     * This method multiplies the matrix by the specified value.
     *
     * @param value The coefficient to multiply the matrix by.
     */
    public void multiply(double value)
    {
        if (value == 1.0)
            return;

        // multiply each element by the value
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                matrix[i][j] *= value;
    }

    /**
     * This method mods the matrix by the specified value.
     *
     * @param value The coefficient to mod the matrix by.
     */
    public void mod(double value)
    {
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                matrix[i][j] %= value;
    }

    /* -- matrix equality -- */

    /**
     * This method returns true if the two matrices contain the same values
     * in each element position of their arrays, otherwise false is returned.
     *
     * @param b The matrix to test equality.
     * @return  True if b == matrix, false otherwise.
     */
    public boolean equals(Matrix b)
    {
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                if (matrix[i][j]<b.matrix[i][j]-SIGMA ||
                        matrix[i][j]>b.matrix[i][j]+SIGMA)
                    return false;
        return true;
    }

    /* -- matrix statistics -- */

    /**
     * This method returns the sum of the elements in the matrix.
     *
     * @return  The sum of the elements in this matrix.
     */
    public double sum()
    {
        double sum = 0.0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += matrix[i][j];
        return sum;
    }

    /**
     * This method returns the magnitude of the elements in the matrix.
     *
     * @return  The magnitude of the elements in this matrix.
     */
    public double mag()
    {
        double mag = 0.0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                mag += Math.max(matrix[i][j], -matrix[i][j]);
        return mag;
    }

    /**
     * This method returns the maximum value of the matrix.
     *
     * @return  The maximum element value in this matrix.
     */
    public double max()
    {
        double max = matrix[0][0];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                if (matrix[i][j] > max)
                    max = matrix[i][j];
        return max;
    }

    /**
     * This method returns the minimum value of the matrix.
     *
     * @return  The minimum element value in this matrix.
     */
    public double min()
    {
        double min = matrix[0][0];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                if (matrix[i][j] < min)
                    min = matrix[i][j];
        return min;
    }

    /**
     * This method returns the mean average of the matrix.
     *
     * @return  The mean of the elements of this matrix.
     */
    public double mean()
    {
        return sum()/(rows*cols);
    }

    /**
     * This method returns the mode value of the matrix.
     *
     * @return  The mode of the elements of this matrix.
     */
    public double mode()
    {
        // determine counts of each element
        double[][] n = new double[rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                for (int k=0; k<rows; k++)
                    for (int l=0; l<cols; l++)
                        if (matrix[i][j] == matrix[k][l])
                            n[i][j]++;

        // determine max counted element
        double max = new Matrix(n).max();
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                if (n[i][j] == max)
                    return matrix[i][j];
        return matrix[0][0];
    }

    /* -- matrix operations -- */

    /**
     * This method returns the dot product of this matrix and matrix b.
     *
     * @param b The matrix to multiply this matrix by
     * @return  The dot product: this * b.
     */
    public double dot(Matrix b)
    {
        Matrix result = new Matrix(matrix);
        result.transpose();
        result = result.cross(b);
        return result.getValue(0, 0);
    }

    /**
     * This method returns the cross product of this matrix and matrix b.
     *
     * @param b The matrix to multiply this matrix by.
     * @return  The cross product: this x b.
     */
    public Matrix cross(Matrix b)
    {
        // verify compatible matrices
        if (cols != b.getRows())
            return null;

        // compute the cross product
        double[][] result = new double[rows][b.getCols()];
        for (int i=0; i<rows; i++)
            for (int j=0; j<b.getCols(); j++)
                for (int k=0; k<cols; k++)
                    result[i][j] += (matrix[i][k] * b.getValue(k, j));
        return new Matrix(result);
    }

    /**
     * This method returns the determinant of this matrix.
     *
     * @return  The determinant: ||this||.
     */
    public double det()
    {
        // compute determinant if [2x2] matrix
        if (rows==2 && cols==2)
            return (matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0]);

        // compute determinant if larger than [2x2]
        double result = 0.0;
        for (int i=0; i<cols; i++)
            result += (matrix[0][i] * cof(0, i).det());
        return result;
    }

    /**
     * This method returns the cofactor matrix for element position row,col
     * in this matrix.
     *
     * @param row   The row position of the element to cofactor from.
     * @param col   The column position of the element to cofactor from.
     * @return  The cofactor matrix of element at row,col.
     */
    public Matrix cof(int row,int col)
    {
        // verify row and col in matrix bounds
        if ((row<0 || row>=rows) || (col<0 || col>=cols))
            return null;

        // compute the cofactor matrix
        double[][] result = new double[rows-1][cols-1];
        for (int i=1; i<rows; i++)
            for (int j=1; j<cols; j++)
                result[i-1][j-1] = matrix[(row+i)%rows][(col+j)%cols];
        return new Matrix(result);
    }

    /**
     * This method returns the adjoint of this matrix.
     *
     * @return  The adjoint matrix of this matrix.
     */
    public Matrix adj()
    {
        double[][] result = new double[rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result[j][i] = cof(i, j).det();
        return new Matrix(result);
    }

    /**
     * This method inverts the matrix and returns true if the matrix is
     * invertible, otherwise false is returned.
     *
     * @return  True if this matrix is invertible, otherwise false.
     */
    public boolean invert()
    {
        // verify matrix is [n x n]
        if (rows != cols)
            return false;

        // compute the inverted matrix
        Matrix result = adj();
        result.divide(det());
        setMatrix(result);
        return true;
    }

    /**
     * This method returns the inverse of the matrix.
     *
     * @return  The inverse matrix of this matrix.
     */
    public Matrix inverse()
    {
        // verify matrix is [n x n]
        if (rows != cols)
            return null;

        // compute the inverse matrix
        Matrix result = adj();
        result.divide(det());
        return result;
    }

    /**
     * This method transposes this matrix.
     */
    public void transpose()
    {
        double[][] result = new double[cols][rows];
        for (int i=0; i<cols; i++)
            for (int j=0; j<rows; j++)
                result[i][j] = matrix[j][i];
        setMatrix(result);
    }

    /**
     * This method returns the transpose of the matrix.
     *
     * @return  The transpose matrix of this matrix.
     */
    public Matrix transposed()
    {
        double[][] result = new double[cols][rows];
        for (int i=0; i<cols; i++)
            for (int j=0; j<rows; j++)
                result[i][j] = matrix[j][i];
        return new Matrix(result);
    }


    /* -- member field getters -- */

    /**
     * This method returns the number of rows in the matrix.
     *
     * @return  The number of rows in this matrix.
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * This method returns the number of columns in the matrix.
     *
     * @return  The number of columns in this matrix.
     */
    public int getCols()
    {
        return cols;
    }

    /**
     * This method returns the two-dimensional array containing the matrix.
     *
     * @return  The matrix data.
     */
    public double[][] getMatrix()
    {
        return matrix;
    }

    /**
     * This method returns the value in the matrix at the specified
     * position.
     *
     * @param i The row position of the desired value.
     * @param j The column position of the desired value.
     * @return  The value at position (i,j) of this matrix.
     */
    public double getValue(int i, int j)
    {
        if (matrix == null)
        {
            System.out.println("ERROR! Matrix not set yet, returning 0.0.");
            return 0.0;
        }
        if ((i < 0) || (j < 0) || (i >= rows) || (j >= cols))
        {
            System.out.print("ERROR! Specified matrix position out of bounds: ");
            System.out.println("(i,j) = (" + i + ", " + j + ")");
            System.out.println("Returning value at (0,0) instead.");
            return matrix[0][0];
        }
        return matrix[i][j];
    }

    /**
     * This method sets the element at the specified position to the new
     * specified value.
     *
     * @param i The row of the element.
     * @param j The column of the element.
     * @param v The new value of the element.
     */
    public void setValue(int i, int j, double v)
    {
        if ((matrix == null) || ((i<0) || (i>=rows)) || ((j<0) || (j>=cols)))
            return;

        matrix[i][j] = v;
    }

    /* -- printers -- */

    /**
     * This method prints the elements of the matrix.
     */
    public void print()
    {
        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<cols; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }
}
