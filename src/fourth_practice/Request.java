package fourth_practice;

public class Request {
    private final int from_floor;
    private final int to_floor;

    public Request(int from_floor, int to_floor) {
        this.from_floor = from_floor;
        this.to_floor = to_floor;
    }

    public int getFrom_floor() {
        return from_floor;
    }

    public int getTo_floor() {
        return to_floor;
    }

    @Override
    public String toString() {
        return "<"+this.from_floor + ", " + this.to_floor + ">";
    }
}
