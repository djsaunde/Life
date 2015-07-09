/**
 * @author Dan Saunders
 * LifeTest.java
 * Stub class to run the Game of Life / implement JUnit tests
 */

import java.util.Scanner;

public class LifeTest {
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
	    System.out.print("Enter number of rows & columns: "); // what dimensions??
	    int n = Integer.parseInt(sc.nextLine());
	    System.out.print("Enter number of steps to run simulation: "); // how many steps??
	    int steps = Integer.parseInt(sc.nextLine());
	    System.out.print("Generate a random game of Life? (y/n) -> "); // the user may input his / her own configuration
	    String in = sc.nextLine();
	    Life game = null;
	    if (in.equals("y")) {
	      game = new Life(n, steps, null); // creates a game with an empty board (to be randomly generated)
	    }
	    else {
		  System.out.println("Enter a list of \"alive\" cells in tuple format with (rows, columns) such as (a,b) (c,d) (e,f)...");
		  String[] aliveCells = sc.nextLine().split(" "); // take list input and splits each tuple into an array
		  game = new Life(n, steps, aliveCells);
		}
	}
}