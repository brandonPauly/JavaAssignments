/*
 Brandon Pauly
 CSC 300 - 402
 Dr. Tomuro
 30 October 2013
*/

import stdlib.*;

import java.util.LinkedList;
import java.util.ListIterator;

public class Josephus 
{
	
	public static void main (String[] args)
	{
		StdOut.print("Enter an integer for number of moves: ");
	    int m = StdIn.readInt();
	    StdOut.print("Enter an integer for number of participants: ");
	    int n = StdIn.readInt();
	    
	    LinkedList<Integer> JoseList = new LinkedList<Integer>();
	    
	    for (int i = 1; i < n+1; i++)
	    	JoseList.add(new Integer(i));
	    
	    ListIterator<Integer> JoseIter = JoseList.listIterator(0);
	    
	    while (JoseList.size() > 1)
	    {
	    	Integer prev = null;
	    	
	    		for (int j = 0; j < m+1; j++)
	    		{
					if (!JoseIter.hasNext())
	    			{
	    			    JoseIter = JoseList.listIterator(0);
	    			    prev = JoseIter.next();
	    			}
	    			else
	    			    prev = JoseIter.next();
	    		}
	    		StdOut.println("Person eliminated: " + prev);
	    		JoseIter.remove();
	    }
	    StdOut.println("*** AND THE WINNER IS: " + JoseList.get(0) + "!!! ***");
	}
}