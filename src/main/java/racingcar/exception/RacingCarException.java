package racingcar.exception;

public class RacingCarException extends IllegalArgumentException {

    private final ErrorCode errorCode;

    public RacingCarException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
