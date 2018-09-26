package utilities;

import java.util.*;

/**
 * The MathUtilities class contains useful functions in math applications.
 */
public class MathUtilities
{
    /**
     * The default constructor.
     */
    public MathUtilities()
    {
    }

    /* -- misc math functions -- */

    /**
     * This method returns the maximum value in the specified array.
     *
     * @param a The array to find the maximum value in.
     * @return The maximum value in the array.
     */
    public static int max(int[] a)
    {
        int max = a[0];
        for (int i : a)
            if (i > max)
                max = i;
        return max;
    }

    /**
     * This method returns the minimum value in the specified array.
     *
     * @param a The array to find the minimum value in.
     * @return The minimum value in the array.
     */
    public static int min(int[] a)
    {
        int min = a[0];
        for (int i : a)
            if (i < min)
                min = i;
        return min;
    }

    /**
     * This method returns the summation of i over [1, n].
     * <p>
     * Time complexity:     O(1) Space complexity:    O(1)
     *
     * @param n The max value of i.
     * @return The summation: 1 + 2 + ... + n.
     */
    public static int simpleSummation(int n)
    {
        return n*(n+1)/2;
    }

    /**
     * This method returns the summation of i over [n1, n2].
     * <p>
     * Time complexity:     O(1) Space complexity:    O(1)
     *
     * @param n1 The min value of i.
     * @param n2 The max value of i.
     * @return The summation: n1 + (n1+1) + ... + n2.
     */
    public static int simpleSummationBetween(int n1, int n2)
    {
        return simpleSummation(n2) - simpleSummation(n1);
    }

    /**
     * This method returns the sum of a simple geometric progression, where
     * every two consecutive elements have the same ratio.
     * <p>
     * Time complexity:     O(1) Space complexity:    O(1)
     *
     * @param c The constant value.
     * @param r The ratio value.
     * @param n The number of values.
     * @return The summation: c*r^1 + c*r^2 + ... + c*r^(n-1).
     */
    public static int geometricSummation(int c, int r, int n)
    {
        if ((r == 1) || (n < 1))
            return 0;
        return c*(((int) Math.pow((double) r, (double) n))-1)/(r-1);
    }

    /**
     * This method returns the summation of (n choose k) over k in [0, n],
     * according to the generalized binomial theorem.
     * <p>
     * Time complexity:     O(1) Space complexity:    O(1)
     *
     * @param n The number of choices.
     * @return The summation:    (n choose 0) + (n choose 1) + ... + (n choose
     * n)
     */
    public static int chooseSummation(int n)
    {
        return binomialSummation(1, 1, n);
    }

    /**
     * This method returns the summation of (n choose k)*x^(n-k)*y^k over k in
     * [0, n], according to the binomial theorem.
     * <p>
     * Time complexity:     O(1) Space complexity:    O(1)
     *
     * @param x The x value.
     * @param y The y value.
     * @param n The number of choices.
     * @return The summation:    (n choose 0)*x^(n-0)*y^0 + ... + (n choose
     * 1)*x^(n-1)*y^1 + ... +   ... + (n choose n)*x^(n-n)*y^n
     */
    public static int binomialSummation(int x, int y, int n)
    {
        if (n > 0)
            return (int) Math.pow((double) (x+y), (double) n);
        return 1;
    }

    /**
     * This method returns the choose value for the specified integers, using
     * the recursive formula for binomial coefficients.
     * <p>
     * Time complexity:     O(2^n) Space complexity:    O(1)
     *
     * @param n The number being chosen from.
     * @param k The number choosing from n.
     * @return The value (n choose k).
     */
    public static int binomialCoeffIterative(int n, int k)
    {
        if ((0 < k) && (k < n))
            return chooseIterative(n-1, k-1) + chooseIterative(n-1, k);
        return 1;
    }

    /**
     * This method returns the choose value for the specified integers, using
     * the binomial coefficients from Pascal's triangle.
     * <p>
     * Time complexity:     O(n^2) Space complexity:    O(n^2)
     *
     * @param n The number being chosen from.
     * @param k The number choosing from n.
     * @return The value (n choose k).
     */
    public static int binomialCoeffPascal(int n, int k)
    {
        int[] row = rowPascalTriangleDynamic(n);
        if (row != null)
            return row[k-1];
        return 1;
    }

