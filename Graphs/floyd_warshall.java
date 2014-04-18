/*
 * Alan Wright
 * Date: 4/9/2013
 * 
 * Contains functions for Floyd Warshall's shortest path algorithm. 
 *
 * Floyd Warshall works for finding shortest path in a directed, weighted graph and 
 * accounts for positive and negative edge weights. 
 * 
 * Runtime: O(v^3)
 * Space: O(v^2)
 */

import java.util.*;

public class floyd_warshall {

	public static final int MAX = 100000;
	public static final int NO_PATH = -1;
	
	public static void main (String[] args) {
		
		//Create graph
		int[][] dist = createGraph();
		
		//Initialize path building array
		int[][] path = initPaths(dist);
		
		//Call Floyd Warshall's algo
		int[][] shortestPaths = floydwarshall(dist, path);
		
		// Print our shortest paths!
		printShortestPaths(path, shortestPaths);
	}
	
	//Creates the following graph:
	// (Note: 4 nodes)
	/*
	 *         0 
	 *   (4) /(3)\ (-2)
	 *      1 --- 2   
	 *  (-1) \   / (2) 
	 *         3
	 * with directions 1 to 0, 0 to 2, 2 to 3, 3 to 1 and 2 to 3
	 * and weights shown in ()
	 */
	public static int[][] createGraph() {
		
		int[][] dist = new int[4][4];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				dist[i][j] = MAX;
				
		dist[1][0] = 4;
		dist[0][2] = -2;
		dist[2][3] = 2;
		dist[3][1] = -1;
		dist[1][2] = 3;
		
		return dist;
	}
	
	//Initializes the path array with the starting indices so we can
	// build the path we create
	public static int[][] initPaths(int[][] dist) {
		
		int[][] path = new int[4][4];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				if(dist[i][j] != MAX || i == j)
					path[i][j] = i;
				else
					path[i][j] = NO_PATH;
		
		return path;
	}
	
	/* 
	 * Performs Floyd Warshall's algorithm on the graph and returns a matrix
	 * storing the costs. (e.g. retval[i][j] = cost for shortest path from i to j)
	 * Also edits the path matrix to fill it with previous nodes used to rebuild
	 * the shortest paths in the printShortestPath method.
	 *
	 * Note: dist array is modified. If you want to keep original dist values, clone it
	 *  and modify/return the cloned array. 
	 */
	public static int[][] floydwarshall(int[][] dist, int[][] path) {
		
		int N = dist.length;
		
		//Find shorter paths that go through node k
    	for (int k = 0; k < N; k++) {

      		//Look at all possible pairs of points
      		for (int i = 0; i < N; i++) {
        		for (int j = 0; j < N; j++) {
        			
        			//If this new path through node k is better, save it
          			if (dist[i][k] + dist[k][j] < dist[i][j]) {
          				dist[i][j] = dist[i][k] + dist[k][j];
          				path[i][j] = path[k][j];
          			}
          		}
      		}
    	}
    	return dist;
	}
	
	//Prints out all the shortest paths
	public static void printShortestPaths(int[][] paths, int[][] cost) {
	
		//Look at all start/ends since the graph is directed (i.e. 1 -> 2 != 2 -> 1)
		for (int i = 0; i < paths.length; i++) {
      		for (int j = 0; j < paths[0].length; j++) {
      		
      			//Make sure we are not traveling to ourself
      			if( i != j) {
      			
      				int start = i, end = j;
      				String currPath = end + "";
    				System.out.println("Shortest path from " + start + " to " + end); 
      			
      				//Work backwards from end to start looking at previous node
    				while (paths[start][end] != start) {
    					currPath = paths[start][end] + " -> " + currPath;
    					end = paths[start][end];
    				}
    				System.out.println(start + " -> " + currPath);
    				System.out.println("With cost " + cost[i][j]);
    				System.out.println();
    			}
    		}
    	}
    }
}