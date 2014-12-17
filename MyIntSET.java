/*
 * Brandon Pauly
 * CSC 301 - 502
 * Dr. Riely
 * Assignment 4
 */
package algs32;
import algs13.Queue;
import stdlib.*;

/* ***********************************************************************
 * A simple BST with int keys and no values
 * 
 * Complete each function below.
 * Write each function as a separate recursive definition (do not use more than one helper per function).
 * Depth of root==0.
 * Height of leaf==0.
 * Size of empty tree==0.
 * Height of empty tree=-1.
 *************************************************************************/
public class MyIntSET {
    private Node root;
    private class Node {
        private final int key;
        private Node left, right;
        private int height;
        public Node(int key) { this.key = key; }
    }
    
    // the number of nodes in the tree
    public int size() { return size(root); }
    
    private int size(Node x){
    	if (x == null) { return 0; }
    	return 1 + size(x.right) + size(x.left);
    }
    
    // the height of the tree
    public int height() { return height(root); }
    
    private int height(Node x){
    	if (x == null) return -1;
    	return 1 + Math.max(height(x.left), height(x.right));
    }
        
    // the number of odd nodes in the tree
    public int sizeOdd() { return sizeOdd(root); }
    
    private int sizeOdd(Node x){
    	if (x == null) return 0; 
    	return sizeOdd(x.right) + sizeOdd(x.left) + (x.key % 2 == 1 ? 1 : 0);
    }

    // the number of nodes in the tree, at exactly depth k
    public int sizeAtDepth(int k) { return sizeAtDepth(k, root, 0); }
    
    private int sizeAtDepth(int k, Node x, int depth){
    	if (x == null) return 0;
    	return sizeAtDepth(k, x.right, depth + 1) + sizeAtDepth(k, x.left, depth + 1) + (k == depth ? 1 : 0);
    }
    
    // the number of nodes in the tree, above depth k (not including k) 
    public int sizeAboveDepth(int k) { return sizeAboveDepth(k, root, 0); }
    
    private int sizeAboveDepth(int k, Node x, int depth){
    	if (x == null) return 0;
    	return sizeAboveDepth(k, x.left, depth + 1) + sizeAboveDepth(k, x.right, depth + 1) + (k > depth ? 1 : 0);
    }
    
    // the number of nodes in the tree, below depth k (not including k) 
    public int sizeBelowDepth(int k) { return sizeBelowDepth(k, root, 0); }
    
    private int sizeBelowDepth(int k, Node x, int depth){
    	if (x == null) return 0;
    	return sizeBelowDepth(k, x.left, depth + 1) + sizeBelowDepth(k, x.right, depth + 1) + (k < depth ? 1 : 0);
    }
    
    // the number of nodes in the tree, at exactly height k
    public int sizeAtHeight(int k) { return sizeAtHeight(k, root); }
    
    private int sizeAtHeight(int k, Node x){
    	if (x == null) return 0;
    	return sizeAtHeight(k, x.left) + sizeAtHeight(k, x.right) + (x.height == k ? 1 : 0);
    }
    
    // the number of nodes in the tree, above height k (not including k) 
    public int sizeAboveHeight(int k) { return sizeAboveHeight(k, root); }
    
    private int sizeAboveHeight(int k, Node x){
    	if (x == null) return 0;
    	return sizeAboveHeight(k, x.left) + sizeAboveHeight(k, x.right) + (x.height > k ? 1 : 0);
    }
    
    // the number of nodes in the tree, below height k (not including k) 
    public int sizeBelowHeight(int k) { return sizeBelowHeight(k, root); }
    
    private int sizeBelowHeight(int k, Node x){
    	if (x == null) return 0;
    	return sizeBelowHeight(k, x.left) + sizeBelowHeight(k, x.right) + (x.height < k ? 1 : 0);
    }
    
    // tree is balanced if for every node, size of left == size of right
    public boolean isPerfectlyBalancedS() { return isPerfectlyBalancedS(root) > -1; }
    
    private int isPerfectlyBalancedS(Node x){
    	if (x == null) return 0;
    	int lft = isPerfectlyBalancedS(x.left);
    	int rgt = isPerfectlyBalancedS(x.right);
    	if ((lft == rgt) && (lft != -1)) return lft + 1;
    	return -1;
    }
    
    // tree is balanced if for every node, height of left == height of right
    public boolean isPerfectlyBalancedH() { return isPerfectlyBalancedH(root) > -2;}
    
