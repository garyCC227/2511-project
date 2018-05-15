package application;

import java.util.Comparator;

public class BoardStateComparator implements Comparator<Board> {
    
    
    /**
     * @pre Both board must contain the same vehicles
     * @pre Both board must be 6x6
     * 
     */
    public int compare(Board a, Board b) {
	
	Vehicle[][] aBoard = a.getBoard();
	Vehicle[][] bBoard = b.getBoard();
	
	for (int i = 0; i < 6 ; i++) {
	    for (int j = 0; j < 6 ; j++) {
		if (aBoard[i][j] != bBoard[i][j]) {
		    return 1;
		}
	    }
	}
	
	return 0;
    }
    
}
