package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.exception.ErrorCode;
import racingcar.exception.RacingCarException;

import java.util.List;

@DisplayName("Cars 일급 컬렉션")
class CarsTest {

    @Nested
    @DisplayName("Cars 객체 생성")
    class CreateCarsTest {
        @Test
        @DisplayName("성공: 쉼표로 구분된 이름 문자열로 Cars 객체를 생성한다.")
        void createCars_Success() {
            String carNamesInput = "pobi,woni,jun";

            Cars cars = new Cars(carNamesInput);

            assertThat(cars.getCars())
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni", "jun");
        }

        @Nested
        @DisplayName("실패: 유효하지 않은 이름 입력")
        class CreateCarsFailureTest {
            @Test
            @DisplayName("자동차 이름에 중복이 있으면 예외가 발생한다.")
            void createCars_DuplicateNames() {
                String duplicateNames = "pobi,woni,pobi";

                assertThatThrownBy(() -> new Cars(duplicateNames))
                        .isInstanceOf(RacingCarException.class)
                        .hasMessage(ErrorCode.DUPLICATE_CAR_NAME.getMessage());
            }

            @Test
            @DisplayName("자동차 이름 입력이 null이면 예외가 발생한다.")
            void createCars_NullInput() {
                String nullInput = null;

                assertThatThrownBy(() -> new Cars(nullInput))
                        .isInstanceOf(RacingCarException.class)
                        .hasMessage(ErrorCode.NULL_CAR_NAMES_INPUT.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("자동차 경주 기능 (runOneRound)")
    class RaceFunctionTest {
        private Cars cars;

        @BeforeEach
        void setUp() {
            cars = new Cars("pobi,woni");
        }

        @Test
        @DisplayName("랜덤 값이 4 이상이면 전진(moveForward)한다.")
        void runOneRound_MoveForward() {
            int moveNumber = 4;
            int stopNumber = 3;

            assertRandomNumberInRangeTest(
                () -> cars.runOneRound(),
                moveNumber, stopNumber
            );

            List<Car> carList = cars.getCars();
            assertThat(carList.get(0).getPosition()).isEqualTo(1); // pobi 전진
            assertThat(carList.get(1).getPosition()).isEqualTo(0); // woni 정지
        }

        @Test
        @DisplayName("랜덤 값이 4 미만이면 정지(stay)한다.")
        void runOneRound_Stay() {
            int stopNumber1 = 0;
            int stopNumber2 = 3;

            assertRandomNumberInRangeTest(
                () -> cars.runOneRound(),
                stopNumber1, stopNumber2
            );

            List<Car> carList = cars.getCars();
            assertThat(carList.get(0).getPosition()).isEqualTo(0);
            assertThat(carList.get(1).getPosition()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("우승자 찾기 기능 (findWinners)")
    class FindWinnersTest {
        private Cars cars;

        @BeforeEach
        void setUp() {
            cars = new Cars("pobi,woni,jun");
        }

        @Test
        @DisplayName("단독 우승자를 정확히 찾는다.")
        void findSingleWinner() {
            cars.getCars().get(0).moveForward();
            cars.getCars().get(0).moveForward();
            cars.getCars().get(1).moveForward();
            

            List<Car> winners = cars.findWinners();
            
            assertThat(winners)
                    .extracting(Car::getName)
                    .containsExactly("pobi");
        }

        @Test
        @DisplayName("공동 우승자를 정확히 찾는다.")
        void findMultipleWinners() {
            cars.getCars().get(0).moveForward();
            cars.getCars().get(2).moveForward();
            

            List<Car> winners = cars.findWinners();

            assertThat(winners)
                    .extracting(Car::getName)
                    .containsExactlyInAnyOrder("pobi", "jun");
        }

        @Test
        @DisplayName("모든 자동차가 움직이지 않았을 때 (위치 0) 모두가 우승자이다.")
        void findAllWinnersWhenNoMove() {


            List<Car> winners = cars.findWinners();

            assertThat(winners)
                    .extracting(Car::getName)
                    .containsExactlyInAnyOrder("pobi", "woni", "jun");
        }
    }
}