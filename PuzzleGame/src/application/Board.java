package application;

import java.util.ArrayList;

public class Board {
    private Vehicle[][] board;
    private ArrayList<Vehicle> vehicleList;
    private int movesFromSolution;

    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 0;
    private static final int CAR = 2;
    private static final int TRUCK = 3;
    private static final int TAIL = 1;
    private static final int HEAD = 0;
    private static final int Y_COORD = 1;
    private static final int X_COORD = 0;
    private static final int DIRECTION_UP = 0;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_LEFT = 3;
    // add Arraylist of vehicles

    public Board() {
	this.board = new Vehicle[6][6];
	this.vehicleList = new ArrayList<Vehicle>();
	this.movesFromSolution = 0;
    }

    public void generateBoard(/* level */) {

	/*
	 * set up data
	 */
	// set vehicles
	Vehicle c1 = new Vehicle(0, 2, 0, 0);
	Vehicle c2 = new Vehicle(0, 2, 1, 2);
	// if this is the red car
	c2.setIsRedCar();
	Vehicle c3 = new Vehicle(0, 2, 4, 4);
	Vehicle c4 = new Vehicle(1, 2, 0, 4);
	Vehicle t1 = new Vehicle(0, 3, 2, 5);
	Vehicle t2 = new Vehicle(1, 3, 0, 1);
	Vehicle t3 = new Vehicle(1, 3, 3, 1);
	Vehicle t4 = new Vehicle(1, 3, 5, 0);

	addVehicle(c1);
	addVehicle(c2);
	addVehicle(c3);
	addVehicle(c4);
	addVehicle(t1);
	addVehicle(t2);
	addVehicle(t3);
	addVehicle(t4);

    }

    public Vehicle[][] getBoard() {
	return board;
    }

    public ArrayList<Vehicle> getVehicleList() {
	return vehicleList;
    }

    // ======================= all check operation
    /*
     * 0 - horizontal, 1-vertical for if it is a valid Up operation check available
     * space in board? check valid orientation?
     */
    public boolean canMoveUp(Vehicle v) {
	// check orientation is valid for this vehicle ?
	if (v.getOrientation() != VERTICAL) {
	    return false;
	}

	/*
	 * check is there available space in board to allow vehicle move? for Up
	 * operation, we need to check head of the vehicle no valid situation: - vehicle
	 * locates at top of board - not available space in board to move in
	 */
	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];

	if (headY == 0 || board[headX][headY - 1] != null) {
	    return false;
	}

