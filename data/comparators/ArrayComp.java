package data.comparators;

import java.util.Comparator;

/**
 * The ArrayComp class is used for comparing generic arrays of basic data
 * objects that model the primitive types.
 *
 * To compare collection objects that can be converted to arrays, use:
 *
 *      ArrayComp.compare((T[]) a.toArray(), (T[]) b.toArray())
 *
 * @param <T>   The type of data objects in the arrays to compare.
 */
public class ArrayComp<T> implements Comparator<T[]>
{
    /**
     * This method implements the compare() method of the Comparator
     * interface by calling the compareTo() method of the corresponding
     * comparator class for the basic data object type, T, for the
     * arrays of T-typed objects.
     *
     * @param a The left-hand side comprable array.
     * @param b The right-hand side comprable array.
     * @return  The compare value (-1, 1, or 0).
     */
    public int compare(T[] a, T[] b)
    {
        // compare a and b based on length
        if (a.length < b.length)
            return 1;
        else if (a.length > b.length)
            return -1;

        // create a new generic comparator
        TComp<T> comp = new TComp<>();

        // verify a and b type names match
        if (comp.compareType(a[0], b[0]) != 1)
            return 0;

        // compare the arrays (element-by-element)
        int j = 0;
        int k1 = 0;
        int k2 = 0;
        while (j < a.length)
        {
            k1 += comp.compare(a[j], b[j]);
            k2 += comp.compare(b[j], a[j]);
            j++;
        }
        return Integer.compare(k1, k2);
    }
}
