/* 
 * Alan Wright
 * 4/15/14
 *
 * This program finds the longest common subsequence between two sequences. It uses a Dynammic Programming solution that builds
 * a table for all possible sequence lengths, carrying over the max from previous. A table for "AAA" and "ABA" would look like:
 * _____________
 * | 0 | 0 | 0 | 
 * | 0 | 1 | 1 |
 * | 0 | 1 | 2 | <--answer
 * -------------
 * 
 * Utilizes the overlapping subproblem that if str1[i] != str2[j] then the best is:
 *   max(length(str1[i-1], str2[j]) and length(str1[i], str2[j-1]))
 *
 * Runtime: O(n^2) 
 * Space Complexity: O(n^2)
 */

import java.util.*;

public class longest_subsequence {

	public static void main(String[] args) {

		//Test 1 (length should be 4: GTAB)
		String seq1 = "AGGTAB";
		String seq2 = "GXTXAYB";
	    System.out.println("The length of the longest common subsequence of " + seq1 + " and " + seq2 + " is of length" 
	    	+ longest_subsequence(seq1.toCharArray(), seq2.toCharArray()));
	    System.out.println();

	    //Test 2 (length should be 9: IAMBATMAN)
		seq1 = "IZJDADMBJKATEWMAN";
		seq2 = "JEIAMEKDBXCATOIMCXAN";
	    System.out.println("The length of the longest common subsequence of " + seq1 + " and " + seq2 + " is of length " 
	    	+ longest_subsequence(seq1.toCharArray(), seq2.toCharArray()));
	    System.out.println();
	}

	//Uses dynamic programming to build up the table for common subsequences
	public static int longest_subsequence(char[] seq1, char[] seq2) {

		int[][] table = new int[seq1.length + 1][seq2.length + 1];

		//Build the table bottom up
		for (int i = 0; i <= seq1.length; i++) {
			for (int j = 0; j <= seq2.length; j++) {

				//No common sequence
				if (i == 0 || j == 0)
					table[i][j] = 0;

				//Additional letter in common sequence
				else if (seq1[i-1] == seq2[j-1])
					table[i][j] = table[i-1][j-1] + 1;

				//Otherwise try removing one letter from each word and taking the max
				else
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
			}
		}

		//Contains our maximum sequence length
		return table[seq1.length][seq2.length];
	}
}