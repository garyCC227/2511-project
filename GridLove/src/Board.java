import java.util.*;

public class Board {
	private Vehicle[][] board;
	// add Arraylist of vehicles


	public Board() {
		this.board = new Vehicle[6][6];

	}

	public Vehicle[][] get_board() {
		return board;
	}

	// ======================= all check operation
	/*
	 * 0 - horizontal, 1-vertical
	 * for if it is a valid Up operation
	 * check available space in board? check valid orientation?
	 */
	public boolean checkUp(Vehicle v) {
		//check orientation is valid for this vehicle ?
		if(v.get_ori() != 1) {
			return false;
		}

		/*
		 * check is there available space in board to allow vehicle move?
		 * for Up operation, we need to check head of the vehicle
		 * no valid situation:
		 * - vehicle locates at top of board
		 * - not available space in board to move in
		 */
		int[][] tempAddress = v.get_address();
		int headX = tempAddress[0][0];
		int headY = tempAddress[0][1];
		if(headY == 0 || board[headX][headY-1] != null) {
			return false;
		}

		return true;
	}

	//add more check..

	// =============================== all move operation
	/*
	 * move vehicle one step up,
	 * update board, update vehicle in collection of vehicles
	 * @pre: must call checkUp() first.
	 *
	 */
	public void moveUp(Vehicle v) {
		int[][] tempAddress = v.get_address();
		int headX = tempAddress[0][0], headY = tempAddress[0][1];
		int tailX, tailY;

		//vehicle is car
		if(v.get_size() == 2) {
			tailX = tempAddress[1][0];
			tailY = tempAddress[1][1];
		}else if(v.get_size() == 3) {
		//vehicle is truck
			tailX = tempAddress[2][0];
			tailY = tempAddress[2][1];
		}else {
			System.out.println("Erro: invalid size");
			return;
		}

		//update board
		board[headX][headY-1] = v;
		board[tailX][tailY] = null;

		//update Vehicle
		if(v.get_size() == 2) {
			tempAddress[0][1] -=1; // headY move up 1
			tempAddress[1][1] -=1; // tailY move up 1
		}else if(v.get_size() == 3) {
			tempAddress[0][1] -= 1; //headY move up 1
			tempAddress[1][1] -= 1;	//bodyY move up 1
			tempAddress[2][1] -= 1; //tailY move up 1
		}
	}

	public void addVehicle () {

	}

	/*
	 * input info:  which vehicle,   what movement user take
	 * movement operation: grab input information and send them to relative methods.
	 * @param:  vehicle, movement
	 * @return: return true success, otherwise not valid operation
	 */
	public boolean movementOp(Vehicle v, String move) {
		switch (move) {
		case "Up":
			//check if it is a valid operation
			if(!checkUp(v)) { return false; }

			//add more staff for update ...
			break;
		case "Down":
		case "Left":
		case "Right":
		default:
			System.out.println("Errno: Invalid input -> " + move);
			return false;
		}

		return true;
	}
}
