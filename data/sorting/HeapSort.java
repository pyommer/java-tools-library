package data.sorting;

import java.util.Comparator;

/**
 * A sorting class that implements the heapsort algorithm.
 *
 *  Worst-case performance: O(n*log(n))
 *  Best-case performance:  O(n*log(n))     [distinct keys]
 *                          O(n)            [equal keys]
 *
 *  Average performance:    O(n*log(n))
 *
 *  Worst-case complexity:  O(1)            [auxiliary]
 *
 */
public class HeapSort<T> extends AbstractSort<T> implements Sort<T>
{
    /**
     * The constructor for a heapsort object.
     *
     * @param array The initial array to associate with this object.
     * @param comp  The comparator to use for sorting the array.
     */
    public HeapSort(T[] array, Comparator<T> comp)
    {
        init(array, comp);
    }

    /**
     * This method performs a heapsort on the array.
     */
    public void sort()
    {
        if (array.length > 1)
        {
            // build the max heap from the array [O(n)]
            for (int i=array.length/2 - 1; i>=0; i--)
                heapify(array.length, i);

            // extract elements (one-by-one) from the heap
            for (int i=array.length - 1; i>=0; i--)
            {
                // swap current root with end
                swap(i, 0);

                // heapify the reduced heap
                heapify(i, 0);
            }
        }
        reverse();
    }

    /**
     * This method builds the initial heap.
     */
    private void heapify(int size, int pos)
    {
        int max = pos;
        int l = 2*pos + 1;
        int r = 2*pos + 2;

        // update max position
        if ((l < size) && (comp.compare(array[l], array[max]) > 0))
            max = l;
        if ((r < size) && (comp.compare(array[r], array[max]) > 0))
            max = r;

        // recursive swap and heapify until max is root
        if (max != pos)
        {
            swap(max, pos);
            heapify(size, max);
        }
    }

    /**
     * This method reverses the heap-sorted array.
     */
    private void reverse()
    {
        T[] rev = (T[]) new Object[array.length];
        for (int i=0; i<array.length; i++)
            rev[i] = array[array.length - i - 1];
        array = rev;
    }
}
