package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String POSITION_BAR = "-";

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printResultMessageHeader() {
        System.out.println("\n실행 결과");
    }

    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String positionDisplay = POSITION_BAR.repeat(car.getPosition());
            System.out.printf("%s : %s\n", car.getName(), positionDisplay);
        }
        System.out.println();
    }

    public void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("최종 우승자 : %s\n", winnerNames);
    }
}