    /**
     * This method returns the factorial value of the specified integer, using
     * an iterative programming approach.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param n The number to factRecursive.
     * @return The value (n!).
     */
    public static int factIterative(int n)
    {
        if (n < 2)
            return 1;
        int fact = n;
        for (int i=n-1; i>1; i--)
            fact *= i;
        return fact;
    }

    /**
     * This method returns the choose value for the specified integers, using a
     * dynamic iterative programming approach.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param n The number being chosen from.
     * @param k The number choosing from n.
     * @return The value (n choose k).
     */
    public static int chooseDynamic(int n, int k)
    {
        if (n < 2)
            return 1;
        int fact = n;
        for (int i=n-1; i>k; i--)
            fact *= i;
        return fact/factIterative(n-k);
    }

    /**
     * This method returns the choose value for the specified integers, using an
     * iterative programming approach.
     * <p>
     * Time complexity:     O(n^2) Space complexity:    O(1)
     *
     * @param n The number being chosen from.
     * @param k The number choosing from n.
     * @return The value (n choose k).
     */
    public static int chooseIterative(int n, int k)
    {
        return factIterative(n)/(factIterative(k)*factIterative(n-k));
    }

    /**
     * This method returns the greatest common divisor of the specified
     * integers, according to Euclid's recursive algorithm for GCD, using a
     * dynamic iterative programming approach.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param x The first integer.
     * @param y The second integer.
     * @return The greatest common divisor of x and y.
     */
    public static int gcdDynamic(int x, int y)
    {
        if (Math.floorMod(x, y) == 0)
            return y;

        int a = y;
        int b = Math.floorMod(x, y);

        while (b != 0)
        {
            int z = b;
            b = Math.floorMod(a, b);
            a = z;
        }
        return a;
    }

    /**
     * This method returns the n-th Fibonacci number, using an iterative dynamic
     * programming approach. The Fibonacci sequence used starts with 0.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param n The ordinal of the desired number in the Fibonacci sequence.
     * @return The n-th Fibonacci number.
     */
    public static long fibDynamic(int n)
    {
        if (n < 1)
            return 0;
        if (n < 2)
            return 1;
        long[] fib = new long[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i=2; i<=n; i++)
            fib[i] = fib[i-1] + fib[i-2];
        return fib[n];
    }

    /**
     * This method returns the n-th Fibonacci number, using a memoized dynamic
     * programming approach. The Fibonacci sequence used starts with 0.
     * <p>
     * Time complexity:     O(2^n) Space complexity:    O(n)
     *
     * @param n   The ordinal of the desired number in the Fibonacci sequence.
     * @param mem The memoized array of seen fibonacci numbers.
     * @return The n-th Fibonacci number.
     */
    public static long fibMemoize(int n, int[] mem)
    {
        if (mem[n] >= 0)
            return mem[n];
        if (n < 1)
            return 0;
        if (n < 2)
            return 1;
        return fibMemoize(n-1, mem) + fibMemoize(n-2, mem);
    }

    /**
     * This method returns the n-th row of Pascal's triangle, using an iterative
     * dynamic programming approach.
     * <p>
     * Time complexity:     O(n^2) Space complexity:    O(n^2)
     *
     * @param n The desired row of Pascal's triangle.
     * @return The n-th row of Pascal's triangle, or null if n is out of bounds.
     */
    public static int[] rowPascalTriangleDynamic(int n)
    {
        if (n < 1)
            return null;

        int[][] triangle = new int[n][n];
        triangle[0][0] = 1;

        if (n > 1)
        {
            triangle[1][0] = 1;
            triangle[1][1] = 1;

            for (int i=3; i<=n; i++)
            {
                triangle[i-1][0] = 1;
                triangle[i-1][i-1] = 1;

                for (int j=1; j<i-1; j++)
                {
                    triangle[i-1][j] = triangle[i-2][j-1] + triangle[i-2][j];
                }
            }
        }
        return triangle[n-1];
    }

