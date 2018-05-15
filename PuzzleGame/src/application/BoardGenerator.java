package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class BoardGenerator {
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 0;

    int difficulty;

    public BoardGenerator(int difficulty) {
	this.difficulty = difficulty;
    }

    public Board generate() {
	Board newBoard = new Board();

	populateBoard(newBoard);

	configureBoard(newBoard);

	return newBoard;
    }

    // Adds random vehicles in random positions
    public void populateBoard(Board b) {
    	
    	//Generate redCar in finishing position
    	Vehicle redCar = new Vehicle(HORIZONTAL, 2, 4, 2);
    	b.addVehicle(redCar);
    	
    	//Populate the rest of the board with cars
    }

    // Finds the farthest away position by BFS
    public Board configureBoard(Board initial) {
    	
    	LinkedList<Board> openSet = new LinkedList<Board>();
    	ArrayList<Board> closedSet = new ArrayList<Board>();
    	Board currState = null;
    	ArrayList<Move> moves;
    	Board nextState = null;
    	boolean visited = true;
    	
    	openSet.addLast(initial);
    	
    	while (openSet.size() > 0) {
    		currState = openSet.removeFirst();
    		closedSet.add(currState);
    		
    		moves = currState.getAllMoves();
    		for (Move m : moves) {
    			nextState = currState.getNextBoard(m);
    			visited = false;
    			for (Board b : closedSet) {
    				if (b.equals(nextState)) {
    					visited = true;
    					break;
    				}
    			}
    			if (!visited) {
    				openSet.addLast(nextState);
    			}
    		}
    	}
    	
    	return currState;
    	
    }

}
