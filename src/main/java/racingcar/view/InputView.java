package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private InputView() {
        // 인스턴스 생성 방지용
    }

    public static List<String> readCarNames() {
        System.out.println(Message.REQUEST_CAR_NAMES.message);
        String input = Console.readLine();
        validateNotEmpty(input);
        return splitCarNames(input);
    }

    public static int readTryTimes() {
        System.out.println(Message.REQUEST_TRY_TIMES.message);
        String input = Console.readLine();
        validateNotEmpty(input);
        validatePositiveNumber(input);
        return parseToInt(input);
    }

    private static void validateNotEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }

    private static List<String> splitCarNames(String input) {
        return Arrays.stream(input.split(","))
                .collect(Collectors.toList());
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

    private static void validatePositiveNumber(String input) {
        if (parseToInt(input) < 1) {
            throw new IllegalArgumentException("시도할 횟수는 1 이상이어야 합니다.");
        }
    }

    private enum Message {
        REQUEST_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
        REQUEST_TRY_TIMES("시도할 회수는 몇회인가요?");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
