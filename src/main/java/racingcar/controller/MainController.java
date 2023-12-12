package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.view.InputView;

public class MainController {
    public void run() {
        Cars cars = new Cars(InputView.readCarNames());
    }
}
