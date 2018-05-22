package application;

import java.util.*;

public class Vehicle {
    private int orientation; // 0 - horizontal, 1-vertical
    private int[][] address; // 2d array represent its head/tail x,y coordinate
    private int size; // size of vehicle
    private boolean isRedCar; // is objective car or not
    private static final int TAIL = 1;
    private static final int HEAD = 0;
    private static final int Y_COORD = 1;
    private static final int X_COORD = 0;
    private static final int VERTICAL = 1;
    private static final int HORIZONTAL = 0;

    public Vehicle(int orientation, int size, int headX, int headY) {
	this.orientation = orientation;
	this.size = size;
	this.address = new int[2][2];
	set_address(headX, headY, size, orientation);
	isRedCar = false;
    }

    public int getSize() {
	return this.size;
    }

    public int getOrientation() {
	return this.orientation;
    }

    public int[][] getAddress() {
	return this.address;
    }

    public void setAddress(int[][] address) {
	// copies address
	for (int i = 0; i < 2; i++) {
	    for (int j = 0; j < 2; j++) {
		this.address[i][j] = address[i][j];
	    }
	}
    }

    // ====================temp
    public void print() {
	System.out.println("vehicles: car");
	if (orientation == 0) {
	    System.out.println("horizontal");
	} else {
	    System.out.println("vertical");
	}

	System.out.println("head coor: (" + address[0][0] + "," + address[0][1] + ")");
	System.out.println("tail corr: (" + address[1][0] + "," + address[1][1] + ")");
    }

    public void set_address(int headX, int headY, int size, int ori) {
	if (ori == HORIZONTAL) {
	    this.address[HEAD][X_COORD] = headX;
	    this.address[HEAD][Y_COORD] = headY;
	    this.address[TAIL][X_COORD] = headX + size - 1;
	    this.address[TAIL][Y_COORD] = headY;
	} else if (ori == VERTICAL) {
	    this.address[HEAD][X_COORD] = headX;
	    this.address[HEAD][Y_COORD] = headY;
	    this.address[TAIL][X_COORD] = headX;
	    this.address[TAIL][Y_COORD] = headY + size - 1;
	}
    }

    // =======
    public void setIsRedCar() {
	this.isRedCar = true;
    }

    public boolean getIsRedCar() {
	return this.isRedCar;
    }
}