    /**
     * This method prints a row of Pascal's triangle in formatted output.
     *
     * @param row The row to print.
     * @param n   The number of rows in the triangle.
     */
    public static void printPascalRow(int[] row, int n)
    {
        if (row != null)
        {
            for (int j=n; j>row.length; j--)
                System.out.print("  ");
            for (int r : row)
                System.out.printf("%-3d ", r);
            System.out.println();
        }
    }

    /* -- prime number functions -- */

    /**
     * This method returns true if the specified integers are relatively prime.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param x The first integer.
     * @param y The second integer.
     * @return true if x and y are relatively prime, otherwise false.
     */
    public static boolean areRelativePrime(int x, int y)
    {
        return (gcdDynamic(x, y) == 1);
    }

    /**
     * This method returns true if the specified integer is prime.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param x The integer to test.
     * @return true if x is prime, otherwise false.
     */
    public static boolean isPrime(int x)
    {
        for (int i=2; i<=x/2; i++)
            if (Math.floorMod(x, i) == 0)
                return false;
        return true;
    }

    /* -- misc example function uses -- */

    /**
     * This method returns the magic square constant for a magic square of the
     * specified order. The magic square constant is defined as the summation of
     * integers in [1, n^2] divided by n, where n is the order of the magic
     * square.
     *
     * @param n The order of the magic square.
     * @return The magic square constant for a magic square of order n.
     */
    public static int magicSquareConstant(int n)
    {
        return simpleSummation(n * n) / n;
    }

    /**
     * This method returns the number of valid strings using 2*n parentheses.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param n The half-number of parentheses in a string.
     * @return The number of valid strings using 2*n parentheses.
     */
    public static int countValidParentheses(int n)
    {
        if (n < 1)
            return 1;
        return chooseIterative(2*n, n)/(n+1);
    }

    /* -- common problems in reducing time complexity -- */

    /**
     * This method returns the minimum and maximum sum of n-1 of the integers in
     * the specified array of n integers.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param array The array of integer values to sum.
     * @return The min and max sums in the format {min, max}.
     */
    public static long[] sumMinMax(int[] array)
    {
        long sum = 0;
        int  x   = array[0];
        int  y   = array[0];
        for (int i : array)
        {
            sum += i;
            if (i > x)
                x = i;
            if (i < y)
                y = i;
        }
        return new long[] {sum-x, sum-y};
    }

    /**
     * This method returns the maximum sum of all subsets of non-adjacent
     * elements in the array. If there are no non-adjacent subsets, zero is
     * returned.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(1)
     *
     * @param array The array of integers.
     * @return The maximum sum of the non-adjacent subsets in the array.
     */
    public static int maxNonAdjacentSubsetSum(int[] array)
    {
        if (array.length < 3)
            return 0;

        int x = array[0];
        int y = 0;
        int z;
        for (int i=1; i<array.length; i++)
        {
            z = (x > y) ? x : y;
            x = y + array[i];
            y = z;
        }
        return ((x > y) ? x : y);
    }

    /**
     * This method returns the 1-based indices of the first two values in the
     * array that sum to the specified value. If no two values sum to the sum,
     * zero is returned for both indices.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param array The array of values.
     * @param sum   The desired sum.
     * @return A 2-d array of the 1-based indices of the two values.
     */
    public static int[] firstTwoIndicesWithSum(int[] array, int sum)
    {
        // create hashtable for storing the compliment of seen elements
        Hashtable<Integer, Integer> table = new Hashtable<>(array.length);

        // find first two values that sum to the sum, save complement as key
        for (int i=0; i<array.length; i++)
        {
            // check if hashtable contains i-th value as sum complement
            if (table.containsKey(array[i]))
            {
                return new int[] {table.get(array[i]) + 1, i+1};
            }

            // add complement of i-th value to hashtable
            table.put(sum-array[i], i);
        }
        return new int[] {0, 0};
    }

