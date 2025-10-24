package racingcar;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("^[1-9]\\d*$");


    public boolean racingCarNames(String racingCarNames) {
        if(racingCarNames == null || racingCarNames.isEmpty()) {
            return false;
        }

        String[] racingCarNameArray = racingCarNames.split(",");

        return Arrays.stream(racingCarNameArray)
                .map(String::trim)
                .allMatch(this::isValidName);
    }


    private boolean isValidName(String racingCarName) {
        return !racingCarName.isEmpty() && racingCarName.length() <= 5;
    }


    public boolean raceNumber(String inputRaceNumber) {
        if (inputRaceNumber == null || inputRaceNumber.isEmpty()) {
            return false;
        }

        return POSITIVE_INTEGER_PATTERN.matcher(inputRaceNumber).matches();
    }
}
