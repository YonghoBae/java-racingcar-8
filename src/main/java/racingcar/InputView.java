package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputRacingCarName(){
        return Console.readLine();
    }

    public int inputRaceNumber(){
        return Integer.parseInt(Console.readLine());
    }

}
