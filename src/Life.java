/**
 *   @author Dan Saunders
 *   Life.java
 *   My implementation of Conway's Game of Life
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Life {
	 private static boolean[][] lifeBoard;
	 private static int n;
	 private static int s;
	 private static double d;
	 private static LifeGrid gui;
	 private static Map<Integer, Integer> ages;

	 /**
	  *   constructor for the Life game object, which will handle all the game's logic (and currently is printed to the console via toString()
	  * 
	  *   @param n - the number of rows / columns on this instance of the game of Life grid
	  *   @param s - the number of steps this game will run for 
	  *   @param board - the adjacency list which describes the initial state of the game (if this is passed in as null, the game board will
	  *   be randomly generated)
	  *   @throws InterruptedException 
	  *   @throws InvocationTargetException 
	  *   @throws FileNotFoundException 
	  **/
	 public Life(int n, int s, File f, LifeGrid gui, double d) throws InterruptedException, InvocationTargetException, FileNotFoundException {
		 Life.n = n;
		 Life.s = s;
		 Life.d = d;
		 ages = new HashMap<Integer, Integer>();
		 parseBoard(f);
		 Life.gui = gui;
		 Thread.sleep(100);
	 }
	 
	 /**
	  * returns the age associated with the cell at "location"
	  * @param location - index of cell whose age we wish to retrieve
	  * @return int ages.get(location)
	  */
	 public static int getAge(int location) {
		 return ages.get(location);
	 }
	 
	 /**
	  * getter for lifeBoard, sole instance of the game board
	  * @return boolean[][] lifeBoard
	  */
	 public static boolean[][] getBoard() {
		 return lifeBoard;
	 }
	 
	 /**
	  * getter for n, the number of rows / columns in the game board
	  * @return int n
	  */
	 public static int getDimension() {
		 return n;
	 }

	 /**
	  *   this method will set the game in motion and handle delegating logic / drawing the GUI
	  * 
	  *   @param steps - number of times the game will step / draw GUI
	  *   @throws InterruptedException 
	  **/
	 public static void run() throws InterruptedException {
		 for (int i = 0; i != s; i++) {
			 step();
			 gui.draw(i);
			 Thread.sleep(75);
		 }
	 }
	 
	 public static void stop() throws InterruptedException {
		 
	 }
   
	 /**
	  *   either reads in a game board from user input, or calls genRandSeed() if the board is null
	  * 
	  *   @param f - the adjacency matrix passed in by the user to be parsed
	  *   @param n - the number of rows / columns the game board will have
	  *   @throws FileNotFoundException 
	  **/
	 private void parseBoard(File f) throws FileNotFoundException {
		 lifeBoard = new boolean[n][n];
	  
	     if (f == null) { // if no user-input
	    	 genRandSeed();
	    	 return;
	     }
	  
	     for (int i = 0; i < n; i++) { 
	    	 for (int j = 0; j < n; j++) {
	    		 ages.put(i*n+j, 0); // initialize ages to be zero for all entries
	    	 }
	     }
	     
	     Scanner sc = new Scanner(f);
	     String input = sc.nextLine();
	     sc.close();
	     
	     String[] cells = input.split(" ");
	     
	     for (int i = 0; i < cells.length; i++) { // input parsed by setting tuple locations to true
	         String[] cell = cells[i].split(",");
	         int a = Integer.parseInt(cell[0]);
	         int b = Integer.parseInt(cell[1]);
	         lifeBoard[a][b] = true;
	         ages.put(a*n+b, 1); // re-init ages to be one at initial config cells
	     }
	 }
   
   	 /**
	  *   generates a random starting board configuration for the game
	  **/
	 private void genRandSeed() {
		 Random rand = new Random();
	   	 	for (int i = 0; i < n; i++) {
	   	 		for (int j = 0; j < n; j++) {
	   				if (rand.nextDouble() < d) {
	   					ages.put(i*n+j, 1); // init ages at this i, j to be one generation old
	   					lifeBoard[i][j] = true; // init cell alive at this i, j
	   				}
	   				else {
	   					ages.put(i*n+j, 0); // init ages at this i, j to be zero generations old
	   					lifeBoard[i][j] = false; // init cell dead at this i, j
	   				}
	   			}
	   		}
   		}
   
     /**
      *   logic for the next step in the game given the current state of the game board. handles the birth,
      *   death, or maintenance of each cell on the board (considering a better algorithm... cell caching?)
      **/
      private static void step() {
    	  boolean[][] newBoard = new boolean[n][n]; // create a new board to record the changes in the Game of Life after 1 time step.
    	  for (int i = 0; i < n; i++) {
    		  for (int j = 0; j < n; j++) { // iterate through each cell in the 2D grid
    			  int neighbors = 0; // number of neighbors
    			  if (i+1 < n && lifeBoard[i+1][j]) { // neighbor to right
    				  neighbors++;
    			  }
    			  if (i-1 >= 0 && lifeBoard[i-1][j]) { // neighbor to left
    				  neighbors++;
    			  }
    			  if (j+1 < n && lifeBoard[i][j+1]) { // neighbor above
    				  neighbors++;
    			  }
    			  if (j-1 >= 0 && lifeBoard[i][j-1]) { // neighbor below
    				  neighbors++;
    			  }
    			  if (i+1 < n && j-1 >= 0 && lifeBoard[i+1][j-1]) { // upper-right neighbor
    				  neighbors++;
    			  }
    			  if (i-1 >= 0 && j-1 >= 0 && lifeBoard[i-1][j-1]) { // upper-left neighbor
    				  neighbors++;
    			  }
    			  if (i+1 < n && j+1 < n && lifeBoard[i+1][j+1]) { // lower-right neighbor
    				  neighbors++;
    			  }
    			  if (i-1 >= 0 && j+1 < n && lifeBoard[i-1][j+1]) { // lower-left neighbor
    				  neighbors++;
    			  }
    			  if (lifeBoard[i][j]) {
    				  if (neighbors < 2 || neighbors > 3) { // rule of the Game of Life
    					  newBoard[i][j] = false; // kill cell at this i, j
    					  ages.put(i*n+j, 0); // this cell is dead -> age = 0
    				  }
    				  else {
    					  newBoard[i][j] = true;
    					  ages.put(i*n+j, ages.get(i*n+j)+1); // this cell lives on -> age += 1
    				  }
    			  }
    			  else {
    				  if (neighbors == 3) { // rule of the Game of Life
    					  newBoard[i][j] = true; // birth cell at this i, j
    					  ages.put(i*n+j, 1); // this cell is born -> age = 1
    				  }
    				  else {
    					  newBoard[i][j] = false;
    				  }
    			  }
    		  }
    	  }
    	  lifeBoard = newBoard;
      }
   
      /**
       *   returns a simple String representation of the board (in order to print to console for early implementation)
       **/
      public String toString() {
    	  String out = "";
    	  for (int i = 0; i < n; i++) {
    		  for (int j = 0; j < n; j++) {
    			  if (lifeBoard[i][j]) {
    				  out += "x";
    			  } else {
    				  out += " ";
    			  }
    		  }
    		  out += "\n";
    	  }
    	  return out;
   	  }
 }