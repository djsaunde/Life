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
	    System.out.print("Enter number of rows & columns: "); // what dimensions??
	    int n = Integer.parseInt(sc.nextLine());
	    System.out.print("Enter number of steps to run simulation: "); // how many steps??
	    int s = Integer.parseInt(sc.nextLine());
	    System.out.print("Generate a random game of Life? (y/n) -> "); // the user may input his / her own configuration
	    String in = sc.nextLine();
	    sc.close();
		LifeGrid gui = new LifeGrid();
	    if (in.equals("y")) {
	      Life game = new Life(n, s, null, gui); // creates a game with an empty board (to be randomly generated)
	    }
	    else {
	      File f = new File("src/input.txt"); // create board from input file of vectors (currently Gosper's Glider Gun)
		  Life game = new Life(n, s, f, gui);
		}
	}
}