    /**
     * This method returns the 1-based indices of the first two values in the
     * array that sum to the specified value. If no two values sum to the sum,
     * zero is returned for both indices.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param array The array of values.
     * @param sum   The desired sum.
     * @return A 2-d array of the 1-based indices of the two values.
     */
    public static int[] firstTwoValuesWithSum(int[] array, int sum)
    {
        // create hash set for storing the compliment of seen elements
        HashSet<Integer> set = new HashSet<>(array.length);

        // find first two values that sum to the sum, save complement as key
        for (int i=0; i<array.length; i++)
        {
            // check if hashtable contains i-th value as sum complement
            if (set.contains(array[i]))
            {
                return new int[] {sum-array[i], array[i]};
            }

            // add complement of i-th value to hashtable
            set.add(sum-array[i]);
        }
        return new int[] {0, 0};
    }

    /**
     * This method returns the minimum sum of the rank of each element in the
     * specified array, where the rank of each element is determined only by its
     * adjacent elements.
     * <p>
     * Time complexity:     O(n^2)  ?? Space complexity:    O(n)    ??
     *
     * @param array The array of elements to find minimum rank sum.
     * @return The minimum rank sum of the elements.
     */
    public static long minLocalRankSum(int[] array)
    {
        int   n     = array.length;
        int[] left  = new int[n];
        int[] right = new int[n];

        // set base cases
        left[0] = 1;
        right[n - 1] = 1;

        // set middle cases
        for (int i=1; i<n-1; i++)
        {
            left[i] = (array[i] > array[i-1]) ? left[i - 1] + 1 : 1;
            right[n-i-1] = (array[n-i-1] > array[n-i]) ? right[n-i] + 1 : 1;
        }

        // set end cases
        left[n-1] = (array[n-1] > array[n-2]) ? left[n-2] + 1 : 1;
        right[0] = (array[0] > array[1]) ? right[1] + 1 : 1;

        // compute minimum local rank sum
        long sum = 0;
        for (int i=0; i<n; i++)
            sum += Math.max(left[i], right[i]);

        return sum;
    }

    /* -- finding shortest path -- */

    /**
     * The internal XNode class is used for finding the shortest length path.
     */
    public static class XNode
    {
        List<Integer> adj;  // adjacent node ids
        int           id;             // node id
        long          value;         // node value (color)
        int           src;            // source node (from)
        int           dist;           // distance from source node

        // initializes a new node with the specified value
        public XNode(long value)
        {
            adj = new ArrayList<>();
            this.value = value;
            src = -1;
            dist = -1;
        }

        public XNode(int id, long value)
        {
            adj = new ArrayList<>();
            this.id = id;
            this.value = value;
            src = -1;
            dist = -1;
        }

        // initializes the node's source and distance
        public void init(int src)
        {
            this.src = src;
            dist = 0;
        }

        // updates the node's source and increments the distance
        public void add(XNode node)
        {
            src = node.src;
            dist = node.dist + 1;
        }
    }

    /**
     * This method returns the length of the shortest path between two nodes of
     * the same value, or -1 if no such nodes exist.
     * <p>
     * Time complexity:     O(n^2)  ?? Space complexity:    O(n^2)  ??
     *
     * @param from An array of integers, the start nodes for each edge.
     * @param to   An array of integers, the end nodes for each edge.
     * @param ids  An array of integers, the value id per node.
     * @return The minimum distance between any two nodes of the same value.
     */
    public static int findShortestAny(int[] from, int[] to, long[] ids)
    {
        HashSet<Long> set = new HashSet<>(ids.length);
        for (long id : ids)
            set.add(id);

        Long[] id = new Long[set.size()];
        set.toArray(id);
        int min = -1;
        int len;
        for (Long i : id)
        {
            len = findShortest(i, from, to, ids);

            if ((len > 0) && ((len < min) || (min == -1)))
                min = len;
        }
        return min;
    }

    /**
     * This method returns the value of the shortest path between two nodes of
     * the same value, or -1 if no such nodes exist.
     * <p>
     * Time complexity:     O(n^2)  ?? Space complexity:    O(n^2)  ??
     *
     * @param from An array of integers, the start nodes for each edge.
     * @param to   An array of integers, the end nodes for each edge.
     * @param ids  An array of integers, the value id per node.
     * @return The minimum distance between any two nodes of the same value.
     */
    public static long findShortestValue(int[] from, int[] to, long[] ids)
    {
        HashSet<Long> set = new HashSet<>(ids.length);
        for (long id : ids)
            set.add(id);

        Long[] id = new Long[set.size()];
        set.toArray(id);
        long min = -1;
        int  len;
        for (Long i : id)
        {
            len = findShortest(i, from, to, ids);

            if ((len > 0) && ((len < min) || (min == -1)))
                min = i;
        }
        return min;
    }

