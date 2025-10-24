package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("^[1-9]\\d*$");

    public String inputRacingCarName() {
        String racingCarName = Console.readLine();

        if (!validateRacingCarNames(racingCarName)) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }

        return racingCarName;
    }

    private boolean validateRacingCarNames(String racingCarNames) {
        String[] racingCarNameArray = racingCarNames.split(",");

        return Arrays.stream(racingCarNameArray)
                .map(String::trim)
                .allMatch(this::isValidName);
    }

    private boolean isValidName(String racingCarName) {
        return !racingCarName.isEmpty() && racingCarName.length() <= 5;
    }


    public int inputRaceNumber() {
        String raceNumberStr = Console.readLine();

        if (!validateRaceNumber(raceNumberStr)) {
            throw new IllegalArgumentException("값은 1 이상의 정수여야 합니다.");
        }

        return Integer.parseInt(raceNumberStr);
    }

    private boolean validateRaceNumber(String inputRaceNumber) {
        if (inputRaceNumber == null || inputRaceNumber.isEmpty()) {
            return false;
        }

        return POSITIVE_INTEGER_PATTERN.matcher(inputRaceNumber).matches();
    }
}
