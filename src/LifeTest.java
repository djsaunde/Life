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
		System.out.print("Generate a random game of Life? (y/n) -> "); // the user may input his / her own configuration
	    String in = sc.nextLine();
	    
	    double d = 1.0;
	    String saved = "";
	    if (in.equalsIgnoreCase("y")) {
	    	System.out.print("Enter number of rows & columns -> "); // what dimensions??
		    int n = Integer.parseInt(sc.nextLine());
		    System.out.print("Enter number of steps to run simulation -> "); // how many steps??
		    int s = Integer.parseInt(sc.nextLine());
	    	System.out.print("Enter percentage density (between 0 and 1) -> "); // get grid density
	    	d = sc.nextDouble();
	    	
	    	Life game = new Life(n, s, null, new LifeGrid(), d); // creates a game with an empty board (to be randomly generated)
		    Life.run();
	    }
	    else {
	    	System.out.print("Enter \"g\" for Gosper's Glider Gun,\n      \"o\" for Oscillators: ");
	    	saved = sc.nextLine();
	    	File f = null;
	    	Life game = null;
	    	if (saved.equalsIgnoreCase("g")) {	
	    		f = new File("bin/gospers_glider.txt"); // create board from input file of vectors (Gosper's Glider Gun)
	    		game = new Life(80, -1, f, new LifeGrid(), d);
	    	} else if (saved.equalsIgnoreCase("o")) {
	    		f = new File("bin/oscillators.txt"); // create board from input file of vectors (a group of oscillators)
	    		game = new Life(80, -1, f, new LifeGrid(), d);
	    	}
    		Life.run();
	    }
	    sc.close();
	}
}

