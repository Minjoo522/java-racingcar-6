package racingcar.config;

import static racingcar.config.GameConfig.MINIMUM_TRY_NUMBER;
import static racingcar.config.GameConfig.MINIMUM_CARS_NUMBER;
import static racingcar.config.GameConfig.MAXIMUM_CAR_NAME_SIZE;

public enum ErrorMessage {
    NOT_EMPTY("공백을 입력할 수 없습니다"),
    NOT_NUMBER("숫자를 입력해 주세요."),
    DUPLICATED_NAME("중복된 이름을 입력할 수 없습니다."),
    INVALID_TRY_NUMBER(
            String.format(
                    "시도할 횟수는 %d 이상이어야 합니다.",
                    MINIMUM_TRY_NUMBER.getValue()
            )
    ),
    INVALID_CARS_SIZE(
            String.format(
                    "자동차는 %d대 이상이어야 합니다.",
                    MINIMUM_CARS_NUMBER.getValue()
            )
    ),
    INVALID_CAR_NAME_SIZE(
            String.format(
                    "차 이름은 한 글자 이상 %d자 이하여야 합니다.",
                    MAXIMUM_CAR_NAME_SIZE.getValue()
            )
    );

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;
    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
