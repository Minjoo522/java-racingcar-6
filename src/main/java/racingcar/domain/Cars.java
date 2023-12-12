package racingcar.domain;

import static racingcar.config.GameConfig.MINIMUM_CARS_NUMBER;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<String> carNames) {
        validate(carNames);
        cars = createCars(carNames);
    }

    private void validate(List<String> carNames) {
        validateSize(carNames);
        validateDuplicate(carNames);
    }

    private void validateSize(List<String> carNames) {
        if (carNames.size() < MINIMUM_CARS_NUMBER.getValue()) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
    }

    private void validateDuplicate(List<String> carNames) {
        long uniqueName = carNames.stream()
                .distinct()
                .count();
        if (uniqueName != carNames.size()) {
            throw new IllegalArgumentException("중복된 이름을 입력할 수 없습니다");
        }
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
