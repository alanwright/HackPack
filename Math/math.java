/* 
 * Alan Wright
 * 4/24/14
 *
 * This program has useful math functions that appear in some problems. The functions
 * include GCD, LCM, Sieve Primes, Triangle Area, Determinant, Quadratic Formula, 
 * quadratic discriminant, Fibinocci Dynamic Programming and base conversion. 
 */

import java.util.*;

public class math {

	public static void main(String[] args) throws MathException {
		
		//Print sample GCD of 48 and 180 which is 12
		System.out.println("GCD(48,180) = " + gcd(48,180));
		
		//Print sample LCM of 21 and 6 which is 42
		System.out.println("LCM(21,6) = " + lcm(21,6));
		System.out.println();

		System.out.println("732 in octal is " + convertBaseToDecimal(8, 732) + " in decimal."); //474
		System.out.println("47 in decimal is " + convertDecimalToBase(2, 47) + " in base 2"); //101111
		System.out.println();

		//print all primes < 100 
		System.out.println("Sieve: All primes < " + 100);
		System.out.println("-------------------------------");
		boolean[] isPrime = sieve(100);
        int cnt = 1;
        
		//Loop from the bottom down and print primes
       	for(int i = 2; i < isPrime.length; i++)
        	if(isPrime[i])
            	System.out.println("Prime #" + (cnt++) + " is " + i);
        System.out.println();

        //Area of triangle will be 4
        System.out.println("The area of a triangle with a base of 2 cm and a height of 4 cm is " + triangleArea(2,4) + " cm.");
        System.out.println();

        //2x2 determinant = 13
        System.out.println("The determinant of:\n|6 7|\n|5 8|\n is: " + determinant(6,7,5,8));
        System.out.println();

        //Quadratic formula yields 0.79 and -12.58
        System.out.println("Given 4x^2 + 50x - 4 = 0, the quadratic formula yields:");
        double[] quad = quadraticFormula(4,50,-4);
        System.out.println(quad[0] + " and " + quad[1]);
        System.out.println();

        //First 10 fib are 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
        System.out.println("The first 10 Fibinocci numbers are: ");
        int[] fib = fibinocciDP(10);
        for(Integer i : fib)
        	System.out.print(i + " ");
        System.out.println();
	}

	//calculates the gcd of a and b
	public static int gcd(int a, int b) {
		return b==0 ? a : gcd(b, a % b);
	}

	//calculates the lcm of a and b
	public static int lcm(int a, int b) {
		return a/gcd(a,b)*b; //note use this order of operations to prevent overflow!
	}

	//Performs the sieve algorithm and fills a boolean array marking
	// all primes as true at their index. (e.g. isPrime[11] = true)
	public static boolean[] sieve(int n) {
			
		//Initialize to true
    	boolean[] isPrime = new boolean[n + 1];
    	for(int i = 2; i < isPrime.length; i++)
       		isPrime[i] = true; 
        
        //Sieve algorithm, eliminating multiples
        for(int i = 2; i <= (int)Math.floor(Math.sqrt(n)); i++)
        	for(int j = i*2; j < isPrime.length; j+=i)
        		isPrime[j] = false;
        
        return isPrime;
	}

	//Calculates the area of a triangle given a base and height.
	public static double triangleArea(double base, double height) {
		return .5 * base * height;
	}

	//Calculates the determinant
	public static double determinant(double a, double b, double c, double d) {
		return a * d - b * c;
	}

	//Calculates the quadratic formula given a, b, c from a polynomial of the form
	// ax^2 + bx + c
	// returns both answers in a double array of size 2
	public static double[] quadraticFormula(double a, double b, double c) throws MathException {
		if(a == 0)
			throw new MathException("Can't divide by 0 in the Quadratic Formula");
		double[] ans = new double[2];
		double discr = discriminant(a, b, c);
		ans[0] = (-b + discr)/(2 * a);
		ans[1] = (-b - discr)/(2 * a);
		return ans;
	}

	//Calculates the discriminant in the quadratic formula
	// Throws 
	public static double discriminant(double a, double b, double c) throws MathException{
		double discriminant = b * b - 4 * a * c;
		if (discriminant < 0) 
			throw new MathException("Discriminant is negative in the Quadratic Formula.");
		return Math.sqrt(discriminant);
	}

	//Calculates n fibinocci sequence numbers where n >= 3. Uses Dynammic Programming approach. 
	public static int[] fibinocciDP(int n) {
		int[] fib = new int[n+1];
		fib[0] = 0;
		fib[1] = 1;
		fib[2] = 2;
		for(int i = 3; i <= n; i++)
			fib[i] = fib[i-2] + fib[i -1];
		return fib;
	}

	//Coverts any base number to decimal (based 10)
	public static int convertBaseToDecimal(int base, int num) {
		int ans = 0;
		int cnt = 0; //starting at base^0

		while(num > 0){
			ans += (num % 10) * Math.pow(base, cnt); //isolate rightmost digit and multiply by base^pos
			num /= 10; 								 //remove rightmost digit
			cnt++;
		}
		return ans;
	}

	//Coverts any decimal number to another base
	// works for bases 2 to 10
	public static int convertDecimalToBase(int base, int num) {
		int ans = 0;
		int cnt = 0; 

		while(num > 0){
			ans += (num % base) * Math.pow(10, cnt);
			num /= base;
			cnt++;
		}
		return ans;
	}
}

class MathException extends Exception {
	public MathException(String msg){ super(msg);}
}