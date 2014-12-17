
import stdlib.StdIn;
import stdlib.StdOut;

public class recStars
{
	public static void stars(int x)
	{
		if (x == 0)
			return;
		else
		{
			for (int i = 0; i < x; i++)
			{
				StdOut.print("*");
			}
			StdOut.println();
			stars(x-1);
			
		}
	}
    public static void main (String[] args)
    {
    	StdOut.print("Please enter an integer: ");
    	int a = StdIn.readInt();
    	stars(a);
    	
    }
}
