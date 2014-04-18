/* 
 * Alan Wright
 * Group: Username Invalid
 * 4/15/14
 *
 * This program finds if an intersection between a line segment and circle exists.
 * It uses the following formulation:
 * Line seg: x = x1 + lambda * dx
 * Line seg: y = y1 + lambda * dy
 * 
 * Plug in for circle equation
 * (x1 + lambda * x - cx)^2 + (y1 + lamda * dy - cy)^2 = r^2
 * where (cx,cy) is the center of the circle with radius r.
 *
 * Rewrite the equation
 * (dx^2 + dy^2)lambda^2 - 2(dx (x1 - cx) + dy(y1 - cy))lambda + (x1 - cx)^2 + (y1 - cy)^2 - r^2 = 0
 * use the quadratic formula to determine if a solution exists (see function)
 */

import java.util.*;

public class circle_line {

	public static void main(String[] args) {
		
		//Clearly intersecting line segment. 
		System.out.printf("Given line segment from (%d,%d) to (%d,%d) and a circle at (%d,%d) with radius %d", -2, 0, 2, 0, 0, 0, 2);
		System.out.println();
		boolean intersect = isIntersecting(0, 0, 2, -2, 0, 2, 0);
		
		if(intersect)
			System.out.println("The line segment intersects the circle.");
		else
			System.out.println("The line segment does not intersect the circle.");
		System.out.println();

		//Tangent line, not technically intersectin
		System.out.printf("Given line segment from (%d,%d) to (%d,%d) and a circle at (%d,%d) with radius %d", 0, 0, 8, 0, 5, 2, 4);
		System.out.println();
		 intersect = isIntersecting(0, 0, 8, 0, 5, 2, 4);
		
		if(intersect)
			System.out.println("The line segment intersects the circle.");
		else
			System.out.println("The line segment does not intersect the circle.");
		System.out.println();
	}

	//Uses quadratic formula to see if the a circle and line segment are intersecting
	public static boolean isIntersecting(double circlex, double circley, double radius, double x1, double y1, double x2, double y2) {
		
		//Calculate change in x and y for the segment
		double deltax = x2 - x1;
		double deltay = y2 - y1;

		//Set up our quadratic formula
		double a = deltax * deltax + deltay * deltay;
		double b = 2 * (deltax * (x1-circlex) + deltay * (y1 - circley));
		double c = (x1 - circlex) * (x1 - circlex) + (y1 - circley) * (y1 - circley) - radius * radius;

		//Check if there is a negative in the discriminant
		double discriminant = b * b - 4 * a * c;
		if (discriminant < 0) 
			return false;

		//Try both +- in the quadratic formula
		double quad1 = (-b + Math.sqrt(discriminant))/(2 * a);
		double quad2 = (-b - Math.sqrt(discriminant))/(2 * a);

		//If the result is between 0 and 1, there is an intersection
		if (quad1 >= 0 && quad1 <= 1) 
			return true;
		else if (quad2 >= 0 && quad2 <= 1) 
			return true;
		return false;
	}
}