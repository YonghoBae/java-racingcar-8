package racingcar;

import racingcar.exception.ErrorCode;
import racingcar.exception.RacingCarException;

import java.util.regex.Pattern;

public class RaceRound {

    private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("^[1-9]\\d*$");

    private final int round;

    public RaceRound(String inputRound) {
        validate(inputRound);
        this.round = Integer.parseInt(inputRound);
    }

    private void validate(String inputRound) {
        if (inputRound == null || !POSITIVE_INTEGER_PATTERN.matcher(inputRound).matches()) {
            throw new RacingCarException(ErrorCode.INVALID_RACE_ROUND);
        }
    }

    public int getRound() {
        return round;
    }
}