    /**
     * This method returns the length of the shortest path between two nodes of
     * the same specified value, or -1 if no such nodes exist.
     * <p>
     * Time complexity:     O(n^2)  ?? Space complexity:    O(n^2)  ??
     *
     * @param x    An integer, the id of the color to match.
     * @param from An array of integers, the start nodes for each edge.
     * @param to   An array of integers, the end nodes for each edge.
     * @param ids  An array of integers, the value id per node.
     * @return The length of the shortest path between two nodes of the same
     * value.
     */
    public static int findShortest(long x, int[] from, int[] to, long[] ids)
    {
        // build nodes list
        int     n     = ids.length;
        int     flag  = 0;
        XNode[] nodes = new XNode[n];
        for (int i=0; i<n; i++)
        {
            nodes[i] = new XNode(ids[i]);
            if (ids[i] == x)
                flag++;
        }

        // verify two or more values in ids
        if (flag > 1)
        {
            // add adjacent nodes for edges length 1
            for (int i=0; i<from.length; i++)
            {
                nodes[from[i]-1].adj.add(to[i]-1);
                nodes[to[i]-1].adj.add(from[i]-1);
            }

            // create queue of nodes with value
            Queue<Integer> list = new LinkedList<>();
            for (int i=0; i<n; i++)
            {
                if (nodes[i].value == x)
                {
                    nodes[i].init(i);
                    list.add(i);
                }
            }

            // loop list to find distances from each node
            while (!list.isEmpty())
            {
                int head = list.remove();

                // loop adjacent nodes of current head
                for (int adj : nodes[head].adj)
                {
                    // determine non-adjacent lengths
                    if (nodes[adj].src != nodes[head].src)
                    {
                        if (nodes[adj].src > 0)
                        {
                            return nodes[adj].dist + nodes[head].dist + 1;
                        }
                        else
                        {
                            nodes[adj].add(nodes[head]);
                            list.add(adj);
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * This method returns an XNode array representing the tree from the
     * specified edges and node values.
     *
     * @param from The array of edge starting nodes.
     * @param to   The array of edge ending nodes.
     * @param ids  The array of node values.
     * @return The queue-list representing the tree.
     */
    public static XNode[] buildXNodeTree(int[] from, int[] to, long[] ids)
    {
        // build nodes list
        int     n     = ids.length;
        XNode[] nodes = new XNode[n];
        for (int i=0; i<n; i++)
            nodes[i] = new XNode(i+1, ids[i]);

        // add adjacent nodes for edges length 1
        for (int i=0; i<from.length; i++)
        {
            nodes[from[i]-1].adj.add(to[i]-1);
            nodes[to[i]-1].adj.add(from[i]-1);
        }

        // create queue of nodes
        for (int i=0; i<n; i++)
        {
            nodes[i].init(i);

            // loop adjacent nodes of current node
            for (int adj : nodes[i].adj)
            {
                // determine non-adjacent lengths
                if (nodes[adj].src != nodes[i].src)
                {
                    nodes[adj].add(nodes[i]);
                }
            }
        }
        return nodes;
    }

    /**
     * This method prints the specified list of XNodes as a tree.
     *
     * @param tree The XNode tree to be printed.
     */
    public static void printXNodeTree(XNode[] tree)
    {
        if (tree != null)
        {
            Queue<Integer> queue = new LinkedList<>();

            for (XNode t : tree)
            {
                queue.add(t.id);

                System.out.print("(" + t.id + ", " + t.value + ")\t");

                for (Integer i : t.adj)
                    if (!queue.contains(i+1))
                        System.out.print("(" + (i+1) + ") ");
                System.out.println();
            }
            System.out.println();
        }
        else
        {
            System.out.println(" ** Empty list. **");
        }
    }

    /* -- tree traversals -- */

    /**
     * The internal YNode class is used for tree traversals.
     */
    public static class YNode
    {
        int   id;
        long  value;
        YNode left;
        YNode right;

        public YNode(int id, long value)
        {
            this.id = id;
            this.value = value;
            left = null;
            right = null;
        }

        public void printYNode()
        {
            System.out.println("(" + id + ", " + value + ")");
            if (left != null)
            {
                System.out.print("\t");
                left.printYNode();
            }
            if (right != null)
            {
                System.out.print("\t");
                right.printYNode();
            }
        }
    }

    /**
     * This method builds and traverses the tree for the specified mode of
     * traversal, the tree edges, and the node values.
     *
     * @param mode The mode of traversal ({1,2,3} : {LNR,NLR,LRN})
     * @param from An array of integers, the start nodes for each edge.
     * @param to   An array of integers, the end nodes for each edge.
     * @param ids  An array of integers, the value id per node.
     */
    public static void traversal(int mode, int[] from, int[] to, long[] ids)
    {
        // build the tree
        YNode[] nodes = new YNode[ids.length];
        for (int i=0; i<ids.length; i++)
            nodes[i] = new YNode(i+1, ids[i]);
        for (YNode node : nodes)
        {
            for (int i=0; i<from.length; i++)
            {
                if (from[i] == node.id)
                {
                    if (node.left == null)
                        node.left = nodes[to[i]-1];
                    else
                        node.right = nodes[to[i]-1];
                }
            }
        }

        // traverse the tree
        switch (mode)
        {
            case 1:
                inorderTraversal(nodes[0]);
                break;
            case 2:
                preorderTraversal(nodes[0]);
                break;
            case 3:
                postorderTraversal(nodes[0]);
                break;
        }
        System.out.println();
    }

    /**
     * This method prints the nodes of the tree with the specified root in a
     * depth-first, inorder traversal. Assumes this is a binary tree.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param root The root of the tree of YNodes to be traversed.
     */
    public static void inorderTraversal(YNode root)
    {
        if (root != null)
        {
            inorderTraversal(root.left);
            System.out.print(root.id + " ");
            inorderTraversal(root.right);
        }
    }

    /**
     * This method prints the nodes of the tree with the specified root in a
     * depth-first, preorder traversal. Assumes this is a binary * tree.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param root The root of the tree of YNodes to be traversed.
     */
    public static void preorderTraversal(YNode root)
    {
        if (root != null)
        {
            System.out.print(root.id + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    /**
     * This method prints the nodes of the tree with the specified root in a
     * depth-first, postorder traversal. Assumes this is a binary * tree.
     * <p>
     * Time complexity:     O(n) Space complexity:    O(n)
     *
     * @param root The root of the tree of YNodes to be traversed.
     */
    public static void postorderTraversal(YNode root)
    {
        if (root != null)
        {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.id + " ");
        }
    }

    /* -- sorting arrays -- */

    /**
     * This method prints the integer array.
     *
     * @param array The array to be printed.
     */
    public static void printArray(int[] array)
    {
        for (int a : array)
            System.out.print(a + " ");
        System.out.println();
    }

    /**
     * This method swaps the nodes at the specified positions in the array.
     *
     * @param x     The first position.
     * @param y     The second position.
     * @param array The original array.
     */
    public static void swap(int x, int y, int[] array)
    {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * This method swaps the values in the specified positions in the array,
     * using a bitwise XOR to reduce space complexity.
     *
     * @param x     The first position.
     * @param y     The second position.
     * @param array The original array.
     */
    public static void swapBitwise(int x, int y, int[] array)
    {
        if (array[x] == array[y])
            return;

        array[x] = array[x] ^ array[y];
        array[y] = array[x] ^ array[y];
        array[x] = array[x] ^ array[y];
    }

    /**
     * This method returns the sorted array by performing a quicksort on the
     * specified array.
     * <p>
     * Worst-case performance: O(n^2) Best-case performance:  O(n*log(n))
     * [simple] O(n)            [3-way and equal keys]
     * <p>
     * Average performance:    O(n*log(n))
     * <p>
     * Worst-case complexity:  O(n)            [auxiliary, naive] O(log(n))
     * [auxiliary, Sedgewick 1978]
     *
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    public static int[] quicksort(int[] array)
    {
        if (array.length > 1)
            quickStep(0, array.length-1, array);
        return array;
    }

    /**
     * This method recursively performs the steps of quick sort.
     *
     * @param left  The left-side bound.
     * @param right The right-side bound.
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    public static int[] quickStep(int left, int right, int[] array)
    {
        if (left < right)
        {
            int pivot = array[right];
            int l     = left;
            int r     = right - 1;

            while (l <= r)
            {
                // scan right to find element larger than the pivot
                while ((l <= r) && (array[l] <= pivot))
                    l++;

                // scan left to find element smaller than the pivot
                while ((l <= r) && (array[r] >= pivot))
                    r--;

                // swap elements if both found
                if (l < r)
                    swap(l, r, array);
            }

            // swap pivot with left element
            swap(l, right, array);

            // recursive step each side of the pivot
            quickStep(left, l-1, array);   // left to pivot step
            quickStep(l+1, right, array);   // pivot to right step
        }
        return array;
    }

    /**
     * This method returns the sorted array by performing a mergesort on the
     * specified array.
     * <p>
     * Worst-case performance: O(n*log(n)) Best-case performance:  O(n*log(n))
     * [typical] O(n)            [natural variant]
     * <p>
     * Average performance:    O(n*log(n))
     * <p>
     * Worst-case complexity:  O(n)            [total] O(n)
     * [auxiliary] O(1)            [auxiliary, linked lists]
     *
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    public static int[] mergesort(int[] array)
    {
        if (array.length > 1)
        {
            // get left and right sub-arrays
            int[] a = new int[array.length/2];
            System.arraycopy(array, 0, a, 0, a.length);
            int[] b = new int[array.length - a.length];
            System.arraycopy(array, a.length, b, 0, b.length);

            // sort the sub-arrays
            a = mergesort(a);
            b = mergesort(b);

            // merge the sorted sub arrays
            return mergeStep(a, b);
        }
        return array;
    }

    /**
     * This method merges the two specified arrays into a sorted array.
     *
     * @param a The left-side to be merged.
     * @param b The right-side to be merged.
     * @return The merged array.
     */
    public static int[] mergeStep(int[] a, int[] b)
    {
        int[] sort = new int[a.length + b.length];
        int   i    = 0;
        int   j    = 0;

        // sort and merge the arrays
        for (int k=0; k<sort.length; k++)
        {
            if (i >= a.length)
                sort[k] = b[j++];
            else if (j >= b.length)
                sort[k] = a[i++];
            else if (a[i] <= b[j])
                sort[k] = a[i++];
            else
                sort[k] = b[j++];
        }
        return sort;
    }

    /**
     * This method returns the sorted array by performing a heapsort on the
     * specified array.
     * <p>
     * Worst-case performance: O(n*log(n)) Best-case performance:  O(n*log(n))
     * [distinct keys] O(n)            [equal keys]
     * <p>
     * Average performance:    O(n*log(n))
     * <p>
     * Worst-case complexity:  O(1)            [auxiliary]
     *
     * @param array The array to be sorted.
     * @return The sorted array.
     */
    public static int[] heapsort(int[] array)
    {
        if (array.length > 1)
        {
            // build initial max heap
            for (int i=array.length/2-1; i>=0; i--)
                heapifyStep(array.length, i, array);

            // extract elements (one-by-one) from heap
            for (int i=array.length-1; i>=0; i--)
            {
                swap(i, 0, array);          // swap current and end
                heapifyStep(i, 0, array); // heapify the reduced heap
            }
        }
        return array;
    }

    /**
     * This method performs the heapify step for heapsort and returns the
     * heapified array.
     *
     * @param size  The size of the heap.
     * @param pos   The current position.
     * @param array The array to be heapified.
     * @return The heapified array.
     */
    public static int[] heapifyStep(int size, int pos, int[] array)
    {
        int max = pos;
        int l   = 2*pos + 1;
        int r   = 2*pos + 2;

        // update max position
        if ((l < size) && (array[l] > array[max]))
            max = l;
        if ((r < size) && (array[r] > array[max]))
            max = r;

        // swap and heapify until max at root
        if (max != pos)
        {
            swap(max, pos, array);
            heapifyStep(size, max, array);
        }
        return array;
    }
}

