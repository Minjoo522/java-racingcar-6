package racingcar.controller;

import java.util.stream.IntStream;
import racingcar.domain.Cars;
import racingcar.domain.Result;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class MainController {
    public void run() {
        Cars cars = new Cars(InputView.readCarNames());
        int tryTimes = InputView.readTryTimes();
        runRounds(cars, tryTimes);
    }

    private void runRounds(Cars cars, int tryTimes) {
        OutputView.printRoundResultSubject();
        IntStream.range(0, tryTimes)
                .forEach(i -> runOneRound(cars));
    }

    private void runOneRound(Cars cars) {
        cars.playOneRound();
        OutputView.printRoundResult(Result.getOneRound(cars));
    }
}
