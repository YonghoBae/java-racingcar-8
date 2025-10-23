package racingcar;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class InputViewTest {

    private InputView inputView;
    private InputStream originalSystemIn;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
        originalSystemIn = System.in;
    }

    @AfterEach
    void tearDown() {
        Console.close();
        System.setIn(originalSystemIn);
    }


    private void mockSystemIn(String input) {
        String simulatedInput;

        if (input == null) {

            simulatedInput = "\n";
        } else {
            simulatedInput = input + "\n";
        }

        InputStream mockedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(mockedInputStream);
    }


    @Test
    @DisplayName("시도 횟수로 유효한 값('5')을 입력하면 숫자 5를 반환한다.")
    void inputRaceNumberSuccess() {
        String simulatedRaceNumberInput = "5";
        mockSystemIn(simulatedRaceNumberInput);

        int actualRaceNumber = inputView.inputRaceNumber();

        assertThat(actualRaceNumber).isEqualTo(5);
    }

    @Test
    @DisplayName("시도 횟수로 경계값('1')을 입력하면 숫자 1을 반환한다.")
    void inputRaceNumberBoundarySuccess() {
        String simulatedBoundaryInput = "1";
        mockSystemIn(simulatedBoundaryInput);

        int actualRaceNumber = inputView.inputRaceNumber();

        assertThat(actualRaceNumber).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "01", "-1", "abc", "1.5", " 5"})
    @NullAndEmptySource
    @DisplayName("시도 횟수로 1 미만의 값이나 숫자가 아닌 값을 입력하면 IllegalArgumentException이 발생한다.")
    void inputRaceNumberFail(String invalidRaceNumberInput) {
        mockSystemIn(invalidRaceNumberInput);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.inputRaceNumber())
                .withMessageContaining("1 이상의 정수여야 합니다.");
    }
}