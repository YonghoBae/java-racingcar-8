package racingcar;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("InputView 단위 테스트")
class InputViewTest {

    private final InputStream originalSystemIn = System.in;

    static class AlwaysPassValidatorStub extends Validator {
        @Override
        public boolean racingCarNames(String racingCarName) {
            return true;
        }

        @Override
        public boolean raceNumber(String raceNumberStr) {
            return true;
        }
    }

    static class AlwaysFailValidatorStub extends Validator {
        @Override
        public boolean racingCarNames(String racingCarName) {
            return false;
        }

        @Override
        public boolean raceNumber(String raceNumberStr) {
            return false;
        }
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
        Console.close();
    }

    private void setupSystemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    @DisplayName("유효한 자동차 이름 입력을 받으면 해당 값을 반환한다")
    void 유효한_자동차_이름_입력_시_값_반환() {
        String input = "pobi,woni,jun";
        setupSystemIn(input + "\n");
        InputView inputView = new InputView(new AlwaysPassValidatorStub());

        String result = inputView.inputRacingCarName();

        assertThat(result).isEqualTo(input);
    }

    @Test
    @DisplayName("유효하지 않은 자동차 이름을 입력 받으면 예외를 던진다")
    void 유효하지_않은_자동차_이름_입력_시_예외_발생() {
        String invalidInput = "pobi,javaji";
        setupSystemIn(invalidInput + "\n");
        InputView inputView = new InputView(new AlwaysFailValidatorStub());

        assertThatThrownBy(inputView::inputRacingCarName)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
    }

    @Test
    @DisplayName("유효한 시도 횟수 입력을 받으면 해당 숫자를 반환한다")
    void 유효한_시도_횟수_입력_시_숫자_반환() {
        String input = "5";
        setupSystemIn(input + "\n");
        InputView inputView = new InputView(new AlwaysPassValidatorStub());

        int result = inputView.inputRaceNumber();

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("유효하지 않은 시도 횟수 입력을 받으면 예외를 던진다")
    void 유효하지_않은_시도_횟수_입력_시_예외_발생() {
        String invalidInput = "0";
        setupSystemIn(invalidInput + "\n");
        InputView inputView = new InputView(new AlwaysFailValidatorStub());

        assertThatThrownBy(inputView::inputRaceNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("값은 1 이상의 정수여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아닌 문자인 경우 예외를 던진다")
    void 숫자가_아닌_시도_횟수_입력_시_예외_발생() {
        String invalidInput = "abc";
        setupSystemIn(invalidInput + "\n");
        InputView inputView = new InputView(new AlwaysFailValidatorStub());

        assertThatThrownBy(inputView::inputRaceNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("값은 1 이상의 정수여야 합니다.");
    }
}

