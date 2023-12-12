package racingcar.view;

import java.util.List;

public class OutputView {
    private OutputView() {
        // 인스턴스 생성 방지용
    }

    public static void printRoundResultSubject() {
        System.out.println("실행 결과");
    }

    public static void printRoundResult(List<String> result) {
        result.forEach(System.out::println);
        System.out.println();
    }
}
