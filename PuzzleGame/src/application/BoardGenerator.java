package application;

public class BoardGenerator {
    
    int difficulty;
    
    public BoardGenerator (int difficulty) {
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
	
    }
    
    
    // Finds the furthest away position by BFS
    public void configureBoard(Board b) {
	
    }
    
}
