/*
 * Maximum Flow or Network Flow
 * Alan Wright
 * Date: 4/22/2014
 * 
 * Creates a flow graph and finds the corresponding maximum flow using the Edmonds-Karp
 * implementation of the Ford Fulkerson Algorithm. This is because it uses BFS to find the augmenting path. 
 * The implementation with an adjacency matrix causes bfs to be O(v^2) instead of O(v)
 *
 * Maxflow is good for finding bipartite matching, 
 *
 * MaxFlow w/ Adj Matrix        Maxflow w/o Adj Matrix
 * Runtime: O(E * V^3)          Runtime: O(E * V^2)
 * Space: O(V^2)                Space: O(V^2)
 */

import java.util.*;

public class max_flow {

    public static final int BEGINNING = -1;

    public static void main(String[] args){
        int[][] g = createGraph();

        int maxFlow = getMaxFlow(g, 0, 5);
        System.out.println("The maximum flow of the graph is: " + maxFlow);
    }

    /*
     * Creates the following flow graph:
     *          12
     *  16 (1)------>(3)   20
     *    / ^         ^  \ 
     * (0)  | 4     7 |  (5)
     *    \ |         |  /
     *  13 (2)------>(4)   4
     *          14
     */
    public static int[][] createGraph() {
        int[][] g = {
                        {0, 16, 13, 0, 0, 0},
                        {0, 0, 0, 12, 0, 0},
                        {0, 4, 0, 0, 14, 0},
                        {0, 0, 0, 0, 0, 20},
                        {0, 0, 0, 7, 0, 4},
                        {0, 0, 0, 0, 0, 0}
                    };
        return g;
    }

    //Uses the Edmonds Karp implementation of the Ford Fulkerson Algorithm where bfs
    // is used to find the augmenting path from source to sink.
    public static int getMaxFlow(int[][] graph, int source, int sink) {

        //Create a copy of the graph to alter
        int numV = graph.length;
        int[][] copyGraph = new int[numV][numV];
        for (int i = 0; i < numV; i++)
            for (int j = 0; j < numV; j++)
                 copyGraph[i][j] = graph[i][j];
        
        //Path will be used to store our maxFlow path
        int[] path = new int[numV];
        int maxFlow = 0;

        //While a path exists from source to sink
        while (modifiedBFS(copyGraph, source, sink, path)) {

            //Follow the path generated in the BFS and find the pathFlow
            int pathFlow = Integer.MAX_VALUE;
            int i = sink;
            while( i != source) {
                int p = path[i];
                pathFlow = Math.min(pathFlow, copyGraph[p][i]);
                i = p;
            }
        
            //Distribute that flow throughout the graph
            i = sink;
            while(i != source) {
                int p = path[i];
                copyGraph[p][i] -= pathFlow; //flow we're using
                copyGraph[i][p] += pathFlow; //back flow
                i = p;
            }

            String pth = sink + "";
            i = path[sink];
            while (i != BEGINNING) {
                pth = i + " -> " + pth;
                i = path[i];
            }
            System.out.println("Found path " + pth + " with a flow of " + pathFlow);

            //Store that pathFlow
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    //Determines if there is a path from source to sink, and fills that path in the path array.
    public static boolean modifiedBFS(int[][] graph, int source, int sink, int path[]) {
        
        int N = graph.length;
        boolean[] visited = new boolean[N];

        //Initialize and start at source
        visited[source] = true;
        path[source] = BEGINNING;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(source);
     
        //Bfs with queue
        while (!q.isEmpty()) {

            int curr = q.remove();

            for (int i = 0; i < N; i++) {
                if (graph[curr][i] > 0 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    path[i] = curr;
                }
            }
        }
        //Did we hit the sink?
        return visited[sink];
    }
}