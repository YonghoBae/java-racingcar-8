package racingcar;

import racingcar.exception.ErrorCode;
import racingcar.exception.RacingCarException;

import java.util.Objects;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int STARTING_POSITION = 0;
    private static final int FORWARD_STEP = 1;

    private final String name;
    private int position;

    public Car(String name) {
        this.name = validateAndTrim(name);
        this.position = STARTING_POSITION;
    }

    private String validateAndTrim(String name) {
        if (Objects.isNull(name)) {
            throw new RacingCarException(ErrorCode.INVALID_CAR_NAME_NULL);
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new RacingCarException(ErrorCode.INVALID_CAR_NAME_EMPTY);
        }
        if (trimmedName.length() > MAX_NAME_LENGTH) {
            throw new RacingCarException(ErrorCode.CAR_NAME_TOO_LONG);
        }
        return trimmedName;
    }

    public void moveForward() {
        this.position += FORWARD_STEP;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isAt(int position) {
        return this.position == position;
    }
}