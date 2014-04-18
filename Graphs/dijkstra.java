/*
 * Alan Wright
 * Group: Username Invalid
 * Date: 4/9/2013
 * 
 * Creates a graph with 7 nodes then performs both a regular and optimized Dijkstra's
 * algorithm on the graph (represented in an adjacency matrix). The optimized Dijkstra
 * uses a priority queue. 
 *
 * Dijkstras is good for finding the single source shortest paths in a graph 
 * with positive edge weights.  
 *
 * Regular Dijkstra          Optimized Dijkstra (Priority Queue)
 * Runtime: O(v^2)           Runtime: O(E*log(v))
 * Space: O(v^2)             Space: O(v^2)
 */

import java.util.*;

public class dijkstra {

	public static final int NO_EDGE = -1;

	public static void main(String[] args) {
		
		//Create our graph
		int[][] adj = createGraph();
		
		System.out.println("Running slower Dijkstras from node 0");
		System.out.println("----------------------------------------");
		//Run dijkstras from 0
		int start = 0;
		int[] dist = dijkstra(adj, start);
		
		for(int i = 0; i < dist.length; i++) {
			if(i != start) 
				System.out.println("Shortest Distance from " + start + " to " + i + " is " + dist[i]);
		}
		System.out.println();
		
		//Create our graph again
		node[] nodes = createGraphEdges();
		
		System.out.println("Running optimized Dijkstras from node 0");
		System.out.println("----------------------------------------");
		//Run dijkstras from 0
		node start2 = nodes[0];
		dijkstraPQ(nodes, start2);
		
		for(node n : nodes) {
			if(n != start2){
				System.out.println("Shortest Distance from " + start2.index + " to " + n.index + " is " + n.minWeight);
				System.out.print("With path: ");
				printShortestPathTo(n);	
			}
		}
	}
	
	//Creates the following bidirectional graph:
	// (Note: 7 total nodes)
	/*        
	 *           (3) 
	 *       1 ------- 3
	 *  (1)/ | --\  (4)| \(3)
	 *    0  |    \(2) |  5       7
	 *  (2)\ |(1)  \___| /(3)
	 *       2 ------- 4
	 *           (2)
	 */
	public static int[][] createGraph() {
		int[][] adj = new int[7][7];
		for(int i = 0; i < 7; i++)
			for(int j = 0; j < 7; j++)
				adj[i][j] = NO_EDGE;
		
		adj[0][1] = adj[1][0] = 1;
		adj[0][2] = adj[2][0] = 2;
		adj[1][2] = adj[2][1] = 1;
		adj[1][3] = adj[3][1] = 3;
		adj[1][4] = adj[4][1] = 2;
		adj[2][4] = adj[4][2] = 2;
		adj[3][4] = adj[4][3] = 4;
		adj[3][5] = adj[5][3] = 3;
		adj[4][5] = adj[5][4] = 3;
		
		return adj;
	}
	
	//Creates the same graph as the createGraph function, but uses the node and edge
	// classes to be stored in a priority queue. 
	public static node[] createGraphEdges() {
		node[] nodes = new node[7];
		for(int i = 0; i < 7; i++)
				nodes[i] = new node(i);
		
		nodes[0].adj = new edge[] { new edge(nodes[1], 1), new edge(nodes[2], 2)};
		nodes[1].adj = new edge[] { new edge(nodes[2], 1), new edge(nodes[3], 3), new edge(nodes[4], 2), new edge(nodes[0], 1)};
		nodes[2].adj = new edge[] { new edge(nodes[4], 2), new edge(nodes[0], 2), new edge(nodes[1], 1)};
		nodes[3].adj = new edge[] { new edge(nodes[4], 4), new edge(nodes[1], 3), new edge(nodes[5], 3)};
		nodes[4].adj = new edge[] { new edge(nodes[1], 2), new edge(nodes[2], 2), new edge(nodes[3], 4), new edge(nodes[5], 3)};
		nodes[5].adj = new edge[] { new edge(nodes[3], 3), new edge(nodes[4], 3)};
		nodes[6].adj = null;
		
		return nodes;
	}
	
