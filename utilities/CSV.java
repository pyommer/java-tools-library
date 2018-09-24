package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The CSV class is used for reading, storing, and retrieving the data in
 * a '.csv' document.
 */
public class CSV
{
    /* -- CSV member fields -- */
    private int rows;               // the number of rows in the csv sheet
    private int cols;               // the number of columns in the csv
    private String filename;        // the csv filename
    private String[] title;         // array of the first csv row (col titles)
    private ArrayList<String> csv;  // the rows of the csv file in a list

    /**
     * The constructor for a csv file sheet object.
     *
     * @param filename The name of the csv file to be initialized.
     */
    public CSV(String filename)
    {
        csv = new ArrayList<>();
        readCSV(filename);
    }

    /**
     * This method returns the string value of the data in the specified
     * row,col cell of the csv sheet.
     *
     * @param filename The name of the csv file to read in.
     */
    public void readCSV(String filename)
    {
        if (filename == null)
        {
            System.out.println("ERROR! Null filename received.");
            System.exit(1);
        }

        // set the csv filename
        this.filename = filename;

        // open the csv file
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(filename));
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to open csv file '%s' for reading.\n\n", filename);
            System.exit(1);
        }

        // read the csv title line
        String line = null;
        try
        {
            line = in.readLine();
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to read title line of csv file '%s'.\n\n", filename);
            System.exit(1);
        }

        // verify title line and set title and cols
        if (!line.equals(""))
        {
            title = line.split(",");
            cols = title.length;
        }
        else
        {
            System.out.printf("ERROR! Blank title line recognized in csv file '%s'.\n\n", filename);
            System.exit(1);
        }

        // read the lines of the csv file
        rows = 0;
        while (!line.equals("EOF"))
        {
            // read a line
            try
            {
                line = in.readLine();
                rows++;
            }
            catch (IOException e)
            {
                System.out.printf("ERROR! Unable to read line %d of csv file '%s'.\n\n", rows+1, filename);
                System.exit(1);
            }

            // verify line and set row
            if (line.contains(","))
            {
                csv.add(line);
            }
            else
            {
                //System.out.printf("ERROR! Blank line recognized in csv file '%s' at line %d, skipping line.\n\n", filename, rows);
                //System.exit(1);
            }
        }

        // close the csv file
        try
        {
            in.close();
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to close csv file '%s'.\n\n", filename);
            System.exit(1);
        }
    }

    /**
     * This method returns the string value of the data in the specified
     * row,col cell of the csv sheet and prints progress.
     *
     * @param filename The name of the csv file to read in.
     *
     */
    public void readPrintCSV(String filename)
    {
        // set the csv filename
        this.filename = filename;

        // open the csv file
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(filename));
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to open csv file '%s' for reading.\n\n", filename);
            System.exit(1);
        }

        // read the csv title line
        String line = null;
        try
        {
            line = in.readLine();
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to read title line of csv file '%s'.\n\n", filename);
            System.exit(1);
        }

        System.out.printf("reading from csv file '%s'...\n", filename);

        // verify title line and set title and cols
        if (!line.equals(""))
        {
            title = line.split(",");
            cols = title.length;
        }
        else
        {
            System.out.printf("ERROR! Blank title line recognized in csv file '%s'.\n\n", filename);
            System.exit(1);
        }

        // read the lines of the csv file
        rows = 0;
        while (!line.equals("EOF"))
        {
            // read a line
            try
            {
                line = in.readLine();
                rows++;
            }
            catch (IOException e)
            {
                System.out.printf("ERROR! Unable to read line %d of csv file '%s'.\n\n", rows+1, filename);
                System.exit(1);
            }

            // verify line and set row
            if (line.contains(","))
            {
                System.out.printf("line:\t%s\n", line);
                csv.add(line);
            }
            else
            {
                System.out.printf("ERROR! Blank line recognized in csv file '%s' at line %d.\n\n", filename, rows);
                System.exit(1);
            }
        }

        System.out.printf("csv file read success.\n\n");

        System.out.printf("size:\t%d x %d [rows x cols]\n", rows, cols);
        System.out.printf("fields:\t%s", title[0]);
        for (int i=1; i<title.length; i++)
            System.out.printf(", %s", title[i]);
        System.out.printf("\n");
        System.out.printf("\n");

        // close the csv file
        try
        {
            in.close();
        }
        catch (IOException e)
        {
            System.out.printf("ERROR! Unable to close csv file '%s'.\n\n", filename);
            System.exit(1);
        }
    }

    /**
     * This method returns the string value of the data in the specified
     * row,col cell of the csv sheet.
     *
     * @param row The row of csv sheet containing the desired cell.
     * @param col The column of csv sheet containing the desired cell.
     * @return  this.csv[row][col];
     */
    public String getValue(int row, int col)
    {
        if ((row >= 0) && (col >= 0))
            return csv.get(row).split(",")[col];
        return "";
    }

    /**
     * This method returns the string value of the data in the specified
     * column (col2) where the column (col1) of the row is the specified value.
     *
     * @param col1  The column containing the value to find.
     * @param col2  The column containing the data to return.
     * @param value The value to find to indicate the row.
     * @return  this.csv[row][col2], where this.csv[row][col1] = value;
     */
    public String getValue(int col1, int col2, String value)
    {
        if ((col1 >= 0) && (col2 >= 0))
        {
            String[] row = getRow(col1, value);
            if (row != null)
                return row[col2];
        }
        return "";
    }

    /**
     * This method returns the string array of the data in the specified
     * row of the csv sheet.
     *
     * @param row The row of csv sheet to be returned.
     * @return  this.csv[row];
     */
    public String[] getRow(int row)
    {
        if ((row >= 0) && (row < getRows()))
            return csv.get(row).split(",");
        return null;
    }

    /**
     * This method returns the string array of the data in the row with the
     * specified value in the specified column.
     *
     * @param col   The column containing the value.
     * @param value The value indicating the row.
     * @return  this.csv[row], where this.csv[row][col] = value;
     */
    public String[] getRow(int col, String value)
    {
        if (col >= 0)
        {
            String[] tokens;
            for (String row : csv)
            {
                tokens = row.split(",");
                if (tokens[col].equals(value))
                    return tokens;
                if (tokens[0].equals("EOF"))
                    break;
            }
        }
        return null;
    }

    /**
     * This method returns the current number of rows in the csv sheet.
     *
     * @return  this.csv.length;
     */
    public int getRows()
    {
        return csv.size();
    }

    /**
     * This method returns the number of columns in the csv sheet.
     *
     * @return  this.title.length;
     */
    public int getCols()
    {
        return title.length;
    }
}
