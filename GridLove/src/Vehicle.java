import java.util.*;

public class Vehicle {
	private final int orientation; // 0 - horizontal, 1-vertical
	private int[][] address; // 2d array represent its head/tail x,y coordinate
	private int size;		// size of vehicle

	public Vehicle(final int orientation, int size) {
		this.orientation = orientation;
		this.size = size;
		init_address(this.size);

	}

	/*
	 *@pre: must be called after size is initialized, only use it when constructor called
	 * initialize a 2-D array for store address of vehicle
	 * e.g.  for a vertical car
	 * {5,5        -----head x == [0][0], head y == [0][1]
	 *  5,6} 	   -----tail x == [1][0], tail y == [1][1]
	 */
	public void init_address(int size) {
		this.address = new int[2][size];
	}

	public int get_size() {
		return this.size;
	}

	public final int get_ori() {
		return this.orientation;
	}

	public int[][] get_address(){
		return this.address;
	}

	public int getVehicleOrientation() {
		return this.orientation;
	}
}