	return true;
    }

    /*
     * 0 - horizontal, 1-vertical for if it is a valid left operation check
     * available space in board? check valid orientation?
     */
    public boolean canMoveLeft(Vehicle v) {
	// check orientation is valid for this vehicle
	if (v.getOrientation() != 0) {
	    return false;
	}

	/*
	 * check is there available space in board to allow vehicle move? for Up
	 * operation, we need to check head of the vehicle no valid situation: - vehicle
	 * locates at top of board - not available space in board to move in
	 */
	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];

	if (headX == 0 || board[headX - 1][headY] != null) {
	    return false;
	}

	return true;
    }

    /*
     * 0 - horizontal, 1-vertical for if it is a valid Down operation check
     * available space in board? check valid orientation?
     */
    public boolean canMoveDown(Vehicle v) {
	// check orientation for vehicle
	if (v.getOrientation() != 1) {
	    return false;
	}
	/*
	 * check is there available space in board to allow vehicle move? for Up
	 * operation, we need to check head of the vehicle no valid situation: - vehicle
	 * locates at top of board - not available space in board to move in
	 */
	int[][] currAddress = v.getAddress();
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	if (tailY == 5 || board[tailX][tailY + 1] != null) {
	    return false;
	}
	return true;

    }

    /*
     * 0 - horizontal, 1-vertical for if it is a valid Right operation check
     * available space in board? check valid orientation?
     */
    public boolean canMoveRight(Vehicle v) {
	if (v.getOrientation() != 0) {
	    return false;
	}
	/*
	 * check is there available space in board to allow vehicle move? for Up
	 * operation, we need to check head of the vehicle no valid situation: - vehicle
	 * locates at top of board - not available space in board to move in
	 */
	int[][] currAddress = v.getAddress();
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	if (tailX == 5 || board[tailX + 1][tailY] != null) {
	    return false;
	}
	return true;

    }

    // add more check..

    // =============================== all move operation
    /*
     * move vehicle one step up, update board, update vehicle in collection of
     * vehicles
     * 
     * @pre: must call canMoveUp() first.
     *
     */
    public boolean moveUp(Vehicle v) {
	if (!canMoveUp(v)) {
	    return false;
	}

	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	// update board
	board[tailX][tailY] = null;
	board[headX][headY - 1] = v;

	// update Vehicle
	currAddress[HEAD][Y_COORD]--;
	currAddress[TAIL][Y_COORD]--;

	return true;
    }

    /*
     * move vehicle one step Left, update board, update vehicle in collection of
     * vehicles
     * 
     * @pre: must call canMoveLeft() first.
     *
     */
    public boolean moveLeft(Vehicle v) {
	if (!canMoveLeft(v)) {
	    return false;
	}

	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	// update board
	board[tailX][tailY] = null;
	board[headX - 1][headY] = v;

	// update Vehicle
	currAddress[HEAD][X_COORD]--;
	currAddress[TAIL][X_COORD]--;

	return true;
    }

    /*
     * move vehicle one step Right, update board, update vehicle in collection of
     * vehicles
     * 
     * @pre: must call canMoveRight() first.
     *
     */
    public boolean moveRight(Vehicle v) {
	if (!canMoveRight(v)) {
	    return false;
	}

	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	// update board
	board[tailX + 1][tailY] = v;
	board[headX][headY] = null;

	// update Vehicle
	currAddress[HEAD][X_COORD]++;
	currAddress[TAIL][X_COORD]++;

	return true;
    }

    /*
     * move vehicle one step Down, update board, update vehicle in collection of
     * vehicles
     * 
     * @pre: must call canMoveDown() first.
     *
     */
    public boolean moveDown(Vehicle v) {
	if (!canMoveDown(v)) {
	    return false;
	}

	int[][] currAddress = v.getAddress();
	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	// update board
	board[tailX][tailY + 1] = v;
	board[headX][headY] = null;

	// update Vehicle
	currAddress[HEAD][Y_COORD]++;
	currAddress[TAIL][Y_COORD]++;

	return true;
    }

    /*
     * adds vehicle to board
     * 
     * @pre vehicle is not null, vehicle contains valid address
     */
    public boolean addVehicle(Vehicle v) {
	int[][] currAddress = v.getAddress();

	int headX = currAddress[HEAD][X_COORD];
	int headY = currAddress[HEAD][Y_COORD];
	int tailX = currAddress[TAIL][X_COORD];
	int tailY = currAddress[TAIL][Y_COORD];

	// checks if there is available space on the board
	for (int i = headX; i <= tailX; i++) {
	    for (int j = headY; j <= tailY; j++) {
		
		if (board[i][j] != null) {
		    // if vehicle address already taken, return false
		    return false;
		}
	    }
	}

	// set the cells in board point to the vehicle
	for (int i = headX; i <= tailX; i++) {
	    for (int j = headY; j <= tailY; j++) {
		board[i][j] = v;
	    }
	}

	// adds vehicle to list of vehicles in board
	vehicleList.add(v);

	return true;
    }

    public Vehicle getVehicle(int x, int y) {
	return board[x][y];
    }

    /*
     * input info: which vehicle, what movement user take movement operation: grab
     * input information and send them to relative methods.
     * 
     * @param: vehicle, movement
     * 
     * @return: return true success, otherwise not valid operation
     */
    public boolean movementOp(Vehicle v, String move) {
	switch (move) {
	case "UP":
	    // check if it is a valid operation
	    if (!canMoveUp(v)) {
		return false;
	    }

	    moveUp(v);
	    break;
	case "DOWN":
	    if (!canMoveDown(v)) {
		return false;
	    }

	    moveDown(v);
	    break;
	case "LEFT":
	    if (!canMoveLeft(v)) {
		return false;
	    }

	    moveLeft(v);
	    break;
	case "RIGHT":
	    if (!canMoveRight(v)) {
		return false;
	    }

	    moveRight(v);
	    break;
	default:
	    System.out.println("Errno: Invalid input -> " + move);
	    return false;
	}

	return true;
    }

    public ArrayList<Move> getAllMoves() {
	ArrayList<Move> allMoves = new ArrayList<Move>();
	Move newMove;
	for (Vehicle v : this.vehicleList) {
	    if (canMoveUp(v)) {
		newMove = new Move(v, DIRECTION_UP);
		allMoves.add(newMove);
	    }
	    if (canMoveRight(v)) {
		newMove = new Move(v, DIRECTION_RIGHT);
		allMoves.add(newMove);
	    }
	    if (canMoveDown(v)) {
		newMove = new Move(v, DIRECTION_DOWN);
		allMoves.add(newMove);
	    }
	    if (canMoveLeft(v)) {
		newMove = new Move(v, DIRECTION_LEFT);
		allMoves.add(newMove);
	    }
	}

	return allMoves;
    }

    /**
     * Returns a new board state after executing a given move
     * 
     */
    public Board getNextBoard(Move move) {
	Vehicle vehicleToMove = move.getVehicle();
	
	Board nextBoard = new Board();
	for (Vehicle v : this.vehicleList) {
	    Vehicle newVehicle = new Vehicle(v.getOrientation(), v.getSize(), 0, 0);
	    newVehicle.setAddress(v.getAddress());
	    if (v == vehicleToMove) {
		vehicleToMove = newVehicle;
	    }
	    nextBoard.addVehicle(newVehicle);
	}

	int direction = move.getDirection();
	switch (direction) {
	case DIRECTION_UP:
	    nextBoard.moveUp(vehicleToMove);
	    break;
	case DIRECTION_RIGHT:
	    nextBoard.moveRight(vehicleToMove);
	    break;
	case DIRECTION_DOWN:
	    nextBoard.moveDown(vehicleToMove);
	    break;
	case DIRECTION_LEFT:
	    nextBoard.moveLeft(vehicleToMove);
	    break;
	default:
	    System.out.println("Errno: Invalid Direction");
	    break;
	}

	nextBoard.movesFromSolution = this.movesFromSolution++;

	return nextBoard;
    }
    
    public int getMovesFromSolution( ) {
	return this.movesFromSolution;
    }

    /**
     * Compares two boards to see if the state (position of all vehicles) is the
     * same
     * 
     * @pre Both boards must contain the same vehicles (not necessarily the same
     *      object)
     */
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Board)) {
	    return false;
	}

	Board b = (Board) obj;
	Vehicle[][] bBoard = b.board;

	if (bBoard == null) {
	    return false;
	} else {

	    Vehicle[][] aBoard = this.board;

	    for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 6; j++) {
		    if (aBoard[i][j] == null && bBoard[i][j] != null) {
			return false;
		    }
		    if (aBoard[i][j] != null && bBoard[i][j] == null) {
			return false;
		    }
		}
	    }
	    return true;
	}

    }

    // ===================== temp use
    public void print_board() {
	System.out.println(" 012345");
	for (int i = 0; i < 6; i++) {
	    System.out.print(i);
	    for (int j = 0; j < 6; j++) {
		if (board[j][i] == null) {
		    System.out.print("-");
		} else {
		    System.out.print("*");
		}
	    }
	    System.out.println("");
	}

	// for(Vehicle v: vehicleList) {
	// v.print();
	// }
    }

    public void tempInitBoard() {

    }
    // ======================
}
