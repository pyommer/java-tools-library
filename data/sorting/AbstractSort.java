package data.sorting;

import java.util.Comparator;

/**
 * A generic sorting abstract class that implements common methods used in
 * sorting algorithms and holds the package-private generic array of elements
 * and the specified package-private comparator for comparing the elements.
 *
 * @param <T>   The type of objects to be sorted.
 */
public abstract class AbstractSort<T> implements Sort<T>
{
    Comparator<T> comp;
    public T[] array;

    /**
     * This method initializes the array associated with this object.
     *
     * @param array The initial array to associate with this object.
     * @param comp  The comparator to use for sorting the array.
     */
    public void init(T[] array, Comparator<T> comp)
    {
        this.array = array;
        //this.array = (T[]) new Object[array.length];
        //System.arraycopy(array, 0, this.array, 0, array.length);
        this.comp = comp;
    }

    /**
     * This method swaps the values at positions left and right in the array.
     *
     * @param left  The left-side position to swap.
     * @param right The right-side position to swap.
     */
    public void swap(int left, int right)
    {
        T value = array[right];
        array[right] = array[left];
        array[left] = value;
    }

    /**
     * This method checks if the array is sorted in ascending order.
     *
     * @return  true if a is sorted, otherwise false;
     */
    public boolean isSorted()
    {
        for (int i=1; i<array.length; i++)
            if (comp.compare(array[i], array[i-1]) < 0)
                return false;
        return true;
    }

    /**
     * This method prints the array.
     */
    @Override
    public void print()
    {
        for (T a : array)
            System.out.print(a.toString() + " ");
        System.out.println();
    }
}
