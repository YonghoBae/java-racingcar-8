package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private Validator validator;

    public InputView(Validator validator) {
        this.validator = validator;
    }

    public String inputRacingCarName() {
        String racingCarName = Console.readLine();

        if (!validator.racingCarNames(racingCarName)) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }

        return racingCarName;
    }

    public int inputRaceNumber() {
        String raceNumberStr = Console.readLine();

        if (!validator.raceNumber(raceNumberStr)) {
            throw new IllegalArgumentException("값은 1 이상의 정수여야 합니다.");
        }

        return Integer.parseInt(raceNumberStr);
    }

}
