/*
 Brandon Pauly
 CSC 301
 Assignment 1
 Dr. Riely
 */

package algs31;

import java.util.Iterator;
import stdlib.StdRandom;

public class MySTTest {

    private static void assertEquals(Object expected, Object actual) {
        if (expected == null) 
        {
            if (actual != null) throw new AssertionError();
        } 
        else 
        {
            if (! expected.equals (actual)) throw new AssertionError();
        }
    }

    public static void testMin() {
        // This is a fully random test.
        // These tests are better if you can come up with one.
        for (int numTrials = 100; numTrials > 0; numTrials--) {
            BinarySearchST<Integer,Integer> st = new BinarySearchST<Integer,Integer>();
            assertEquals (null, st.min ());
            int min = Integer.MAX_VALUE;
            for (int numAdded = 100; numAdded > 0; numAdded--) {
                int next = StdRandom.uniform (1000);
                st.put (next, next*10);
                min = Math.min (min, next);
                assertEquals (min, st.min ());
            }
        }
    }

    public static void testMax() {
        // This is a test with specific values
        // Not as good as a random test.  
        BinarySearchST<Integer,Integer> st = new BinarySearchST<Integer,Integer>();
        assertEquals (null, st.max()); // empty
        st.put (5, 50);
        assertEquals (5, st.max());  // one
        st.put (2, 20);
        assertEquals (5, st.max());  // does not change
        st.put (7, 70);
        assertEquals (7, st.max());  // changes
        st.put (1, 10);
        assertEquals (7, st.max());  // does not change
        st.put (8, 80);
        assertEquals (8, st.max());  // changes

        // symmetrically
        st = new BinarySearchST<Integer,Integer>();
        assertEquals (null, st.max()); // empty
        st.put (5, 50);
        assertEquals (5, st.max());  // one
        st.put (7, 70);
        assertEquals (7, st.max());  // changes
        st.put (2, 20);
        assertEquals (7, st.max());  // does not change
        st.put (8, 80);
        assertEquals (8, st.max());  // changes
        st.put (1, 10);
        assertEquals (8, st.max());  // does not change
    }

    public static void testFloor() 
    {
        for (int numTests = 0; numTests < 100; numTests++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	int tstFlr = StdRandom.uniform(1000);
        	assertEquals(null, symTab.floor(tstFlr));
        	int justAbv = -1;
        	
        	for (int numNums = 0; numNums < 100; numNums++)
        	{
        		int next = StdRandom.uniform(1000);
        		symTab.put(next, next*10);
        		if (next <= tstFlr && next > justAbv)
        			justAbv = next;
        		if (justAbv == -1)
        			assertEquals (null, symTab.floor(tstFlr));
        		else
        			assertEquals (justAbv, symTab.floor(tstFlr));
        	}
        }
    }

    public static void testCeiling() 
    {
        for (int numTests = 0; numTests < 100; numTests++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	int tstCeil = StdRandom.uniform(1000);
        	assertEquals(null, symTab.ceiling(tstCeil));
        	int justBel = Integer.MAX_VALUE;
        	
        	for (int numNums = 0; numNums < 100; numNums++)
        	{
        		int next = StdRandom.uniform(1000);
        		symTab.put(next, next*10);
        		if (next >= tstCeil && next < justBel)
        			justBel = next;
        		if (justBel == Integer.MAX_VALUE)
        			assertEquals(null, symTab.ceiling(tstCeil));
        		else
        			assertEquals(justBel, symTab.ceiling(tstCeil));
        	}
        }  
    }

    public static void testSelect() 
    {
        for (int i = 0; i < 100; i++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	int posit = 0;
        	assertEquals(null, symTab.select(posit));
        	int expVal = -1;
        	
        	
        	for (int j = 0; j < 100; j++)
        	{
        		int next = StdRandom.uniform(1000);
        		boolean contained = false;
				if (symTab.get(next) != null)
        			contained = true;
        		symTab.put(next, next*10);
        		if (j == 0)
        			expVal = next;
        		if (j > 0 && next < expVal && !contained)
        			posit++;
        		assertEquals(expVal, symTab.select(posit));
        	}
        }
    }

