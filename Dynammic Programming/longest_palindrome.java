/* 
 * Alan Wright
 * 4/15/14
 *
 * This program finds the longest palindrome (a string the same backwards and fowards) in a character sequence.
 * It uses a Dynammic Programming solution that builds a table for all possible sequence lengths, carrying over the max
 * from previous. A table for "AAA" would look like:
 * _____________
 * | 1 | 2 | 3 | <--answer
 * | 1 | 1 | 2 |
 * | 0 | 0 | 1 |
 * -------------
 * 
 * Runtime: O(n^2) 
 * Space Complexity: O(n^2)
 */

import java.util.*;

public class longest_palindrome {

	public static void main(String[] args) {

		//Test 1 (length should be 7: BABCBAB)
		String seq = "BBABCBCAB";
	    System.out.println("The length of the longest palindrome for the sequence " + seq + " is of length"
	     + longest_palindrome(seq.toCharArray()));
	    System.out.println();

	    //Test 2 (length should be 21)
	    seq = "AMANAPLANACANALPANAMA";
	    System.out.println("The length of the longest palindrome for the sequence " + seq + " is of length" 
	    	+ longest_palindrome(seq.toCharArray()));
	    System.out.println();		
	}

	//Uses dynammic programming to build a table for all possible sequence lengths and find the longest palindrome in sequence.
	public static int longest_palindrome(char[] seq) {

		int[][] table = new int[seq.length][seq.length];

		//Seed table and check strings of length 1
		for(int i = 0; i < seq.length; i++)
			table[i][i] = 1;

		//Build our table
		//outer loop is our length
		for (int i = 2; i <= seq.length; i++) {

			//inner loop moves the beginning point
        	for (int j = 0; j < seq.length - i + 1; j++) {

        		//our end point
            	int k = j + i - 1;

            	//Length 2 palindrome (Base case)
            	if (seq[j] == seq[k] && i == 2)
               		table[j][k] = 2;

               	//End matches our beginning, so grab from the table and increment
            	else if (seq[j] == seq[k])
               		table[j][k] = table[j+1][k-1] + 2;

               	//Otherwise take our max from previous palindromes. 
            	else
               		table[j][k] = Math.max(table[j][k-1], table[j+1][k]);
        	}
    	}
 		
 		//Return the max palindrome from the set. 	
    	return table[0][seq.length - 1];
	}
}