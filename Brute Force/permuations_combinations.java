/*
 * Alan Wright
 * 4/18/2014
 *
 * Program generates permuations and combinations. Note: recursive stack cannot overflow 10,000
 */


import java.util.*;

public class permuations_combinations {

	public static char[] word;
	
	public static void main(String[] args){
		//Permute ABC
		word = new char[3];
		word[0] = 'a';
		word[1] = 'b';
		word[2] = 'c';
		System.out.println("Printing out all permuations for abc:");
		permute(new int[word.length], new boolean[word.length], 0);
		System.out.println();

		//All combinations of length 5 from BAKER\
		word = new char[5];
		word[0] = 'b';
		word[1] = 'a';
		word[2] = 'k';
		word[3] = 'e';
		word[4] = 'r';
		System.out.println("Printing out all combinations of letters in baker:");
		combinate(new boolean[word.length], 0, word.length);
		System.out.println();
	}

	//Permutes integer indices which reference the array we want to permute
	public static void permute(int[] perm, boolean[] used, int k){
		
		// Base Case
		if(k==perm.length) {
			for(Integer i : perm)
				System.out.print(word[i]);
			System.out.println();
			return;
		}
		
		// Permute each place
		for(int i = 0; i < perm.length; i++){
			
			if(!used[i]){
				perm[k] = i;
				used[i] = true;
				permute(perm, used, k+1); //try with		
				used[i] = false;
			}
		}
	}
	
	//Combinates integer indices which reference the array we want to permute
	public static void combinate(boolean[] used, int k, int end){
		
		// Base Case
		if(k == end){
			for(int i = 0; i < used.length; i++)
				if(used[i])
					System.out.print(word[i]);
			System.out.println();
			return;
		}

		used[k] = true;
		combinate(used, k+1, end); //try with
		used[k] = false;
		combinate(used, k+1, end); //try without	
	}
}