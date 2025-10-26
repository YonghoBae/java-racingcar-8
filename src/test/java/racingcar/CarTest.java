package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ErrorCode;
import racingcar.exception.RacingCarException;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Car 클래스")
public class CarTest {

    @Nested
    @DisplayName("자동차 생성")
    class CreateCarTest {

        @Nested
        @DisplayName("성공")
        class CreateCarSuccessTest{

            @Test
            @DisplayName("유효한 이름으로 자동차를 생성한다")
            void createCar_Success(){
                String name = "pobi";

                Car car = new Car(name);

                assertThat(car.getName()).isEqualTo(name);
                assertThat(car.getPosition()).isEqualTo(0);
            }

            @Test
            @DisplayName("이름 앞뒤의 공백을 제거하고 생성한다")
            void createCar_TrimWhiteSpace(){
                String name = "pobi";
                String trimmedName = "  pobi  ";

                Car car = new Car(trimmedName);

                assertThat(car.getName()).isEqualTo(name);
                assertThat(car.getPosition()).isEqualTo(0);
            }
        }


        @Nested
        @DisplayName("실패: 유효하지 않은 이름")
        class CreateCarFailureTest{

            @Test
            @DisplayName("이름이 5자를 초과하면 예외가 발생한다.")
            void createCar_NameTooLong(){
                String longName = "pobilong";

                assertThatThrownBy(() -> new Car(longName))
                        .isInstanceOf(RacingCarException.class)
                        .hasMessage(ErrorCode.CAR_NAME_TOO_LONG.getMessage());
            }

            @ParameterizedTest
            @ValueSource(strings = {"", "   "})
            @DisplayName("이름이 비어있거나 공백이면 예외가 발생한다.")
            void createCar_NameEmptyOrBlank(String invalidName){
                assertThatThrownBy(() -> new Car(invalidName))
                        .isInstanceOf(RacingCarException.class)
                        .hasMessage(ErrorCode.INVALID_CAR_NAME_EMPTY.getMessage());
            }

            @Test
            @DisplayName("이름이 null이면 예외가 발생한다.")
            void createCar_NameNull() {
                String nullName = null;

                assertThatThrownBy(() -> new Car(nullName))
                        .isInstanceOf(RacingCarException.class)
                        .hasMessage(ErrorCode.INVALID_CAR_NAME_NULL.getMessage());
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
            assertThat(carList.get(0).getPosition()).isEqualTo(1);
            assertThat(carList.get(1).getPosition()).isEqualTo(0);
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
