package racingcar;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int STARTING_POSITION = 0;
    private static final int FORWARD_STEP = 1;

    private final String name;
    private int position;

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public void move() {
        this.position++;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }
}
