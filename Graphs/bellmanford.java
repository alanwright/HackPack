/*
 * Alan Wright
 * 4/11/2014
 * 
 * Program finds shortest path between a start node and all other nodes as well as
 *  maximum path using Bellman Ford.
 *  
 * Bellman Ford is good at finding shortest path between two nodes or one node and \
 *  all other nodes. Edge weights can be negated to find the maximum path. Does not handle
 *  negative edge weights with positive edge weights.
 *  
 *  Runtime: O(V*E)
 *  Space: O(V)
 */
import java.util.*;

public class bellmanford {

	public static final int V = 5;
	public static final int E = 7;
	public static final int MAX = 10000;

	public static void main(String[] args) {
			
		//Create the graph
		edge[] edgeList = createGraph();

		// Bellman ford to find the longest path
		System.out.println("Finding the min paths from node 0");
		int[] paths = new int[V];
		int[] minEstimates = bellmanFord(edgeList, 0, paths);
		printPaths(0, minEstimates, paths);

		// if we negate each edge we can find the max path
		for(int i = 0; i < edgeList.length; i++) 
			edgeList[i].weight = -1 * edgeList[i].weight;
		
		//Find the maximum path
		System.out.println("Finding the max paths from node 0");
		paths = new int[V];
		int[] maxEstimates = bellmanFord(edgeList, 0, paths);
		printPaths(0, maxEstimates, paths);
	}
	
	/*
	 * Creates the following graph with 5 nodes
	 *        (3)
	 *    0 -----> 1 <
     *    | \ (3)  ^   \ (4)
     *(6) |   \    |(1) 4
     *    v     \> |   / (2)
     *    2 ------>3 <
     *        (2)
     *  view less confusing image: http://bit.ly/1sNfp56	 
	 */
	public static edge[] createGraph() {
		edge[] edgeList = new edge[E];
		edgeList[0] = new edge(0,2,6);
		edgeList[1] = new edge(2,3,2);
		edgeList[2] = new edge(0,3,3);
		edgeList[3] = new edge(1,0,3);
		edgeList[4] = new edge(4,1,4);
		edgeList[5] = new edge(4,3,2);
		edgeList[6] = new edge(3,1,1);
		return edgeList;
	}
	
	//Performs bellman ford to find the least expensive path from the start node
	// to all other nodes using the edge list. Stores previous nodes in pred to rebuild
	// the path. 
	public static int[] bellmanFord(edge[] edgeList, int start,int[] pred) {

		//Create and initialize our estimates array. 
		int[] estimates = new int[V];
		for(int i = 0; i < V; i++) {
			estimates[i] = MAX;
			pred[i] = -1;
		}
		estimates[start] = 0;

		
		for (int i = 0; i < V; i++) {
			//Attempt going through each edge to see if it is better
			for (edge e: edgeList) {
				if (estimates[e.start] + e.weight < estimates[e.end]) {
					estimates[e.end] = estimates[e.start]+ e.weight;
					pred[e.end] = e.start;
				}
			}
		}
		
		//Return estimates
		return estimates;
	}
	
	//Prints paths and estimates from the start node to all other nodes. 
	public static void printPaths(int start, int[] estimates, int[] paths) {
		for(int j = 0; j < V; j++) {
			if(start == j) continue;

			System.out.println("Weight from " + start + " to " + j + " is " + estimates[j]);
			
			int end = j;
			System.out.println(paths[end]);
			
			//Check to see if a path exists. 
			if(paths[end] == -1) {
				System.out.println("No path from " + start + " to " + end);
				System.out.println();
			}
			else {
				
				//Work backwards from end to start looking at previous node
				String currPath = end + "";
				while (paths[end] != start) {
					currPath = paths[end] + " -> " + currPath;
					end = paths[end];
				}
				System.out.println(start + " -> " + currPath);
				System.out.println();
			}
		}
	}
}

class edge {

	public int start;
	public int end;
	public int weight;

	public edge(int s, int e, int w) {
		start = s;
		end = e;
		weight = w;
	}
}