/**
 * @author Dan Saunders
 * Life.java
 * My implementation of Conway's Game of Life
 */

import java.util.ArrayList;
import java.util.Random;

public class Life {
   private boolean[][] lifeBoard;
   private int n;
   private LifeGrid gui;
   private ArrayList<BoardListener> listeners;

   /**
    * constructor for the Life game object, which will handle all the game's logic (and currently is printed to the console via toString()
    * 
    * 	@param n - the number of rows / columns on this instance of the game of Life grid
    * 	@param s - the number of steps this game will run for 
    * 	@param board - the adjacency list which describes the initial state of the game (if this is passed in as null, the game board will
    *   be randomly generated)
    * @throws InterruptedException 
    **/
   public Life(int n, int s, String[] board) throws InterruptedException {
     this.n = n;
     parseBoard(board, n);
     
     gui = new LifeGrid(lifeBoard, n);
     
     for (int i = 0; i < n; i++) {
    	 for (int j = 0; j < n; j++) {
    		 listeners.add(new GuiUpdater(i, j, n, gui.getPanel()));
    	 }
     }
     run(s);
   }

   /**
    * this method will set the game in motion and handle delegating logic / drawing the GUI
    * 
    * 	@param steps - number of times the game will step / draw GUI
    * @throws InterruptedException 
    **/
   private void run(int steps) throws InterruptedException {
     for (int i = 0; i < steps; i++) {
       step();
       gui.draw();
       Thread.sleep(1000);
     }
   }
   
   /**
    * either reads in a game board from user input, or calls genRandSeed() if the board is null
    * 
    * 	@param input - the adjacency matrix passed in by the user to be parsed
    * 	@param n - the number of rows / columns the game board will have
    **/
    private void parseBoard(String[] input, int n) {
    	lifeBoard = new boolean[n][n];
	  
    	if (input == null) {
    		genRandSeed();
    		return;
    	}
	  
    	for (int i = 0; i < input.length; i++) { // input parsed by setting tuple locations to true
    		int a = Integer.parseInt(input[i].substring(1, 2));
    		int b = Integer.parseInt(input[i].substring(3, 4));
    		lifeBoard[a][b] = true;
    	}
    }
   
   /**
    * generates a random starting board configuration for the game
    **/
   private void genRandSeed() {
	   Random rand = new Random();
	   		for (int i = 0; i < n; i++) {
	   			for (int j = 0; j < n; j++) {
	   				if (rand.nextDouble() < 0.5) {
	   					lifeBoard[i][j] = true;
	   				}
	   				else {
	   					lifeBoard[i][j] = false;
	   				}
	   			}
	   		}
   		}
   
   /**
    * calculates the logic for the next step in the game given the current state of the game board. handles the birth,
    * death, or maintenance of each cell on the board (considering a better algorithm... cell caching?)
    **/
   private void step() {
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
        	 listeners.get(i*n+j).boardChanged();
             newBoard[i][j] = false;
           }
         }
         else {
           if (neighbors == 3) {
        	 listeners.get(i*n+j).boardChanged();
             newBoard[i][j] = true;
           }
         }
       }
     }
     lifeBoard = newBoard;
   }
   
   /**
    * returns a simple String representation of the board (in order to print to console for early implementation)
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
