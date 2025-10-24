package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Validator 단위 테스트")
class ValidatorTest {

    @DisplayName("자동차 이름이 유효한 경우 true를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "pobi,woni", "pobi,woni,jun", " pobi , woni ", "12345"})
    void 유효한_자동차_이름(String input) {
        Validator validator = new Validator();
        boolean result = validator.racingCarNames(input);
        assertThat(result).isTrue();
    }

    @DisplayName("자동차 이름 중 5자를 초과하는 이름이 있으면 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"javaji", "pobi,javaji", "pobi,woni,javaji"})
    void 자동차_이름이_5자를_초과(String input) {
        Validator validator = new Validator();
        boolean result = validator.racingCarNames(input);
        assertThat(result).isFalse();
    }

    @DisplayName("자동차 이름이 비어있거나 공백만 있는 경우 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", ",", ",,", "pobi,,woni", "pobi, ,woni"})
    void 자동차_이름이_비어있음(String input) {
        Validator validator = new Validator();
        boolean result = validator.racingCarNames(input);
        assertThat(result).isFalse();
    }

    @DisplayName("자동차 이름 입력이 null이면 false를 반환한다")
    @Test
    void 자동차_이름이_null() {
        Validator validator = new Validator();
        boolean result = validator.racingCarNames(null);
        assertThat(result).isFalse();
    }

    @DisplayName("시도 횟수가 1 이상의 정수이면 true를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "10", "999"})
    void 유효한_시도_횟수(String input) {
        Validator validator = new Validator();
        boolean result = validator.raceNumber(input);
        assertThat(result).isTrue();
    }

    @DisplayName("시도 횟수가 0 또는 0으로 시작하면 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "01", "05"})
    void 시도_횟수가_0이거나_0으로_시작(String input) {
        Validator validator = new Validator();
        boolean result = validator.raceNumber(input);
        assertThat(result).isFalse();
    }

    @DisplayName("시도 횟수가 숫자가 아니거나 음수, 소수, 공백 포함 시 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1a2", "-1", "1.5", " 5 ", "1 2"})
    void 시도_횟수가_숫자_형식이_아님(String input) {
        Validator validator = new Validator();
        boolean result = validator.raceNumber(input);
        assertThat(result).isFalse();
    }

    @DisplayName("시도 횟수가 null 또는 빈 문자열이면 false를 반환한다")
    @ParameterizedTest
    @NullSource
    @EmptySource
    void 시도_횟수가_null이거나_빈_문자열(String input) {
        Validator validator = new Validator();
        boolean result = validator.raceNumber(input);
        assertThat(result).isFalse();
    }
}

