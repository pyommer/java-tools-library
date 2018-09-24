package data.sorting;

import java.util.Comparator;

/**
 * A sorting class that implements an in-place quicksort algorithm.
 *
 *  Worst-case performance: O(n^2)
 *  Best-case performance:  O(n*log(n))     [simple]
 *                          O(n)            [3-way and equal keys]
 *
 *  Average performance:    O(n*log(n))
 *
 *  Worst-case complexity:  O(n)            [auxiliary, naive]
 *                          O(log(n))       [auxiliary, Sedgewick 1978]
 */
public class QuickSort<T> extends AbstractSort<T> implements Sort<T>
{
    /**
     * The constructor for a quicksort object.
     *
     * @param array The initial array to associate with this object.
     * @param comp  The comparator to use for sorting the array.
     */
    public QuickSort(T[] array, Comparator<T> comp)
    {
        init(array, comp);
    }

    /**
     * This method performs a quicksort on the array.
     */
    public void sort()
    {
        if (array.length > 1)
            step(0, array.length - 1);
    }

    /**
     * This method recursively performs the steps of quicksort.
     */
    private void step(int left, int right)
    {
        if (left < right)
        {
            T pivot = array[right];
            int l = left;
            int r = right - 1;

            while (l <= r)
            {
                // scan right to find element larger than the pivot
                while ((l <= r) && (comp.compare(array[l], pivot) < 1))
                    l++;

                // scan left to find element smaller than the pivot
                while ((l <= r) && (comp.compare(array[r], pivot) > -1))
                    r--;

                // swap elements if both found
                if (l < r)
                    swap(l, r);
            }

            // swap pivot with left element
            swap(l, right);

            // recursive step each side of the pivot
            step(left, l - 1);      // left to pivot step
            step(l + 1, right);      // pivot to right step
        }
    }
}
