package racingcar;

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
            throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다.");
        }
    }

    public int getRound() {
        return round;
    }
}