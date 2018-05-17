package application;

public class Main2 {
	public static void main(String[] args) {
		/*
		 * test:  13 vehicles
		 */

		BoardGenerator n = new BoardGenerator(14, 40);
		
		
		Board bTemp = n.generate();
		

		bTemp.print_board();
		
	}
}
