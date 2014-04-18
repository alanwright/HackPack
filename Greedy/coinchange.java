/*
 * Alan Wright
 * Group: Username Invalid
 * Date: 4/12/2013
 * 
 * Finds the minimum number of coins need to make change using the US coin system.
 *
 * This greedy approach only works for coin systems and values which are not a matroid 
 *  (e.g Coins = {1, 15, 25}, Sum = 30 will not work) http://en.wikipedia.org/wiki/Matroid#Independent_sets
 */

import java.util.*;


public class coinchange {
	
	public static void main (String[] args) {
		
		//Define our coinset
		int[] S = {1, 5, 10, 25};
		
		System.out.println("Making change for $1.37");
		System.out.println("It took a toal of " + makeChange(S, 137) + " coins to make $1.37");
		System.out.println();

		System.out.println("Making change for $0.04");
		System.out.println("It took a toal of " + makeChange(S, 4) + " coins to make $0.04");
		System.out.println();

		System.out.println("Making change for $0.99");
		System.out.println("It took a toal of " + makeChange(S, 99) + " coins to make $0.99");
		System.out.println();
	}

	//Uses a single loop and integer/mod to greedily make change taking groups of change at a time.
	//PRECONDITION: coins is sorted in ascending order and sum is the number of cents
	//POSTCONDITION: returns minimum number of coins needed to make change for the amount given.
	public static int makeChange(int[] coins, int sum) {

		//Geedily choose the largest coin that will fit, and take as many as possible
		int cnt = 0;
		for(int i = coins.length - 1; i >= 0 && sum != 0; --i) {
			int num = sum/coins[i];
			System.out.println("Chose " + num + " of " + coins[i] + " cent(s) coins");
			cnt+= num;
			sum%=coins[i];
		}
		return cnt;
	}
	
	//Uses a 2 loops to make change taking only one coin at a time. 
	//PRECONDITION: coins is sorted in ascending order and val is the number of cents
	//POSTCONDITION: returns minimum number of coins needed to make change for the amount given.
	public static int makeChange2(int[] coins, int val) {

		//Geedily choose the largest coin that will fit into val
		int cnt = 0;
		while(val != 0) {
			for(int i = coins.length - 1; i >=0; --i){
				if(val - coins[i] >= 0) {
					val -= coins[i];
					System.out.println("Chose " + coins[i] + " cent(s)");
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}
}