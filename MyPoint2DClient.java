package algs12;

import stdlib.*;

/*
 Brandon Pauly
 CSC 300 - 402
 Dr. Tomuro
 Assignment 2
 */

public class MyPoint2DClient 
{
    public static void main(String[] args) 
    {
    	
    	StdOut.print("Enter an integer for 'N' points: ");
    	int N = StdIn.readInt();

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.006);
        
        final Point2D[] arPoints = new Point2D[N];
        
        for (int i = 0; i < N; i++) 
        {
            final int x = StdRandom.uniform(100);
            final int y = StdRandom.uniform(100);
            arPoints[i] = new Point2D(x, y);
            arPoints[i].draw();
        }
        
        double leastDist = 101;
        Point2D cp1 = null, cp2 = null;
        
        for (int i = 0; i < N; i++)
        {
        	for (int j = i + 1; j < N; j++)
        	{
        		
        		if (arPoints[i].distanceTo(arPoints[j]) < leastDist)
        		{
        			leastDist = arPoints[i].distanceTo(arPoints[j]);
        			cp1 = arPoints[i];
        			cp2 = arPoints[j];
        		}
        		
        	}
        }

        StdDraw.setPenColor(StdDraw.RED);
        cp1.draw();
        cp2.draw();
        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        cp1.drawTo(cp2);
        

    }
}