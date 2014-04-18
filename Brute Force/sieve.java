/*
 * Alan Wright
 * Group: Username Invalid
 * Date: 4/9/2013
 * 
 * Contains function for calculating primes using Sieve of Eratosthenes
 */

public class sieve {

	private static final int ROOF = 1000;
    
	public static void main(String []args){
         
        boolean[] isPrime = sieve();
        
		System.out.println("All primes < " + ROOF);
		System.out.println("-------------------------------");
        int cnt = 1;
        
		//Loop from the bottom down and print primes
       	for(int i = 2; i < isPrime.length; i++)
        	if(isPrime[i])
            	System.out.println("Prime #" + (cnt++) + " is " + i);
	}
	
	//Performs the sieve algorithm and fills a boolean array marking
	// all primes as true at their index. (e.g. isPrime[11] = true)
	public static boolean[] sieve() {
			
		//Initialize to true
    	boolean[] isPrime = new boolean[ROOF + 1];
    	for(int i = 2; i < isPrime.length; i++)
       		isPrime[i] = true; 
        
        //Sieve algorithm, eliminating multiples
        for(int i = 2; i <= (int)Math.floor(Math.sqrt(ROOF)); i++)
        	for(int j = i*2; j < isPrime.length; j+=i)
        		isPrime[j] = false;
        
        return isPrime;
	}
}