    private int isPerfectlyBalancedH(Node x){
    	if (x == null) return 0;
    	int lft = isPerfectlyBalancedH(x.left);
    	int rgt = isPerfectlyBalancedH(x.right);
    	if ((lft == rgt) && (lft != -2)) return lft + 1; 
    	return -2;
    }
    
    // tree is odd balanced if for every node, #odd children on left == # odd children on right
    public boolean isOddBalanced() { return isOddBalanced(root) > -1; }
    
    private int isOddBalanced(Node x){
    	if (x == null) return 0;
    	int lft = isOddBalanced(x.left);
    	int rgt = isOddBalanced(x.right);
    	if ((lft == rgt) && (lft != -1)) return lft + (x.key % 2 == 1 ? 1 : 0);
    	return -1;
    }
    
    // tree is semi-balanced if every node is semi-balanced
    // A node with 0 children is semi-balanced.
    // A node with 1 child is NOT semi-balanced.
    // A node with 2 chilldren is semi-balanced if (size-of-larger-child / size-of-smaller-child) <= 2
    public boolean isSemiBalanced() { return isSemiBalanced(root) > -1; }
    
    private int isSemiBalanced(Node x){
    	if (x == null) return 0;
    	if (x.right == null && x.left == null) return 1;
    	if (x.right != null && x.left != null){
    		int lft = isSemiBalanced(x.left);
    		int rgt = isSemiBalanced(x.right);
    		int check = (lft > rgt) ? (lft / rgt) : (rgt / lft);
    		if (check <= 2 && (lft != -1 && rgt != -1)) return lft + rgt + 1;
    	}
    	return -1;
    }

    // remove all subtrees with odd roots (if node is odd, remove it and its children)
    public void removeOddSubtrees () { root = removeOddSubtrees(root); }
    
    private Node removeOddSubtrees(Node x){
    	if (x == null || x.key % 2 == 1) return null;
    	x.right = removeOddSubtrees(x.right);
    	x.left = removeOddSubtrees(x.left);
    	return x;
    }
        
    // remove all subtrees below depth k from the original tree
    public void removeDepth(int k) { root = removeDepth(k, root, 0); }
    
    private Node removeDepth(int k, Node x, int depth){
    	if (x == null || depth > k) return null;
    	x.right = removeDepth(k, x.right, depth + 1);
    	x.left = removeDepth(k, x.left, depth + 1);
    	return x;
    	
    }

    // remove all leaves from the original tree
    public void removeLeaves() { root = removeLeaves(root); }
    
    private Node removeLeaves(Node x){
    	if (x == null || (x.right == null && x.left == null)) return null;
    	x.right = removeLeaves(x.right);
    	x.left = removeLeaves(x.left);
    	return x;
    }
    
    // remove all subtrees of height k from the original tree
    public void removeHeight(int k) { root = removeHeight(k, root); }
    
    private Node removeHeight(int k, Node x){
    	if (x == null || x.height == k) return null;
    	x.right = removeHeight(k, x.right);
    	x.left = removeHeight(k, x.left);
    	return x;
    }
    
    // remove all nodes that have only one child by "promoting" that child
    public void removeSingles() { root = removeSingles(root); }
    
    private Node removeSingles(Node x){
    	if (x == null) return x;
    	x.left = removeSingles(x.left);
    	x.right = removeSingles(x.right);
    	if (x.right == null && x.left != null) x = x.left;
    	if (x.left == null && x.right != null) x = x.right;
    	return x;
    }
    
    // add a child with key=0 to all nodes that have only one child
    // (you do not need to retain symmetric order or uniqueness of keys, obviously)
    public void addZeroToSingles() { root = addZeroToSingles(root); }
    
    private Node addZeroToSingles(Node x){
    	if (x == null) return x;
    	if (x.right == null && x.left != null) x.right = new Node(0);
    	if (x.left == null && x.right != null) x.left = new Node(0);
    	x.left = addZeroToSingles(x.left);
    	x.right = addZeroToSingles(x.right);
    	return x;
    }

