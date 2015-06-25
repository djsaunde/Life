package src;
/**
 * @author Dan Saunders
 * Life.java
 * My implementation of Conway's Game of Life
 */

 import java.util.*;

 public class Life {
   private boolean[][] lifeBoard;
   private int n;

   public Life(int n, int steps, String[] board) { // focused constructor for the game board with dimensions NxN and user-entered board
     this.n = n;
     parseBoard(board, n);
     run(steps);
   }
   
   public Life(int n, int steps) { // general constructor for game board with dimensions NxN
	 this.n = n;
	 genRandSeed();
	 run(steps);
   }

   private void run(int steps) { // sets the game in motion based on input
	 LifeGrid grid = new LifeGrid(n);
     for (int i = 0; i < steps; i++) {
       step();
     }
     System.out.println(this);
   }

   private void parseBoard(String[] aliveCells, int n) { // parse input in "lifeBoard" class variable
	  lifeBoard = new boolean[n][n];
	  
	  for (int i = 0; i < aliveCells.length; i++) { // input parsed by setting tuple locations to true
		int a = Integer.parseInt(aliveCells[i].substring(1, 2));
        int b = Integer.parseInt(aliveCells[i].substring(3, 4));
        lifeBoard[a][b] = true;
      }
   }

   private void genRandSeed() { // create a random configuration for which the game to begin on
     Random rand = new Random();
     for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
         if (rand.nextDouble() <= 0.5) {
           lifeBoard[i][j] = true;
         }
         else {
           lifeBoard[i][j] = false;
         }
       }
     }
     System.out.println(this);
   }

   private void step() { // method to move one step forward in the game
	 boolean[][] newBoard = lifeBoard.clone(); // create a new board to record the changes in the Game of Life after 1 time step.
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
           if (neighbors <= 1 || neighbors >= 4) {
             newBoard[i][j] = false;
           }
         }
         else {
           if (neighbors == 3) {
             newBoard[i][j] = true;
           }
         }
       }
     }
     lifeBoard = newBoard;
   }

   public String toString() { // returns a String representation of the game board
     String out = "";
     for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
    	 if (lifeBoard[i][j]) {
    		 out += ".";
    	 } else {
    		 out += " ";
    	 }
       }
       out += "\n";
     }
     return out;
   }
 }