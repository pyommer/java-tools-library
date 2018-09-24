package data.comparators;

import java.util.Comparator;

/**
 * The TComp class is used as a generic comparator for the basic data
 * objects that model the primitive types.
 *
 * @param <T>   The type of data object to compare.
 */
public class TComp<T> implements Comparator<T>
{
    /**
     * This method implements the compare() method of the Comparator
     * interface by calling the compareTo() method of the corresponding
     * comparator class for the basic data object type, T.
     *
     * @param a The left-hand side comprable object.
     * @param b The right-hand side comprable object.
     * @return  The compare value (-1, 1, 0).
     */
    public int compare(T a, T b)
    {
        // verify a and b type names match
        if (compareType(a, b) != 1)
            return 0;

        // return the corresponding compare value
        if (a.getClass().getTypeName().contains("Boolean"))
        {
            return Boolean.compare((Boolean) a, (Boolean) b);
        }
        else if (a.getClass().getTypeName().contains("Byte"))
        {
            return Byte.compare((Byte) a, (Byte) b);
        }
        else if (a.getClass().getTypeName().contains("Integer"))
        {
            return Integer.compare((Integer) a, (Integer) b);
        }
        else if (a.getClass().getTypeName().contains("Long"))
        {
            return Long.compare((Long) a, (Long) b);
        }
        else if (a.getClass().getTypeName().contains("Short"))
        {
            return Short.compare((Short) a, (Short) b);
        }
        else if (a.getClass().getTypeName().contains("Float"))
        {
            return Float.compare((Float) a, (Float) b);
        }
        else if (a.getClass().getTypeName().contains("Double"))
        {
            return Double.compare((Double) a, (Double) b);
        }
        else if (a.getClass().getTypeName().contains("Character"))
        {
            return Character.compare((Character) a, (Character) b);
        }
        else if (a.getClass().getTypeName().contains("String"))
        {
            return ((String) a).compareTo((String) b);
        }
        else
        {
            System.out.print("ERROR! Unrecognized type name value: ");
            System.out.print(a.getClass().getTypeName() + ".\n");
            System.out.println("Returning zero (0) instead.");
        }
        return 0;
    }

    /**
     * This method returns the comparison value of comparing the type
     * names of the generic types, T.
     *
     * @param a The left-hand side comprable object.
     * @param b The right-hand side comprable object.
     * @return  The type name comparison (1 if type(a) = type(b), 0 otherwise).
     */
    public int compareType(T a, T b)
    {
        // verify a and b type names match
        if ((a != null) && (b != null) && !a.getClass().getTypeName().equals(b.getClass().getTypeName()))
        {
            System.out.print("ERROR! Comparison between non-matching types: ");
            System.out.print("(a) " + a.getClass().getTypeName() + " != ");
            System.out.print("(b) " + b.getClass().getTypeName() + ".\n");
            System.out.println("Returning zero (0) instead.");
            return 0;
        }
        return 1;
    }
}
