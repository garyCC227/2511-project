import java.util.*;

public class Board {
    private Vehicle[][] board;
    private ArrayList<Vehicle> vehicleList;
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 0;
    private static final int CAR = 2;
    private static final int TRUCK = 3;
    private static final int TAIL = 1;
    private static final int HEAD = 0;
    private static final int Y_COORD = 1;
    private static final int X_COORD = 0;
    // add Arraylist of vehicles

    public Board() {
        this.board = new Vehicle[6][6];
        this.vehicleList = new ArrayList<Vehicle>();
    }

    public Vehicle[][] getBoard() {
        return board;
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
        int headX = currAddress[HEAD][X_COORD], 
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
        int headX = currAddress[HEAD][X_COORD], 
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
        if (!v.canMoveUp()) {
            return false;
        }
        
        int[][] currAddress = v.getAddress();
        int headX = currAddress[HEAD][X_COORD], 
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
        if (!v.canMoveLeft()) {
            return false;
        }
        
        int[][] currAddress = v.getAddress();
        int headX = currAddress[HEAD][X_COORD], 
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
        if (!v.canMoveRight()) {
            return false;
        }
        
        int[][] currAddress = v.getAddress();
        int headX = currAddress[HEAD][X_COORD], 
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
    public void moveDown(Vehicle v) {
        if (!v.canMoveDown()) {
            return false;
        }
        
        int[][] currAddress = v.getAddress();
        int headX = currAddress[HEAD][X_COORD], 
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
    
    /* adds vehicle to board
     * @pre vehicle is not null, vehicle contains valid address
     */
    public boolean addVehicle(Vehicle v) {
        int headX = currAddress[HEAD][X_COORD], 
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
                
        // adds vehicle to list of vehicles in board
        vehicleList.add(v);
        
        return true;
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
        case "Up":
            // check if it is a valid operation
            if (!canMoveUp(v)) {
                return false;
            }

            // add more staff for update ...
            break;
        case "Down":
            if (!canMoveDown(v)) {
                return false;
            }
            break;
        case "Left":
            if (!canMoveLeft(v)) {
                return false;
            }
            break;
        case "Right":
            if (!canMoveRight(v)) {
                return false;
            }
            break;
        default:
            System.out.println("Errno: Invalid input -> " + move);
            return false;
        }

        return true;
    }
}
