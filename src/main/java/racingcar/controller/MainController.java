package racingcar.controller;

import java.util.List;
import racingcar.view.InputView;

public class MainController {
    public void run() {
        List<String> carNames = InputView.readCarNames();
    }
}
