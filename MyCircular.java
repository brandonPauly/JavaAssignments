package algs12;
import stdlib.*;

/*
Brandon Pauly
CSC 300 - 402
Dr. Tomuro
Assignment 2
*/

public class MyCircular
{
  public static int circIndex(String first, String second)
    { 
	  if (first.length() == second.length())
		  return first.concat(first).indexOf(second); 
	  else
		  return -1;
    }


  public static void main(String[] args)
  {
    String s1, s2;

    StdOut.print("String 1: ");
    s1 = StdIn.readString();
    StdOut.print("String 2: ");
    s2 = StdIn.readString();

    // Call the function circIndex() to obtain the index of the
    // beginning of a circular rotation.  Returned index is 0-based.
    // -1 means not a circ. rotation.
    int cindex = circIndex(s1, s2);

    // Display the result/message to the terminal.

    if (cindex >= 0)
    {
    	StdOut.println("'" + s2 + "' is a circular rotation of '" + s1 + "' starting at position " + cindex);
    	StdOut.println(s1);
    	
    	for (int i = 0; i < cindex; i++)
    	  StdOut.print(" ");
    	
    	StdOut.print(s2);
    }
    else
    	StdOut.println("'" + s2 + "' is not a circular rotation of '" + s1 + "'");
  }
}
