package algs42;

/*
 Brandon Pauly
 CSC 301 - 502
 Dr. Riely
 */

import stdlib.In;
import stdlib.StdOut;
import algs13.Queue;

public class MyDegrees {
	private int[] outDegrees;
	private int[] inDegrees;
	private Queue<Integer> sourceQ;
	private Queue<Integer> sinkQ;
	private boolean map = true;
	
	
    // constructor  
    MyDegrees(Digraph G) {
    	int graphSize = G.V();
    	outDegrees = new int[graphSize];
    	inDegrees = new int[graphSize];
    	sourceQ = new Queue<Integer>();
    	sinkQ = new Queue<Integer>();
    	for (int v = 0; v < graphSize; v++){
    		int out = 0;
    		for (int w : G.adj(v)){
    			out++;
    			inDegrees[w]++;
    		}
    		outDegrees[v] = out;
    		if (out == 0) sinkQ.enqueue(v);
    		if (out != 1) map = false;
    	}
        for (int v = 0; v < graphSize; v++){
        	if (inDegrees[v] == 0) sourceQ.enqueue(v);
        }
    }
    

    // indegree of v
    int indegree(int v) { return inDegrees[v]; }

    // outdegree of v
    int outdegree(int v) { return outDegrees[v]; }

    // sources
    Iterable<Integer> sources() { return sourceQ; }

    // sinks
    Iterable<Integer> sinks() { return sinkQ; }

    // is G a map?
    boolean isMap() { return map; }
    
    public static void main(String[] args) {
//      args = new String [] { "data/tinyDG.txt" };
//      final In in = new In(args[0]);
//      final Digraph G = new Digraph(in);
//      StdOut.println(G);
//      MyDegrees testG = new MyDegrees(G);
//      StdOut.println(testG.isMap());
  }
}


