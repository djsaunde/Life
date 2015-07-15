/**
 *   @author Dan Saunders
 *   LifeTest.java
 *   experimental stub class to run the Game of Life starting from GUI
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class LifeTest {
	
	public static void main(String[] args) throws InterruptedException, InvocationTargetException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
	    System.out.print("Enter number of rows & columns -> "); // what dimensions??
	    int n = Integer.parseInt(sc.nextLine());
	    System.out.print("Enter number of steps to run simulation -> "); // how many steps??
	    int s = Integer.parseInt(sc.nextLine());
	    System.out.print("Generate a random game of Life? (y/n) -> "); // the user may input his / her own configuration
	    String in = sc.nextLine();
	    double d = 1.0;
	    String saved = "";
	    if (in.equalsIgnoreCase("y")) {
	    	System.out.print("Enter percentage density (between 0 and 1) -> "); // get grid density
	    	d = sc.nextDouble();
	    }
	    else {
	    	System.out.print("Enter \"g\" for Gosper's Glider Gun: ");
	    	saved = sc.nextLine();
	    }
	    sc.close();
		LifeGrid gui = new LifeGrid();
	    if (in.equalsIgnoreCase("y")) {
	      Life game = new Life(n, s, null, gui, d); // creates a game with an empty board (to be randomly generated)
	      Life.run();
	    }
	    else {
	    	if (saved.equalsIgnoreCase("g")) {	
	    		File f = new File("src/gospers_glider.txt"); // create board from input file of vectors (currently Gosper's Glider Gun)
	    		Life game = new Life(n, s, f, gui, d);
	    		Life.run();
	    	}
		}
	}
}

