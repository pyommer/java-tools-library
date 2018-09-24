package data.sorting;

import java.util.Comparator;

/**
 * A sorting class that implements the mergesort algorithm.
 *
 *  Worst-case performance: O(n*log(n))
 *  Best-case performance:  O(n*log(n))     [typical]
 *                          O(n)            [natural variant]
 *
 *  Average performance:    O(n*log(n))
 *
 *  Worst-case complexity:  O(n)            [total]
 *                          O(n)            [auxiliary]
 *                          O(1)            [auxiliary, linked lists]
 */
public class MergeSort<T> extends AbstractSort<T> implements Sort<T>
{
    /**
     * The constructor for a mergesort object.
     *
     * @param array The initial array to associate with this object.
     * @param comp  The comparator to use for sorting the array.
     */
    public MergeSort(T[] array, Comparator<T> comp)
    {
        init(array, comp);
    }

    /**
     * This method performs a mergesort on the array.
     */
    public void sort()
    {
        if (array.length > 1)
        {
            // build the left-side subarray
            T[] a = (T[]) new Object[array.length / 2];
            System.arraycopy(array, 0, a, 0, a.length);

            // build the right-side subarray
            T[] b = (T[]) new Object[array.length - a.length];
            if (b.length >= 0) System.arraycopy(array, a.length, b, 0, b.length);

            // sort the sub arrays
            MergeSort<T> aSub = new MergeSort<>(a, comp);
            aSub.sort();
            MergeSort<T> bSub = new MergeSort<>(b, comp);
            bSub.sort();

            // merge the subarrays in this array
            merge(aSub.array, bSub.array);
        }
    }

    /**
     * This method merges the two arrays.
     *
     * @param a The left-side to be merged.
     * @param b The right-side to be merged.
     */
    private void merge(T[] a, T[] b)
    {
        T[] c = (T[]) new Object[a.length + b.length];
        int i = 0;
        int j = 0;

        for (int k=0; k<c.length; k++)
        {
            if (i >= a.length)
                c[k] = b[j++];
            else if (j >= b.length)
                c[k] = a[i++];
            else if (comp.compare(a[i], b[j]) < 1)
                c[k] = a[i++];
            else
                c[k] = b[j++];
        }
        System.arraycopy(c, 0, array, 0, c.length);
    }
}
