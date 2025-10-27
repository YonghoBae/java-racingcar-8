package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import racingcar.exception.ErrorCode;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 공동_우승_기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "1");
                    assertThat(output()).contains(
                            "pobi : -",
                            "woni :",
                            "jun : -",
                            "최종 우승자 : pobi, jun"
                    );
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    @Test
    void 여러_라운드_진행_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "2");
                    assertThat(output()).contains(
                            "pobi : -",    // 1라운드
                            "woni :",
                            "pobi : --",   // 2라운드
                            "woni : -",
                            "최종 우승자 : pobi"
                    );
                },
                MOVING_FORWARD, STOP,
                MOVING_FORWARD, MOVING_FORWARD
        );
    }


    @Test
    void 이름_중복_예외_및_메시지_테스트() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("pobi,woni,pobi", "1"))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThat(output()).contains(ErrorCode.DUPLICATE_CAR_NAME.getMessage());
        });
    }

    @Test
    void 이름_공백_예외_및_메시지_테스트() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThat(output()).contains(ErrorCode.INVALID_CAR_NAME_EMPTY.getMessage());
        });
    }

    @Test
    void 시도_횟수_숫자_아님_예외_및_메시지_테스트() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("pobi,woni", "a"))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThat(output()).contains(ErrorCode.INVALID_RACE_ROUND.getMessage());
        });
    }

    @Test
    void 시도_횟수_0_이하_예외_및_메시지_테스트() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("pobi,woni", "0"))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThat(output()).contains(ErrorCode.INVALID_RACE_ROUND.getMessage());
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
