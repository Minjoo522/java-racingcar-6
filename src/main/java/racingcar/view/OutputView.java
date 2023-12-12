package racingcar.view;

import java.util.List;

public class OutputView {
    private OutputView() {
        // 인스턴스 생성 방지용
    }

    public static void printRoundResultSubject() {
        System.out.println(Message.ROUND_RESULT_SUBJECT.message);
    }

    public static void printRoundResult(List<String> result) {
        result.forEach(System.out::println);
        System.out.println();
    }

    public static void printWinner(String result) {
        System.out.print(Message.FINAL_RESULT_SUBJECT.message);
        System.out.println(result);
    }

    private enum Message {
        ROUND_RESULT_SUBJECT("실행 결과"),
        FINAL_RESULT_SUBJECT("최종 우승자 : ");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
