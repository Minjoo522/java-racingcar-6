package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static racingcar.config.ErrorMessage.INVALID_TRY_NUMBER;
import static racingcar.config.ErrorMessage.NOT_EMPTY;
import static racingcar.config.ErrorMessage.NOT_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    @AfterEach
    void close() {
        Console.close();
    }

    @DisplayName("[Exception] 차량 이름 목록에 공백을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    void readCarNamesByEmpty(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readCarNames)
                .withMessage(NOT_EMPTY.getMessage());
    }

    @DisplayName("[Success] 차량 이름 목록을 쉼표를 기준으로 스플릿하여 리스트로 리턴한다.")
    @Test
    void returnSplitCarNames() {
        command("car1,car2,car3");
        assertThat(InputView.readCarNames())
                .isEqualTo(List.of("car1", "car2", "car3"));
    }

    @DisplayName("[Exception] 시도 횟수에 공백을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    void readTryTimesByEmpty(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readTryTimes)
                .withMessage(NOT_EMPTY.getMessage());
    }

    @DisplayName("[Exception] 시도 횟수에 정수가 아닌 값을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"숫자가아님", "1.1"})
    void readTryTimesByNotNumber(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readTryTimes)
                .withMessage(NOT_NUMBER.getMessage());
    }

    @DisplayName("[Exception] 시도 횟수에 1 미만의 숫자를 입력하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    void readTryTimesByNotPositive(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readTryTimes)
                .withMessage(INVALID_TRY_NUMBER.getMessage());
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}