    /* ***************************************************************************
     * Some methods to create and display trees (don't change these)
     *****************************************************************************/    
    public void put(int key) { root = put(root, key); }
    private Node put(Node n, int key) {
        if (n == null) return new Node(key);
        if      (key < n.key) n.left  = put(n.left,  key);
        else if (key > n.key) n.right = put(n.right, key);
        n.height = height(n);
        return n;
    }
    public MyIntSET copy () {
        MyIntSET that = new MyIntSET ();
        for (int key : levelOrder())
            that.put (key);
        return that;
    }
    public Iterable<Integer> levelOrder() {
        Queue<Integer> keys = new Queue<Integer>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node n = queue.dequeue();
            if (n == null) continue;
            keys.enqueue(n.key);
            queue.enqueue(n.left);
            queue.enqueue(n.right);
        }
        return keys;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int key: levelOrder()) 
            sb.append (key + " ");
        return sb.toString ();
    }
    public void drawTree() {
        if (root != null) {
            StdDraw.setPenColor (StdDraw.BLACK);
            StdDraw.setCanvasSize(1200,700);
            drawTree(root, .5, 1, .25, 0);
        }
    }
    private void drawTree (Node n, double x, double y, double range, int depth) {
        final int CUTOFF = 5;
        StdDraw.text (x, y, Integer.toString (n.key));
        StdDraw.setPenRadius (.007);
        if (n.left != null && depth != CUTOFF) {
            StdDraw.line (x-range, y-.13, x-.01, y-.01);
            drawTree (n.left, x-range, y-.15, range*.5, depth+1);
        }
        if (n.right != null && depth != CUTOFF) {
            StdDraw.line (x+range, y-.13, x+.01, y-.01);
            drawTree (n.right, x+range, y-.15, range*.5, depth+1);
        }
    }
    /* ***************************************************************************
     *  Test client
     *****************************************************************************/
    // create a tree from a string
    public static MyIntSET fromString (String ints) {
        MyIntSET set = new MyIntSET ();
        for (String s : ints.split (" ")) 
            try { set.put (Integer.parseInt (s)); } catch (NumberFormatException e) {}
        return set;
    }
    public static String show(MyIntSET set, boolean draw) {
        if (draw) { set.drawTree (); StdDraw.show (2000); }
        return set.toString ();
    }
    public static void test(String s) {        
        MyIntSET set = MyIntSET.fromString(s);
        StdOut.printf ("size()                 = %d\n", set.size());
        StdOut.printf ("height()               = %d\n", set.height());
        StdOut.printf ("sizeOdd()              = %d\n", set.sizeOdd());
        StdOut.printf ("sizeAtDepth(2)         = %d\n", set.sizeAtDepth(2));
        StdOut.printf ("sizeAboveDepth(2)      = %d\n", set.sizeAboveDepth(2));
        StdOut.printf ("sizeBelowDepth(2)      = %d\n", set.sizeBelowDepth(2));
        StdOut.printf ("sizeAtHeight(2)        = %d\n", set.sizeAtHeight(2));
        StdOut.printf ("sizeAboveHeight(2)     = %d\n", set.sizeAboveHeight(2));
        StdOut.printf ("sizeBelowHeight(2)     = %d\n", set.sizeBelowHeight(2));
        StdOut.printf ("isPerfectlyBalancedS() = %b\n", set.isPerfectlyBalancedS());
        StdOut.printf ("isPerfectlyBalancedH() = %b\n", set.isPerfectlyBalancedH());
        StdOut.printf ("isOddBalanced()        = %b\n", set.isOddBalanced());
        StdOut.printf ("isSemiBalanced()       = %b\n", set.isSemiBalanced());

        MyIntSET tmp;                              StdOut.printf ("original               = %s\n", show (set, true));
//        tmp = set.copy(); tmp.removeOddSubtrees(); StdOut.printf ("removeOddSubtrees()    = %s\n", show (tmp, true));
//        tmp = set.copy(); tmp.removeDepth(2);      StdOut.printf ("removeDepth(2)         = %s\n", show (tmp, true));
//        tmp = set.copy(); tmp.removeLeaves();      StdOut.printf ("removeLeaves()         = %s\n", show (tmp, true));
//        tmp = set.copy(); tmp.removeHeight(2);     StdOut.printf ("removeHeight(2)        = %s\n", show (tmp, true));
        tmp = set.copy(); tmp.removeSingles();     StdOut.printf ("removeSingles()        = %s\n", show (tmp, true));
//        tmp = set.copy(); tmp.addZeroToSingles();  StdOut.printf ("addZeroToSingles()     = %s\n", show (tmp, true));
    }
    public static void main(String[] args) {
        test ("90 30 100 10 81 20 40 60 50 70 62 63 64");        
        //test ("40 20 30 60 10 30 50 70"
        //		+ "");        
        //test ("");        
        //test ("1");
   }
}