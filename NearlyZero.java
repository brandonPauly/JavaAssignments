import java.io.* ;

class NearlyZero
{
 public static void main ( String[] args )
 {
   int[] data = {3, 1, 5, 7, 4, 12, -3, 8, -2};
   
   int lstDiff = Math.abs(data[0]);
   int clsst = data[0];
   
   
   // find the element nearest to zero
   for (int i = 1; i < data.length; i++)
   {
	   if(Math.abs(data[i]) < lstDiff)
	   {
		   lstDiff = Math.abs(data[i]);
		   clsst = data[i];
	   }
   }
     
   // write out the element nearest to zero
   System.out.println("Closest to zero: " + clsst);
 }
}