package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private static final int MOVE_CONDITION_NUMBER = 4;
    private static final int RANDOM_MIN_NUMBER = 0;
    private static final int RANDOM_MAX_NUMBER = 9;
    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    public Cars(String inputCarNames){
        if(inputCarNames == null){
            throw new IllegalArgumentException("자동차 이름 입력이 null입니다.");
        }

        this.cars = Arrays.stream(inputCarNames.split(NAME_DELIMITER,-1))
                .map(Car::new)
                .collect(Collectors.toList());

        validateDuplicateNames(this.cars);
    }

    private void validateDuplicateNames(List<Car> carList){
        long uniqueNameCount = carList.stream()
                .map(Car::getName)
                .distinct()
                .count();

        if(uniqueNameCount != carList.size()){
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }


    public void runOneRound() {
        for (Car car : cars) {
            tryMoveOneCar(car);
        }
    }

    private void tryMoveOneCar(Car car) {
        int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN_NUMBER, RANDOM_MAX_NUMBER);

        if (randomNumber >= MOVE_CONDITION_NUMBER) {
            car.moveForward();
        }
    }


    public List<Car> findWinners() {
        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.isAt(maxPosition))
                .collect(Collectors.toList());
    }


    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
