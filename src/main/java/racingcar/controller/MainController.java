package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Result;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class MainController {
    public void run() {
        Cars cars = setCars();
        int tryTimes = InputView.readTryTimes();

        runRounds(cars, tryTimes);
        OutputView.printWinner(Result.getWinner(cars));
    }

    private Cars setCars() {
        List<String> carNames = InputView.readCarNames();
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
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
