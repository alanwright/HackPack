/* 
 * Alan Wright
 * 4/16/14
 *
 * This program finds the global maximum value that a knapsack with a specified weight threshold can 
 * hold of items with given weights and values. Items can only be chosen once. We generate a table with rows
 * being the number of items in our subset and cols being the size or weight of the knapsack. table[2][5] 
 * would be the maximum value that we could put in our knapsack with weight 5 from the subset of items 1 and 2.
 *
 * Runtime: O(n^2) 
 * Space Complexity: O(n^2)
 */

import java.util.*;

public class knapsack {

	public static void main(String[] args) {

		//Test 1: The maximum should be 30
		int[] weight = {1,2,1};
		int[] vals = {10,8,4};
		int size = 3;
	    System.out.println("The maximum value of 4 objects of weights {1,2,1} and values of {10,8,4} in a knapsack of size 3 is " 
	    	+ knapsack(size, weight, vals));
	    System.out.println();

	    //Test 2: The maxiumum should be 150
		int[] weight2 = {4,2,3,1,6,4};
		int[] vals2 =   {6,4,5,3,9,7};
		size = 10;
	    System.out.println("The maximum value of 4 objects of weights {4,2,3,1,6,4} and values of {6,4,5,3,9,7} in a knapsack of size 10 is " 
	    	+ knapsack(size, weight2, vals2));
	    System.out.println();
	}

	//Calculates the max value a knapsack can hold. This only allows an item to be chosen once!
	public static int knapsack(int size, int[] weights, int[] vals) {

		int numItems = weights.length;
		int[][] table = new int[numItems + 1][size + 1];

		//Look at subsets
	    for (int i = 1; i <= numItems; i++){

	    	//Look at all different weight sizes
	        for (int j = 0; j <= size; j++){

	            if (weights[i-1] <= j)
	            	//Take the max of the previous subset at this size, and adding this value
	                table[i][j] = Math.max(table[i-1][j], vals[i-1] + table[i-1][j - weights[i-1]]);

	            else
	                table[i][j] = table[i-1][j];
	        }
	    }

	    //The bottom right will hold our maximum weight
	    return table[numItems][size];
	}
}