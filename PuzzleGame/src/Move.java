

public class Move {

    private Vehicle vehicle;
    private int Direction;
    private static final int DIRECTION_UP = 0;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_LEFT = 3;

    public Move(Vehicle vehicle, int Direction) {
        this.vehicle = vehicle;
        this.Direction = Direction;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public String dirToString() {
        String dir;
        if (this.Direction == DIRECTION_UP) {
            return "UP";
        } else if (this.Direction == DIRECTION_RIGHT) {
            return "RIGHT";
        } else if (this.Direction == DIRECTION_DOWN) {
            return "DOWN";
        } else {
            return "LEFT";
        }

    }

    public int getDirection() {
        return this.Direction;
    }

    public Move getReverseMove() {
        int direction = (this.Direction + 2) % 4;

        return new Move(this.vehicle, direction);
    }

}
