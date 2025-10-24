package racingcar;

import java.util.List;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Cars cars = createCarsFromInput();
            RaceRound round = createRaceRoundFromInput();

            outputView.printResultMessageHeader();
            runRaces(cars, round);

            List<Car> winners = cars.findWinners();
            outputView.printWinners(winners);

        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private Cars createCarsFromInput() {
        String carNamesInput = inputView.inputRacingCarName();
        return new Cars(carNamesInput);
    }

    private RaceRound createRaceRoundFromInput() {
        String raceRoundInput = inputView.inputRaceRound();
        return new RaceRound(raceRoundInput);
    }

    private void runRaces(Cars cars, RaceRound round) {
        for (int i = 0; i < round.getRound(); i++) {
            cars.runOneRound();
            outputView.printRoundResult(cars.getCars());
        }
    }
}