package data.structures.stacks;

/**
 * A generic stack interface containing the methods used for storing and
 * manipulating objects in a stack data structure. An object that implements
 * this Stack interface allows the objects in a generic array to be accessed
 * in LIFO order.
 *
 * @param <T>   The type of objects to be stored in the stack.
 */
public interface Stack<T>
{
	/**
	 * This method initializes an empty stack with a max size of n.
	 *
	 * @param n The maximum capacity of the stack.
	 */
	void init(int n);

	/**
	 * This method pushes the specified element onto the stack.
	 *
	 * @param x The element to be pushed.
	 */
	void push(T x);

	/**
	 * This method pops the top LIFO element off the stack.
	 *
	 * @return  The top element removed from the stack.
	 */
	T pop();

	/**
	 * This method returns the element on the top of the stack.
	 *
	 * @return  The top element removed from the stack.
	 */
	T peek();

	/**
	 * This method returns the current depth of the stack.
	 *
	 * @return  |this| - count(empty, this);
	 */
	int depth();

	/**
	 * This method returns the maximum capacity of the stack.
	 *
	 * @return  |this|;
	 */
	int maxDepth();

	/**
	 * This method returns the empty status of the stack.
	 *
	 * @return  true if |this| = 0, otherwise false;
	 */
	boolean isEmpty();

	/**
	 * This method clears the stack.
	 */
	void clear();

	/**
	 * This method prints the contents of the stack.
	 */
	void print();
}
