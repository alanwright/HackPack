import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;


public class topSort {
	
	
	
	public static void main(String[] args){
		
		
	}
	
	/**
	 * A topological sort is a topological linear ordering of the vertices
	 * in a graph, such that for every Node uv: u comes before v. This is only 
	 * possible if the graph has no directed cycles.
	 * @param Nodes All the Nodes to sort
	 * @param sorted The sorted elements
	 * @param count How many Nodes we have used
	 * @return true if a topological sort is possible
	 */
	public boolean topologicalSort(Node[] Nodes, Node[] sorted, int count){
		
		ArrayList<Node> startingNodes = new ArrayList<Node>();
		
		// For each Node we have
		for(Node Node : Nodes){
			
			// Skip if we have incoming Nodes
			if(Node.incoming.size()>0)continue;
			
			// This is a starting Node
			startingNodes.add(Node);
		}
		
		// If we have no starting Nodes, return false
		if(startingNodes.size()==0)return false;
		
		// While we have starting Nodes
		while(startingNodes.size()>0){
			
			// Remove a node
			Node node = startingNodes.remove(0);
			
			// Add to the tail of the sorted list
			sorted[count++] = node;
			
			// For each node that this node goes to
			for(Node e2 : Node.outgoing){
				
				// Remove the edge to this node from the graph
				node.outgoing.remove(e2);		
				e2.incoming.remove(node);		
				
				// If this Node has no other incoming Nodes
				// Add it to the starting Nodes
				if(e2.incoming.size()==0)startingNodes.add(e2);
			}
		}

		// For each Node we have
		for(Node Node : Nodes){
			
			// If we have any edges
			// REturn false, the graph has a cycle
			if(node.incoming.size()>0)return false;
			if(node.outgoing.size()>0)return false;
		}
		
		return true;
		
	}
	
}

private class Node {
	
	// Nodes that come to us
	public ArrayList<Node> incoming;
	
	// Nodes that we go to
	public ArrayList<Node> outgoing;

}