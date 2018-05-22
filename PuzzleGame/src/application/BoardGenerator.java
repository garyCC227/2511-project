package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.SSLException;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class BoardGenerator {

    int difficulty;
    int vAmount; // amounts of vehicles
    int steps; // minimum steps we want it to be

    private final int RULE_COL = 6;
    private final int SIZE_BOUND = 5;

    private final int RULE1_V = -1;
    private final int RULE2_V = -2;
    private final int RULE_VAMOUNT = -6;

    private final int RULE_H = -3;
    private final int RULE3_HRow = -4;
    private final int RULE4_HTruck = -5;

    public BoardGenerator(int vAmount, int steps) {
	this.vAmount = vAmount;// vehicles amount
	this.steps = steps;
	this.difficulty = vAmount * steps;
    }

    public Board generate() {
	Board newBoard = new Board();

	populateBoard(newBoard);
	System.out.println("Input");
	newBoard.print_board();
	return configureBoard(newBoard);

    }

    // @desc: Adds random vehicles in random positions
    public void populateBoard(Board b) {
	// add red car to board
	Vehicle redCar = new Vehicle(0, 2, 4, 2);
	redCar.setIsRedCar();
	b.addVehicle(redCar);

	int[] Vcounter = new int[1]; // count how many vertical vehicles we create
	Vcounter[0] = 0;

	// randomly generate all vertical vehicles
	if (!addVerticalVehicle(b, Vcounter)) {
	    System.out.println("Errno: Can't add vertical vehicles");
	    return;
	}

	// Hcounter: how many horizontal vehicles we want to create
	System.out.println("V is " + Vcounter[0]);
	int Hcounter = vAmount - Vcounter[0];
	// randomly add all horizontal vehicles
	if (!addHorizontalVehicle(Hcounter, b)) {
	    System.out.println("Errno: Can't add horizontal vehicles");
	    return;
	}

    }

    // Finds the furthest away position by BFS
    public Board configureBoard(Board initial) {
	LinkedList<Board> openSet = new LinkedList<Board>();
	ArrayList<Board> closedSet = new ArrayList<Board>();
	Board currState = null;
	ArrayList<Move> moves;
	Board nextState = null;
	boolean visited = true;
	boolean planToVisit = true;

	openSet.addLast(initial);

	while (openSet.size() > 0) {

	    currState = openSet.removeFirst();
	    closedSet.add(currState);

	    moves = currState.getAllMoves();
	    for (Move m : moves) {
		nextState = currState.getNextBoard(m);

		visited = false;
		for (Board b : closedSet) {
		    if (nextState.equals(b)) {
			visited = true;
			break;
		    }
		}

		planToVisit = false;
		for (Board b : openSet) {
			if (nextState.equals(b)) {
			    planToVisit = true;
			    break;
			}
		    }
		if (!visited && !planToVisit) {
    		    openSet.addLast(nextState);
		}
	    }

	}

	return currState;
    }

    /*  rule to make board more difficult
     *  - col 3 will always have a size 4 or 3 vehicle, chance to get 4 > get 3
     *  - col 5 will always have a size 3 or 4 vehicle, chance to get 3 > get 4
     *  - other col will have different probability for size 2, 3 ,4
     */
    public int getRandomSize(int whichCol) {
    	Random random = new Random();
    	double n;

    	while(true) {
    		n = random.nextDouble();

    		// if it is col 3
    		// chance to get size 4 = 0.7, change to get 3 = 0.3
    		if(whichCol == 3) {
    			if(n < .3) { return 3;}
    			else { return 4;}
    		}
    		else if(whichCol == 5) {
    		//if col 5
    			if(n < .4) { return 4;}
    			else { return 3;}
    		}
    		else {
    		// other col
    		// to get size 2 = 0.7, size 3 = 0.1 , size 4 = 0.05
    			if(n < .05) { return 4;}
    			else if(n < 0.15) { return 3;}
    			else {return 2;}
    		}
    	}
    }

    /*
     * @desc: randomly number generation. but it will follow some rule.
     *  SIZE_BOUND: sum of vertical vehicles in this column. select from 2,3,4
     *  rule for vertical vehicles generation:
     *  - RULE1_V: row select for size is 2. from 0,3,4
     *  - RULE2_V : row select for size is 4 : from 3,4
     *  rule for horizontal vehicles generation:
     *  - RULE_H: select car or truck.
     *
     * random selection with distributed probability
     */
    public int getRandomNumber(final int rule) {
    	double n;
    	Random random = new Random();

    	while(true) {
    		n = random.nextDouble();

    		//randomly pick how many col will contain vertical vehicles
    		//col which contain verticall vehicle always >= 4(col amount)
    		//chance to get 6 = 0.6, 5 = 0.3, 4=0.1
    		if(rule == RULE_VAMOUNT ){
    			if(n < .1) { return 4; }
    			else if(n < .4) { return 5;}
    			else { return 6;}
    		}

    		//pick col to store vertical vehicles, return 0-5
    		//chance for occur in col 3, 5 more
    		//equal probability
    		if(rule == RULE_COL) {
    			int v = random.nextInt(6);
    			return v;
    		}

    		//randomly select row from 0,3,4 for Rule 1 vertical
    		// make their probability equal
    		if(rule == RULE1_V ) {
    			if(n < 1/5) { return 4;}
    			else if(n < 3/5) { return 0;}
    			else {return 3;}
    		}

    		//randomly select row from 3,4 for Rule 2 vertical
    		//make their probability equal
    		if(rule == RULE2_V ){
    			if(n < .5) { return 3;}
    			else{ return 4; }
    		}

    		//randomly select car or truck for horizontal vehicles. 2 or 3
    		//chance to get a truck will be 0.3, car will be 0.7 for horizontal
    		if(rule == RULE_H){
    			if(n < .3) { return 3; }
    			else { return 2; }
    		}

    		break;
    	}
    	//-1 mean unsuccessful
    	return -1;
    }

    /*
     * @decs: check if this row is not able to store a car , this means it is a bad
     * row
     */
    public boolean checkGoodRow(Vehicle[][] boardData, int whichRow) {
	for (int i = 0; i < 5; i++) {
	    if (boardData[i][whichRow] == null) {
		if (boardData[i + 1][whichRow] == null) {
		    return true;
		}
	    }
	}

	return false;
    }

    /*
     * @desc: select a good row if no more good row, return -1
     */
    public int SmarterRandomRowSelecter(HashSet<Integer> rowSet) {
	int n;
	Random random = new Random();

	while (!rowSet.isEmpty()) {
	    n = random.nextInt(6);
	    if (!rowSet.contains(n)) {
		continue;
	    }
	    return n;
	}
	return -1;
    }

    /*
     * @desc: a smarter random number generator use when we generate horizontal
     * vehicles. make board generation faster use for find valid cell which is able
     * to add a horizontal car or truck in this row
     *
     * @return: return -1 mean this is bad row that can not store this vehicle,
     * otherwise good row
     */
    public int SmarterRandomNumberGenerater(final int rule, Vehicle[][] boardData, int whichRow, int size) {
	int n, i;
	Random random = new Random();
	HashSet<Integer> set = new HashSet<Integer>();

	n = -1;
	// to find valid cell in this row
	i = 0;
	while (i < 5) {
	    // for a car, if this cell and its next cell are both null, this cell is a valid
	    // cell
	    if (size == 2 && i < 5) {
		if (boardData[i][whichRow] == null) {
		    if (boardData[i + 1][whichRow] == null) {
			set.add(i);
			// don't waste time to check i+1 cell. for better generation, not waste space
			i++;
		    }
		}
	    } else if (size == 3 && i < 4) {
		// for truck, if this cell and its i+1 and i+2 cell are all null, this cell is a
		// valid cell
		if (boardData[i][whichRow] == null) {
		    if (boardData[i + 1][whichRow] == null && boardData[i + 2][whichRow] == null) {
			set.add(i);
			// don't waste time to check i+1 && i+2 cell. for better generation, not waste
			// space
			i += 2;
		    }
		}
	    }
	    i += 1;
	}

	/*
	 * valid cell will be added to Set. Then we randomly choose one of the valid
	 * cell to return
	 */
	while (!set.isEmpty()) {
	    n = random.nextInt(5);
	    if (!set.contains(n)) {
		continue;
	    }

	    if (rule == RULE4_HTruck && (n == 4)) {
		continue;
	    }

	    break;
	}

	return n;
    }

    //@decs: randomly generate vertical vehicles
    public boolean addVerticalVehicle(Board b, int[] Vcounter) {

    	/*
    	 * randomly decide how many columns will contain vertical vehicles(select from 1 - 6)
    	 * and randomly decide the sum of the size of vehicles(select from 2,3,4)
    	 */
    	int i;
    	Set<Integer> set = new HashSet<Integer>(); //Set:avoid repetitive elements
    	int verticalVAmount = getRandomNumber(RULE_VAMOUNT); // how many columns will contain vertical vehicles
    	int[] Vsizes = new int[verticalVAmount]; // for vertical vehicles in this column, the sum of their size
    	int[] whichCol = new int[verticalVAmount];

    	//decide the sum of the size of vertical vehicles
    	//decide which column for the vertical vehicles
    	i = 0;
    	while(i < verticalVAmount) {
    		whichCol[i] = getRandomNumber(RULE_COL);
    		if(set.contains(whichCol[i])) {
    			continue;
    		}
    		Vsizes[i] = getRandomSize(whichCol[i]);

    		set.add(whichCol[i]); // avoid repetitive column to be chose
    		i+=1;
    	}


    	/*
    	 * decide the vehicles position in this column ,
    	 * and create the vehicle and add it to board
    	 * rule need to follow: (for headY)
    	 * -- row 2 must always be empty
    	 * -- for size 2: randomly sit on row 0 or row 3 or row 4 -- rule 1 vertical
    	 * -- for size 3: must sit on row 3
    	 * -- for size 4: top vehicle must sit on row 0,  bottom vehicle sit on row 3 or row 4 --rule 2 vertical
    	 */
    	i = 0;
    	int headX,headY;
    	Vehicle Vtemp;
    	while(i < verticalVAmount) {
    		if(Vsizes[i] == 2) {
    			headY = getRandomNumber(RULE1_V);
    			headX = whichCol[i];
    			Vtemp = new Vehicle(1, 2, headX, headY);
    			if(!b.addVehicle(Vtemp)) {
    				System.out.println("Errno: create invalid vertical vehicle");
    				return false;
    			}
    			Vcounter[0]+=1;
    		}else if(Vsizes[i] == 3) {
    			headX = whichCol[i];
    			headY = 3;
    			Vtemp = new Vehicle(1, 3, headX, headY);
    			if(!b.addVehicle(Vtemp)) {
    				System.out.println("Errno: create invalid vertical vehicle");
    				return false;
    			}
    			Vcounter[0]+=1;
    		}else if(Vsizes[i] == 4) {
    			// add first car
    			headX = whichCol[i];
    			headY = 0;
    			Vtemp = new Vehicle(1, 2, headX, headY);
    			if(!b.addVehicle(Vtemp)) {
    				System.out.println("Errno: create invalid vertical vehicle");
    				return false;
    			}
    			Vcounter[0]+=1;

    			// add second car
    			headX = whichCol[i];
    			headY = getRandomNumber(RULE2_V);
    			Vtemp = new Vehicle(1, 2, headX, headY);
    			if(!b.addVehicle(Vtemp)) {
    				System.out.println("Errno: create invalid vertical vehicle");
    				return false;
    			}
    			Vcounter[0]+=1;
    		}else {
    			System.out.println("Errno: invalid size " + Vsizes[i]);
    			return false;
    		}

    		i+=1;
    	}
    	return true;
    }

    /*
     * @desc: Randomly add "Hcounter" numbers of horizontal vehicles in the board
     * and randomly decide add a car or a truck, and its position but if sometime
     * the board is not able to add more horizontal vehicles, but "Hcounter" number
     * is not achieved yet. then don't care the remaining some rule: - for a car:
     * its headX column must <= 4 - for a truck: its headX column must <= 3
     */
    public boolean addHorizontalVehicle(int Hcounter, Board b) {
	int headX, headY, i;
	int[] Hsizes = new int[Hcounter];

	// row collection which store those good row that is able to add vehicles
	HashSet<Integer> rowSet = new HashSet<Integer>();
	rowSet.add(0);
	rowSet.add(1);
	rowSet.add(3);
	rowSet.add(4);
	rowSet.add(5); // keep row 2 empty

	// decide size for all Horizontal vehicles
	i = 0;
	while (i < Hcounter) {
	    Hsizes[i] = getRandomNumber(RULE_H);
	    i += 1;
	}

	/*
	 * add horizontal vehicle -randomly decide which row, then which column -add it
	 * to board, if invalid, create again
	 */
	i = 0;
	Vehicle Vtemp;
	System.out.println("H is " + Hcounter);
	while (i < Hcounter) {
	    if (rowSet.isEmpty()) {
		break;
	    }
	    headY = SmarterRandomRowSelecter(rowSet);

	    // it's a car
	    if (Hsizes[i] == 2) {
		headX = SmarterRandomNumberGenerater(-1, b.getBoard(), headY, 2);
		// not a good row, continue and get a new row
		if (headX == -1) {
		    rowSet.remove(headY);
		    continue;
		}
	    } else if (Hsizes[i] == 3) {
		// it's a truck
		headX = SmarterRandomNumberGenerater(RULE4_HTruck, b.getBoard(), headY, 3);
		if (headX == -1) {
		    // check if this row is a really bad row
		    // which mean it can not even store a car
		    if (!checkGoodRow(b.getBoard(), headY)) {
			rowSet.remove(headY);
		    } else {
			// if it is not a good row for a truck, get next vehicle
			i += 1;
		    }
		    continue;
		}
	    } else {
		System.out.println("Errno: Invalid size");
		return false;
	    }

	    // try to add vehicle to board
	    Vtemp = new Vehicle(0, Hsizes[i], headX, headY);
	    if (!b.addVehicle(Vtemp)) {
		System.out.println("Errno: Invalid vehicle, create one more");
		continue;
	    }
	    System.out.println("how many H now :" + i);
	    i += 1;

	}
	return true;
    }

}
