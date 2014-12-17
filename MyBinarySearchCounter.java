package algs12;
import stdlib.*;
import java.util.Arrays;

/*
Brandon Pauly
CSC 300 - 402
Dr. Tomuro
Assignment 2
 */

public class MyBinarySearchCounter
{
  // precondition: array a[] is sorted
  public static int rank(int key, int[] a, Counter ctr)
  {
    return rank (key, a, 0, a.length - 1, ctr);
  }

  public static int rank(int key, int[] a, int lo, int hi, Counter ctr)
  {
    while (lo <= hi) {
      // Key is in a[lo..hi] or not present.
      ctr.increment();
      final int mid = lo + (hi - lo) / 2;
      if      (key < a[mid]) return rank (key, a, lo, mid - 1, ctr);
      else if (key > a[mid]) return rank (key, a, mid + 1, hi, ctr);
      else return mid;
    }
    return -1;
  }

  public static void main(String[] args)
  {
    args = new String[] { "data/tinyW.txt" };
    StdIn.fromFile ("data/tinyT.txt");

    final int[] whitelist = new In(args[0]).readAllInts();
    Arrays.sort(whitelist);

    //** A Counter object declared
    Counter ctr = new Counter("boo");

    // read key; print if not in whitelist
    while (!StdIn.isEmpty()) {
      final int key = StdIn.readInt();
      if (rank(key, whitelist, ctr) == -1)
        StdOut.println(key);
    }

    StdOut.println("The total number of keys examined was " + ctr.tally());

  }
}
