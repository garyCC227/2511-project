import java.util.*;

public class Board {
	private Vehicle[][] board;
	// add Arraylist of vehicles


	public Board() {
		this.board = new Vehicle[6][6];
        //how about the size 6 for Vechicle[5][5]?

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
    /*
     * 0 - horizontal, 1-vertical
     * for if it is a valid left operation
     * check available space in board? check valid orientation?
     */
    public boolean checkLeft(Vehicle v) {
        //check orientation is valid for this vehicle ?
        if(v.get_ori() != 0) {
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
        if(headX == 0 || board[headX-1][headY] != null) {
            return false;
        }
        
        return true;
    }
    /*
     * 0 - horizontal, 1-vertical
     * for if it is a valid Down operation
     * check available space in board? check valid orientation?
     */
    public boolean checkDown(Vehicle v){
        int tailX,tailY;
        if(v.get_ori() != 1){
            return false;
        }
        /*
         * check is there available space in board to allow vehicle move?
         * for Up operation, we need to check head of the vehicle
         * no valid situation:
         * - vehicle locates at top of board
         * - not available space in board to move in
         */
        
        int [][] tempAddress = v.get_address();
        if(v.get_size==2){
            tailX = tempAddress[1][0];
            tailY = tempAddress[1][1];
            
        }
        else if(v.get_size==3){
            tailX = tempAddress[2][0];
            tailY = tempAddress[2][1];
        }
        if(tailY == 5|| board[tailX][tailY+1] != null){
            return false;
        }
        return true;
        
    }
    /*
     * 0 - horizontal, 1-vertical
     * for if it is a valid Right operation
     * check available space in board? check valid orientation?
     */
    public boolean checkRight(Vehicle v){
        int tailX,tailY;
        if(v.get_ori() != 0){
            return false;
        }
        /*
         * check is there available space in board to allow vehicle move?
         * for Up operation, we need to check head of the vehicle
         * no valid situation:
         * - vehicle locates at top of board
         * - not available space in board to move in
         */
        int [][] tempAddress = v.get_address();
        if(v.get_size==2){
            tailX = tempAddress[1][0];
            tailY = tempAddress[1][1];
            
        }
        else if(v.get_size==3){
            tailX = tempAddress[2][0];
            tailY = tempAddress[2][1];
        }
        if(tailX == 5|| board[tailX+1][tailY] != null){
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
    /*
     * move vehicle one step Left,
     * update board, update vehicle in collection of vehicles
     * @pre: must call checkLeft() first.
     *
     */
    public void moveLeft(Vehicle v) {
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
        board[headX-1][headY] = v;
        board[tailX][tailY] = null;
        
        //update Vehicle
        if(v.get_size() == 2) {
            tempAddress[0][0] -=1; // headX move left 1
            tempAddress[1][0] -=1; // tailX move left 1
        }else if(v.get_size() == 3) {
            tempAddress[0][0] -= 1; //headX move left 1
            tempAddress[1][0] -= 1;    //bodyX move left 1
            tempAddress[2][0] -= 1; //tailX move left 1
        }
    }
    /*
     * move vehicle one step Right,
     * update board, update vehicle in collection of vehicles
     * @pre: must call checkRight() first.
     *
     */
    public void moveRight(Vehicle v) {
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
        board[headX+1][headY] = v;
        board[tailX][tailY] = null;
        
        //update Vehicle
        if(v.get_size() == 2) {
            tempAddress[0][0] +=1; // headX move right 1
            tempAddress[1][0] +=1; // tailX move right 1
        }else if(v.get_size() == 3) {
            tempAddress[0][0] += 1; //headX move right 1
            tempAddress[1][0] += 1;    //bodyX move right 1
            tempAddress[2][0] += 1; //tailX move right 1
        }
    }
    /*
     * move vehicle one step Down,
     * update board, update vehicle in collection of vehicles
     * @pre: must call checkDown() first.
     *
     */
    public void moveDown(Vehicle v) {
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
        board[headX][headY+1] = v;
        board[tailX][tailY] = null;
        
        //update Vehicle
        if(v.get_size() == 2) {
            tempAddress[0][1] +=1; // headY move down 1
            tempAddress[1][1] +=1; // tailY move down 1
        }else if(v.get_size() == 3) {
            tempAddress[0][1] += 1; //headY move down 1
            tempAddress[1][1] += 1;    //bodyY move down 1
            tempAddress[2][1] += 1; //tailY move down 1
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
             if(!checkDown(v)) { return false; }
            break;
		case "Left":
             if(!checkLeft(v)) { return false; }
            break;
		case "Right":
              if(!checkRight(v)) { return false; }
            break;
		default:
			System.out.println("Errno: Invalid input -> " + move);
			return false;
		}

		return true;
	}
}
