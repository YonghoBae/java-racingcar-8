package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

public class InputView {
    private static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("^[1-9]\\d*$");

    public String inputRacingCarName(){
        return Console.readLine();
    }

    public int inputRaceNumber(){
        String raceNumberStr = Console.readLine();

        if(!validateRaceNumber(raceNumberStr)){
            throw new IllegalArgumentException("값은 1 이상의 정수여야 합니다.");
        }

        return Integer.parseInt(raceNumberStr);
    }

    private boolean validateRaceNumber(String inputRaceNumber){
        if(inputRaceNumber == null || inputRaceNumber.isEmpty()){
            return false;
        }

        return POSITIVE_INTEGER_PATTERN.matcher(inputRaceNumber).matches();
    }
}
