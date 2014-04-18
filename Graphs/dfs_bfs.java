/*
 * Alan Wright
 * Group: Username Invalid
 * Date: 4/8/2013
 * 
 * Creates a graph with 2 components and 7 nodes. Then performs both a
 * BFS and DFS on the graph (represented in an adjacency matrix). 
 *
 * BFS is good for finding components, shortest path by edge connections, testing
 *  for bipartiteness, max flow.
 *
 * DFS is good for finding components, finding bridges, solving mazes, topological sort. 
 *
 * BFS                       DFS
 * Runtime: O(|E|)           Runtime: O(|E|)
 * Space: O(|V|)             Space: O(|V|)
 */

import java.util.*;


public class dfs_bfs{
	
	public static void main (String[] args) {
		
		//Generate our graph in an adjacency matrix
		boolean[][] adjMatrix = createGraph();
		
		System.out.println("Performing DFS...");
		dfs(adjMatrix);
		System.out.println("----------------------");
		System.out.println();
		
		System.out.println("Performing BFS...");
		bfs(adjMatrix);
		System.out.println("----------------------");
		System.out.println();
	}
	
	
	//Creates the following bidirectional graph:
	// (Note: 2 components, 7 total nodes)
	/*
	 * 0 ----- 1
	 * |       | 
	 * |       |    4 --- 5
	 * |       |     \  /
	 * 2 ----- 3       6
	 */
	public static boolean[][] createGraph() {
		
		boolean[][] adj = new boolean[7][7];
		adj[0][1] = adj[1][0] = true;
		adj[0][2] = adj[2][0] = true;
		adj[1][3] = adj[3][1] = true;
		adj[2][3] = adj[3][2] = true;
		
		adj[4][5] = adj[5][4] = true;
		adj[5][6] = adj[6][5] = true;
		adj[4][6] = adj[6][4] = true;
		
		return adj;
	}
	
	//Performs DFS on the adjMatrix or graph passed in
	public static void dfs(boolean[][] adjMatrix) {
		
		//get our number of nodes
		int N = adjMatrix.length;
		
		//Create visited matrix
		boolean[] visited = new boolean[N];
		int numComponents = 0;
		
		//Perform DFS on unvisited nodes (we are finding components)
		for(int i = 0; i < N; i++){
			if(!visited[i]) {
				
				numComponents++;
				System.out.println("DFS found new component " + numComponents + " starting from node " + i);
				dfsRec(adjMatrix, N, i, visited);
				System.out.println("DFS finished with component " + numComponents);
			}
		}
		
		System.out.println();
		System.out.println("DFS has finished and found " + numComponents + " components.");
		System.out.println();
	}
	
	//Recursively performs DFS at starting note
	public static void dfsRec(boolean[][] adjMatrix, int N, int pos, boolean[] visited) {
		
		System.out.println("At node " + pos + " in the DFS");
		
		//Mark visited
		visited[pos] = true;
		
		//Look at each node...
		for(int i = 0; i < N; i++) {
			
			//If connected, and not visited
			if(adjMatrix[pos][i] && !visited[i]) {
				
				//Recursively look at the connected nodes
				System.out.println("Moving to connected node " + i);
				dfsRec(adjMatrix, N, i, visited);
			}
		}
		System.out.println("Finished with node " + pos);
	}
	
	//Performs bfs on the adjMatrix or graph passed in
	public static void bfs(boolean[][] adjMatrix) {
		
		//get our number of nodes
		int N = adjMatrix.length;
		
		//Create visited matrix
		boolean[] visited = new boolean[N];
		int numComponents = 0;
		
		//Perform DFS on unvisited nodes (we are finding components)
		for(int i = 0; i < N; i++){
			if(!visited[i]) {
				
				numComponents++;
				System.out.println("BFS found new component " + numComponents + " starting from node " + i);
				bfs(adjMatrix, N, i, visited);
				System.out.println("BFS finished with component " + numComponents);
			}
		}
		
		System.out.println();
		System.out.println("BFS has finished and found " + numComponents + " components.");
		System.out.println();
	}
	
	//Performs BFS from start node using a queue
	public static void bfs(boolean[][] adjMatrix, int N, int pos, boolean[] visited) {
		
		//Create a queue
		ArrayList<Integer> q = new ArrayList<Integer>();
		
		//Add our start node and mark it as visited
		q.add(pos);
		visited[pos] = true;
		
		while(!q.isEmpty()) {
			
			//Get the front of the queue to process
			int curr = q.remove(0);
			System.out.println("At node " + curr + " in the BFS");
			
			//Add connected, unvisited nodes to the queue
			for(int i = 0; i < N; i++) {
				if(adjMatrix[curr][i] && !visited[i]) {
					q.add(i);
					visited[i] = true;
					System.out.println("Found connected node " + i + " and adding to queue");
				}
			}
			System.out.println("Finished with node " + curr);
		}
	}
}