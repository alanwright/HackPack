/*
 * Alan Wright
 * Group: Username Invalid
 * Date: 4/9/2013
 * 
 * Contains functions for Greatest Common Divisor and Least Common Multiple. 
 */

import java.util.*;

public class gcd_lcm {
	
	public static void main (String[] args) {
		//Print sample GCD of 48 and 180 which is 12
		System.out.println("GCD(48,180) = " + gcd(48,180));
		
		//Print sample LCM of 21 and 6 which is 42
		System.out.println("LCM(21,6) = " + lcm(21,6));
		
	}
	
	//calculates the gcd of a and b
	public static int gcd(int a, int b) {
		return b==0 ? a : gcd(b, a % b);
	}

	//calculates the lcm of a and b
	public static int lcm(int a, int b) {
		return a/gcd(a,b)*b; //note use this order of operations to prevent overflow!
	}
}