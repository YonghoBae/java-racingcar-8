package racingcar;

import java.util.Objects;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int STARTING_POSITION = 0;
    private static final int FORWARD_STEP = 1;

    private final String name;
    private int position;

    public Car(String name) {
        String trimmedName = validateAndTrim(name);
        this.name = trimmedName;
        this.position = STARTING_POSITION;
    }

    private String validateAndTrim(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("자동차 이름은 null일 수 없습니다.");
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 공백이거나 비어있을 수 없습니다.");
        }
        if (trimmedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 " + MAX_NAME_LENGTH + "자를 초과할 수 없습니다.");
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