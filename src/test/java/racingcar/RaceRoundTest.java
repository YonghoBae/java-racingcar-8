package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ErrorCode;
import racingcar.exception.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("RaceRound 클래스")
class RaceRoundTest {

    @Nested
    @DisplayName("시도 횟수 생성")
    class CreateRaceRoundTest {

        @Test
        @DisplayName("성공: 1 이상의 정수 문자열로 RaceRound를 생성한다.")
        void createRaceRound_Success() {

            String validInput = "5";

            RaceRound round = new RaceRound(validInput);

            assertThat(round.getRound()).isEqualTo(5);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "-1", "abc", "1.5", " "}) // 다양한 예외 케이스
        @NullAndEmptySource // null과 ""(빈 문자열) 케이스 추가
        @DisplayName("실패: 1 이상의 정수가 아닌 값 입력 시 예외가 발생한다.")
        void createRaceRound_InvalidInput(String invalidInput) {
            
            assertThatThrownBy(() -> new RaceRound(invalidInput))
                    .isInstanceOf(RacingCarException.class)
                    .hasMessage(ErrorCode.INVALID_RACE_ROUND.getMessage());
        }
    }
}