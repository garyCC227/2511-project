import java.util.*;

public class Vehicle {
    private int orientation; // 0 - horizontal, 1-vertical
    private int[][] address; // 2d array represent its head/tail x,y coordinate
    private int size; // size of vehicle

    public Vehicle(int orientation, int size) {
        this.orientation = orientation;
        this.size = size;
        this.address = new int[2][2];
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

}
