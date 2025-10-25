package racingcar.exception;

public enum ErrorCode {
    INVALID_CAR_NAME_NULL("자동차 이름은 null일 수 없습니다."),
    INVALID_CAR_NAME_EMPTY("자동차 이름은 공백이거나 비어있을 수 없습니다."),
    CAR_NAME_TOO_LONG("자동차 이름은 5자를 초과할 수 없습니다."),

    NULL_CAR_NAMES_INPUT("자동차 이름 입력이 null입니다."),
    DUPLICATE_CAR_NAME("자동차 이름은 중복될 수 없습니다."),

    INVALID_RACE_ROUND("시도 횟수는 1 이상의 정수여야 합니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
