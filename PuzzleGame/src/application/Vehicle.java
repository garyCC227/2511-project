package application;

import java.util.*;

public class Vehicle {
    private int orientation; // 0 - horizontal, 1-vertical
    private int[][] address; // 2d array represent its head/tail x,y coordinate
    private int size; // size of vehicle

    public Vehicle(int orientation, int size, int headX, int headY) {
        this.orientation = orientation;
        this.size = size;
        this.address = new int[2][2];
        set_address(headX, headY, size, orientation);
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
        System.out.println("vehicles: car" );
        if(orientation == 0) {
            System.out.println("horizontal");
        }else {
            System.out.println("vertical");
        }
        
        System.out.println("head coor: (" + address[0][0] + "," + address[0][1] + ")" );   
        System.out.println("tail corr: (" + address[1][0] + "," + address[1][1] + ")");
    }
    
    public void set_address(int headX, int headY,int size, int ori) {
    	if(ori == 0) {
    		if(size == 2) {
    			
    			address[0][0] = headX; address[0][1] = headY;
    			address[1][0] = headX+1; address[1][1] = headY;
    			
    		}else if(size == 3) {
    			
    			address[0][0] = headX; address[0][1] = headY;
    			address[1][0] = headX+2; address[1][1] = headY;
    			
    		}
    	}else if(ori == 1) {
    		if(size == 2) {
    			
    			address[0][0] = headX; address[0][1] = headY;
    			address[1][0] = headX; address[1][1] = headY+1;
    			
    		}else if(size == 3) {
    			
    			address[0][0] = headX; address[0][1] = headY;
    			address[1][0] = headX; address[1][1] = headY+2;
    			
    		}
    	}
    }
    //=======
}


