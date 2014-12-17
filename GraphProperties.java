/*
 Brandon Pauly
 CSC 301-502
 Dr. Riely
 */

package algs41;


import java.util.Arrays;

import stdlib.In;
import stdlib.StdOut;
import algs13.Queue;

public class GraphProperties {
	private static final int INFINITY = Integer.MAX_VALUE;
	private int[] eccents;
	private final boolean[] marked;
	
	// constructor (exception if G not connected)
	public GraphProperties(Graph G){
		int count = 0;
		marked = new boolean[G.V()];
		for (int n = 0; n < G.V(); n++) {
            if (!marked[n]) {
                dfs(G, n);
                count++;
            }
		}
		if(count != 1) throw new IllegalArgumentException("Graph is disconnected");
		eccents = new int[G.V()];
		for(int v = 0; v < G.V(); v++)
			bfs(G, v);
	}
	
	private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
	
	private void bfs(Graph G, int v){
		boolean[] tag = new boolean[G.V()];
		Queue<Integer> queue = new Queue<Integer>();
		int[] disTo = new int[G.V()];
		for (int tempv = 0; tempv < G.V(); tempv++) disTo[v] = INFINITY;
		tag[v] = true;
		disTo[v] = 0;
		queue.enqueue(v);
		while (!queue.isEmpty()){
			int nextV = queue.dequeue();
			for (int w : G.adj(nextV))
				if (!tag[w]){
					disTo[w] = disTo[nextV] + 1;
					tag[w] = true;
					queue.enqueue(w);
				}
		}
		int ecc = disTo[0];
		int dLen = disTo.length;

		for (int i = 1; i < dLen; i++){
		    if (disTo[i] > ecc) ecc = disTo[i];
		}
		eccents[v] = ecc;
	}
	
	// eccentricity of v
	public int eccentricity(int v){
		return eccents[v];
	}
	
	// diameter of G
	public int diameter(){
		int diam = eccents[0];
		int length = eccents.length;
		for (int i = 1; i < length; i++) if (eccents[i] > diam) diam = eccents[i];
		return diam;
	}
	
    //  radius of G
	public int radius(){
		int rad = eccents[0];
		int length = eccents.length;
		for (int i = 1; i < length; i++) if (eccents[i] < rad) rad = eccents[i];
		return rad;
	}
	  
	// a center of G
	public int center(){
		int centVal = this.radius();
		int length = eccents.length;
		int cent = -1;
		for (int i = 0; i < length; i++) if (eccents[i] == centVal) cent = i;
		return cent;
	}
	
    public static void main(String[] args) {
//        args = new String [] { "data/tinyCG.txt" };
//        final In in = new In(args[0]);
//        final Graph G = new Graph(in);
//        StdOut.println(G);
//        GraphProperties testG = new GraphProperties(G);
//        StdOut.println(testG.eccentricity(0));
    }

}