	//Runs a slower version of dijkstra without a priority queue that finds the shortest
	// distance from the start node, to all other nodes. 
	// Note: a very large number indicates the nodes are not connected. 
	public static int[] dijkstra(int[][] adj, int source) {
		
		//Create an array to store our minimum estimates to a certain node
		int[] minEst = new int[adj.length];
		for(int i = 0; i< minEst.length; i++)
			minEst[i] = Integer.MAX_VALUE;
		
		//distance to source is 0
		minEst[source] = 0;
		
		//Create an array to signify we have found a shortest distance
		boolean[] minChosen = new boolean[minEst.length];
		
		for(int i = 0; i < minEst.length; i++) {
			int minV = 0;
			int minSeen = Integer.MAX_VALUE;
			
			//Find a minimal distance to a node that hasn't been found yet
			for(int j = 0; j < minEst.length; j++) {
				if(minChosen[j] == false && minEst[j] < minSeen) {
					minSeen = minEst[j];
					minV = j;
				}
			}
			
			//Mark the found node
			minChosen[minV] = true;
			
			//Check if going through minV to any other node is faster
			for(int j = 0; j <  minEst.length; j++) {
				
				//If the distance through minV to x, is less than the distance straight to x
				// save this new value. 
				if(adj[minV][j] != -1 && minEst[minV] + adj[minV][j] < minEst[j]) 
					minEst[j] = minEst[minV] + adj[minV][j];
			}
		}	
		return minEst;
	}
	
	//Runs an optimized version of dijkstra with a priority queue that finds the shortest
	// distance from the start node, to all other nodes. 
	// Note: a very large number indicates the nodes are not connected. 
	public static void dijkstraPQ(node[] nodes, node source) {
		
		//Note that nodes store their minimum distances you would initialize here
		
		//Initialize our priority queue with the start node
		source.minWeight = 0;
		PriorityQueue<node> q = new PriorityQueue<node>();
		q.add(source);
		
		while(!q.isEmpty()) {
			
			//Grab the top of the queue
			node curr = q.poll();
			
			//Break if no minimum weight here
			if(curr.minWeight == Integer.MAX_VALUE)
				break;
			
			//Look at each of the nodes edges
			for(edge e : curr.adj) {
			
				//Get the target of the edge
				node v = e.end;
				
				//Calculate the alternative weight of going through the new edge
				int alternative = curr.minWeight + e.weight;
				
				//If this alternative weight is better
				if(alternative < v.minWeight) {
				
					//Update the priority queue
					q.remove(v);
					v.minWeight = alternative;
					v.previous = curr;
					q.add(v);
				}
			}
		}
	}
	
	//Recursively bubbles backwards building the shortest path
	// and printing it. 
	public static void printShortestPathTo(node end) {
        List<node> path = new ArrayList<node>();
        for (node n = end; n != null; n = n.previous)
            path.add(n);
        Collections.reverse(path);
        
        int sum = 0;
        for(node n : path) { 
        	System.out.print(n.index + " -> ");
        }
        System.out.println("done");
    }
}

//node class that keeps track of a nodes shortest path from the source
// and all it's edges. Sorted according to weight for the priority queue. 
class node implements Comparable<node> {

	public int index;
    public edge[] adj;
    public int minWeight = Integer.MAX_VALUE;
    public node previous;
    
    public node(int i) {
    	index = i;
    }
    
    public int compareTo(node other) {
        return minWeight - other.minWeight;
    }
}

//edge class stores the weight and destination of an edge. 
class edge {

    public node end;
    public int weight;
    
    public edge(node e, int w) { 
    	end = e; 
    	weight = w; 
    }
}
