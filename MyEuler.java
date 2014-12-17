package algs42;

/*
 Brandon Pauly
 CSC 301-502
 Dr. Riely
 */

import stdlib.*;
import algs13.Queue;
import algs13.Stack;

public class MyEuler {
    private final Queue<Integer>[] adj;
    private int E;
    private boolean euler = true;
    private Stack<Integer> euTour;

    @SuppressWarnings("unchecked") // annoying java generic/array stuff
    // constructor  
    MyEuler(Digraph G)  {
    	// create local copy of graph
        adj = new Queue[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = new Queue<Integer>();
            for (int w : G.adj(v))
                adj[v].enqueue(w);
        }
        E = G.E();
        euTour = new Stack<Integer>();
        int s = 0;
        for (int v = 0; v < G.V(); v++)
        	if (adj[v].size() > 0)
        		s = v;
        dfsStack(G,s);
        if (euTour.size() != E+1)
        	euler = false;
        if (euTour.size() == 1)
        	euTour.pop();
    }
    
    private void dfsStack(Digraph G, int s) {
        Stack<Integer> traversed = new Stack<Integer>();
        traversed.push(s);
        while (!traversed.isEmpty()) {
            int t = traversed.pop();
            euTour.push(t);
            int u = t;
            while (!adj[u].isEmpty()) {
                traversed.push(u);
                u = adj[u].dequeue();
            }
            if (u != t) euler = false;
        }
    }

    // does G have a directed Eulerian cycle?
    boolean isEulerian () { return euler; }

    // return a Eulerian cycle (null if the graph is not eulerian)
    Iterable<Integer> tour () { 
    	if (!euler) return null;
    	return euTour;
    }

    public static void main(String[] args) {
        //args = new String[] { "data/tinyDG.txt" }; // NO
        //args = new String[] { "data/tinyDGeuler1.txt" }; // YES
        //args = new String[] { "data/tinyDGeuler2.txt" }; // YES
        //args = new String[] { "data/tinyDGeuler3.txt" }; // YES
        //args = new String[] { "data/tinyDGeuler4.txt" }; // YES
        //args = new String[] { "data/tinyDGeuler5.txt" }; // NO
        args = new String[] { "data/tinyDGeuler6.txt" }; // YES
        //args = new String[] { "10", "20" };
    	//args = new String[] { "data/tinyDGeuler9.txt" };
    	//args = new String[] { "data/emptytinyDGeuler.txt" };
    	//args = new String[] { "data/tinyDAG.txt" };

        Digraph G;
        if (args.length == 1) {
            final In in = new In(args[0]);
            G = new Digraph(in);
        } else {
            final int V = Integer.parseInt(args[0]);
            final int E = Integer.parseInt(args[1]);

            // random graph of V vertices and approximately E edges
            // with indegree[v] = outdegree[v] for all vertices
            G = new Digraph(V);
            final int[] indegree  = new int[V];
            final int[] outdegree = new int[V];
            int deficit = 0;
            for (int i = 0; i < E - deficit/2; i++) {
                final int v = StdRandom.uniform(V);
                final int w = StdRandom.uniform(V);
                if (v == w) { i--; continue; }
                G.addEdge(v, w);
                if (outdegree[v] >= indegree[v]) deficit++;
                else                             deficit--;
                outdegree[v]++;
                if (indegree[w] >= outdegree[w]) deficit++;
                else                             deficit--;
                indegree[w]++;
            }

            while (deficit > 0) {
                final int v = StdRandom.uniform(V);
                final int w = StdRandom.uniform(V);
                if (v == w) continue;
                if (outdegree[v] >= indegree[v]) continue;
                if (indegree[w]  >= outdegree[w]) continue;
                G.addEdge(v, w);
                if (outdegree[v] >= indegree[v]) deficit++;
                else                             deficit--;
                outdegree[v]++;
                if (indegree[w] >= outdegree[w]) deficit++;
                else                             deficit--;
                indegree[w]++;
            }
        }

        StdOut.println(G);
        final MyEuler euler = new MyEuler(G);
        if (euler.isEulerian()) {
            for (final int v : euler.tour()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Not eulerian");
        }
    } 
}
