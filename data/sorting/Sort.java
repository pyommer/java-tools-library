package data.sorting;

import java.util.Comparator;

/**
 * A generic sorting interface containing useful methods in sorting objects
 * of a desired type based on a specified comparator. An object that
 * implements this Sort interface allows the objects in a generic array to
 * be sorted by a specified comparator.
 *
 * @param <T>   The type of objects to be sorted.
 */
public interface Sort<T>
{
    /**
     * This method initializes the array associated with this object.
     *
     * @param array The initial array to associate with this object.
     * @param comp  The comparator to use for sorting the array.
     */
    void init(T[] array, Comparator<T> comp);

    /**
     * This method swaps the values at positions left and right in the array.
     *
     * @param left  The left-side position to swap.
     * @param right The right-side position to swap.
     */
    void swap(int left, int right);

    /**
     * This method performs the sorting algorithm on the array.
     */
    void sort();

    /**
     * This method checks if the array is sorted in ascending order.
     *
     * @return  true if a is sorted, otherwise false;
     */
    boolean isSorted();

    /**
     * This method prints the array.
     */
    void print();
}
