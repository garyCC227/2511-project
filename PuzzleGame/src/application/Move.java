package application;

public class Move {

    Vehicle vehicle;
    int Direction;

    public Move(Vehicle vehicle, int Direction) {
	this.vehicle = vehicle;
	this.Direction = Direction;
    }
    
    public Vehicle getVehicle() {
	return this.vehicle;
    }
    
    public int getDirection() {
	return this.Direction;
    }

}
