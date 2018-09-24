package data.structures.queues;

/**
 * A generic queue interface containing the methods used for storing and
 * manipulating objects in a queue data structure. An object that implements
 * this Queue interface allows the objects in a generic array to be accessed
 * in FIFO order.
 *
 * @param <T>   The type of objects to be stored in the queue.
 */
public interface Queue<T>
{
	/**
	 * This method initializes an empty queue with a max size of n.
	 *
	 * @param n The maximum capacity of the queue.
	 */
	void init(int n);

	/**
	 * This method enqueues the specified element into the queue.
	 *
	 * @param x The element to be enqueued.
	 */
	void enqueue(T x);

	/**
	 * This method dequeues the next FIFO element from the queue.
	 *
	 * @return  The front element removed from the queue.
	 */
	T dequeue();

	/**
	 * This method returns the element at the front of the queue.
	 *
	 * @return  Prt_Btwn(front, front+1, this);
	 */
	T front();

	/**
	 * This method returns the element at the back of the queue.
	 *
	 * @return  Prt_Btwn(back, back+1, this);
	 */
	T back();

	/**
	 * This method returns the current length of the queue.
	 *
	 * @return  |this| - count(empty, this);
	 */
	int length();

	/**
	 * This method returns the maximum capacity of the queue.
	 *
	 * @return  |this|;
	 */
	int maxLength();

	/**
	 * This method returns the empty status of the queue.
	 *
	 * @return  true if |this| = 0, otherwise false;
	 */
	boolean isEmpty();

	/**
	 * This method clears the queue.
	 */
	void clear();

	/**
	 * This method prints the contents of the queue.
	 */
	void print();
}
