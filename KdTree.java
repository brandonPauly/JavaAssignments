/*
 Brandon Pauly
 CSC 301
 Assignment 2 - resubmit
 Dr. Riely
 */

package algs32.kdtree;
import algs12.Point2D;
import algs13.Queue;
import stdlib.*;

public class KdTree {

    private static class Node {
        public Point2D point;
        private Node left, right;
        private int leaves;
        
        public Node(Point2D point, int leaves)
        {
        	this.point = point; 
        	this.leaves = leaves;
        }
    }
    Node root;

    /* construct an empty set of points */
    public KdTree() {        
        root = null;
    }

    /* is the set empty? */
    public boolean isEmpty() { 
        if (root == null)
        	return true;
        return false; 
    }

    /* number of points in the set */
    public int size() { 
        return size(root);
    }
    
    private int size(Node a)
    {
    	if (a == null)
    		return 0;
    	else
    		return a.leaves;
    }

    /* add the point p to the set (if it is not already in the set) */
    public void insert(Point2D p) {
        root = insert(root, false, p);
    }
    
    private Node insert(Node a, boolean d, Point2D p)
    {
    	if (a == null)
    		return new Node(p, 1);
    	if (a.point.equals(p))
    		return a;
    	if (d == false)
    	{
    		if (p.x() < a.point.x())
    			a.left = insert(a.left, !d, p);
    		else
    			a.right = insert(a.right, !d, p);
    			
    			
    	}
    	else
    	{
    		if (p.y() < a.point.y())
    			a.left = insert(a.left, !d, p);
    		else
    			a.right = insert(a.right, !d, p);
    	}
    	a.leaves = size(a.left) + size(a.right) + 1;
    	return a;
    }

    /* does the set contain the point p? */
    public boolean contains(Point2D p) 
    { 
         return contains(root, p, false);
    }
    
    private boolean contains(Node x, Point2D p, boolean depth)
    {
    	if (x == null)
    		return false;
    	
    	if (x.point.equals(p))
    		return true;
    	
    	if (depth == false)
    	{
    		if (p.x() >= x.point.x())
    			return contains(x.right, p, !depth);
    		else
    			return contains(x.left, p, !depth);
    	}
    	
    	else
    	{
    		if (p.y() >= x.point.y())
    			return contains(x.right, p, !depth);
    		else
    			return contains(x.left, p, !depth);
    	}   
    }

    /* draw all of the points to standard draw */
    public void draw() 
    { 
        draw(root, new RectHV (0, 0, 1, 1), false);
    }
    
    private static void draw(Node n, RectHV area, boolean d)
    {
    	if (n == null)
    		return;
    	
    	StdDraw.setPenRadius(.003);
    	if (d == false) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line (n.point.x(), area.ymin(), n.point.x(), area.ymax());
            draw(n.right, new RectHV (n.point.x(), area.ymin(), area.xmax(), area.ymax()), !d);
            draw(n.left, new RectHV (area.xmin(), area.ymin(), n.point.x(), area.ymax()), !d);
            
        } 
    	else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line (area.xmin(), n.point.y(), area.xmax(), n.point.y());
            draw(n.left, new RectHV (area.xmin(), area.ymin(), area.xmax(), n.point.y()), !d);
            draw(n.right, new RectHV (area.xmin(), n.point.y(), area.xmax(), area.ymax()), !d);
        }

        StdDraw.setPenRadius(.007);
        StdDraw.setPenColor(StdDraw.BLACK);
        n.point.draw();
        
    }

    /* all points in the set that are inside the rectangle */
    public Iterable<Point2D> range(RectHV rect) { 
        Queue<Point2D> kdQ = new Queue<Point2D>();
        range(root, rect, kdQ, false);
        return kdQ;
    }
    
    private static void range(Node node, RectHV rect, Queue<Point2D> kdQ, boolean depth)
    {
    	if (node == null)
    		return;
    	if (rect.contains(node.point))
    		kdQ.enqueue(node.point);
    	if (depth == false)
    	{
    		if (node.point.x() <= rect.xmin())
    			range(node.right, rect, kdQ, !depth);
    		else if (node.point.x() > rect.xmax())
    			range(node.left, rect, kdQ, !depth);
    		else
    		{
    			range(node.right, rect, kdQ, !depth);
    			range(node.left, rect, kdQ, !depth);
    		}
    	}
    	else
    	{
    		if (node.point.y() <= rect.ymin())
    			range(node.right, rect, kdQ, !depth);
    		else if (node.point.y() > rect.ymax())
    			range(node.left, rect, kdQ, !depth);
    		else
    		{
    			range(node.right, rect, kdQ, !depth);
    			range(node.left, rect, kdQ, !depth);
    		}
    	}
    }

    /* a nearest neighbor in the set to p; null if set is empty */
    public Point2D nearest(Point2D p) {
    	if (root == null) return null;
    	Point2D champ = root.point;
        return nearest(root, p, champ, false); 
    }
    
    private Point2D nearest(Node node, Point2D p, Point2D champ, boolean depth)
    {
    	if (node == null)
    		return champ;
    	if (node.point.distanceTo(p) < p.distanceTo(champ))
    		champ = node.point;
		if (depth == false)
		{
			if (p.x() < node.point.x())
			{
				champ = nearest(node.left, p, champ, !depth);
				Point2D fakePnt = new Point2D(node.point.x(), p.y());
				if (p.distanceTo(fakePnt) < p.distanceTo(champ))
					champ =  nearest(node.right, p, champ, !depth);
			}
			else
			{
				champ = nearest(node.right, p, champ, !depth);
				Point2D fakePnt = new Point2D(node.point.x(), p.y());
				if (p.distanceTo(fakePnt) < p.distanceTo(champ))
					champ = nearest(node.left, p, champ, !depth);
			}
			
		}
		else
		{
			if (p.y() < node.point.y())
			{
				champ = nearest(node.left, p, champ, !depth);
				Point2D fakePnt = new Point2D(p.x(), node.point.y());
				if (p.distanceTo(fakePnt) < p.distanceTo(champ))
					champ =  nearest(node.right, p, champ, !depth);
			}
			else
			{
				champ = nearest(node.right, p, champ, !depth);
				Point2D fakePnt = new Point2D(p.x(), node.point.y());
				if (p.distanceTo(fakePnt) < p.distanceTo(champ))
					champ = nearest(node.left, p, champ, !depth);
			}
		}
		return champ;
    }
    
    public static void main(String[] args)
    {
//    	KdTree kdTree = new KdTree();
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.7, 0.4));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.3, 0.8));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.1, 0.4));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.8, 0.7));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.4, 0.2));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.9, 0.2));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.6, 0.5));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.2, 0.1));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.2, 0.3));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.7, 0.4));
//    	StdOut.println(kdTree.size());
//    	kdTree.insert(new Point2D(0.5, 0.8));
//    	StdOut.println(kdTree.size());
//    	
//    	StdOut.println(kdTree.contains(new Point2D(0.4, 0.2)));
//    	StdOut.println(kdTree.size());
//    	StdOut.println(kdTree.contains(new Point2D(0.9, 0.1)));
//    	StdOut.println(kdTree.size());
//    	
//    	Iterable<Point2D> Q = kdTree.range(new RectHV(0.2, 0.4, 0.7, 0.8));
//    	StdOut.println(Q.toString());
    	
    	
    }
}