    public static void testRank() 
    {
        for (int testNum = 0; testNum < 100; testNum++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	int arbNum = StdRandom.uniform(1000);
        	assertEquals(0, symTab.rank(arbNum));
        	int expVal = 0;
        	
        	for (int insNum = 0; insNum < 100; insNum++)
        	{
        		int next = StdRandom.uniform(1000);
				if (insNum == 0)
        			symTab.put(arbNum, arbNum*10);
        		else
        		{
        			boolean contained = false;
				    if (symTab.get(next) != null)
        			    contained = true;
				    symTab.put(next, next*10);
				    if (!contained && next < arbNum)
				    	expVal++;
        		}
				assertEquals(expVal, symTab.rank(arbNum));
        	}
        	
        }
    }

    public static void testDeleteMin() 
    {
    	for (int testNum = 0; testNum < 100; testNum++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	boolean failed = false;
        	try {
        	  symTab.deleteMin();
        	  failed = true;
        	} catch (Error e) {}
        	if (failed) throw new AssertionError();
        	
        	int secondSm;
			int firstSm = secondSm = Integer.MAX_VALUE;
        	
        	for (int insNum = 0; insNum < 100; insNum++)
        	{
        		int next = StdRandom.uniform(1000);
        		symTab.put(next, next*10);
        		if (next < firstSm)
        		{
        			secondSm = firstSm;
        			firstSm = next;
        		}
        		if (next > firstSm && next < secondSm)
        			secondSm = next;
        	}
        	symTab.deleteMin();
        	assertEquals(secondSm, symTab.min());
        	
        }	
    }

    public static void testDeleteMax() 
    {
    	for (int testNum = 0; testNum < 100; testNum++)
        {
        	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
        	boolean failed = false;
        	try {
        	  symTab.deleteMin();
        	  failed = true;
        	} catch (Error e) {}
        	if (failed) throw new AssertionError();
        	
        	int secondLg;
			int firstLg = secondLg = -1;
        	
        	for (int insNum = 0; insNum < 100; insNum++)
        	{
        		int next = StdRandom.uniform(1000);
        		symTab.put(next, next*10);
        		if (next > firstLg)
        		{
        			secondLg = firstLg;
        			firstLg = next;
        		}
        		if (next < firstLg && next > secondLg)
        			secondLg = next;
        	}
        	symTab.deleteMax();
        	assertEquals(secondLg, symTab.max());
        	
        }	
    }

    public static void testKeys() 
    {
    	BinarySearchST<Integer, Integer> symTab = new BinarySearchST<Integer, Integer>();
    	Iterator<Integer> itSymTab = symTab.keys().iterator();
    	assertEquals(false, itSymTab.hasNext());
    	
    	symTab.put(9, 90);
    	itSymTab = symTab.keys().iterator();
        assertEquals(true, itSymTab.hasNext());
        assertEquals(9, itSymTab.next());
    	assertEquals(false, itSymTab.hasNext());
    	
    	int[] seq = {2, 3, 12, 7, 7, 1, 2, 9, 0};
    	
    	for (int i = 0; i < 9; i++)
    		symTab.put(seq[i], seq[i]*10);
    	
    	itSymTab = symTab.keys().iterator();
    	
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(0, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(1, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(2, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(3, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(7, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(9, itSymTab.next());
    	assertEquals(true, itSymTab.hasNext());
    	assertEquals(12, itSymTab.next());
    	assertEquals(false, itSymTab.hasNext());
    	
    }

    public static void main (String[] args) {
        testMin();
        testMax();
        testFloor();
        testCeiling();
        testSelect();
        testRank();
        testDeleteMin();
        testDeleteMax();
        testKeys();
    }
}