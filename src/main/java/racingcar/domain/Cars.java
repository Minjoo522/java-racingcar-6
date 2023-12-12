package racingcar.domain;

import static racingcar.config.ErrorMessage.DUPLICATED_NAME;
import static racingcar.config.ErrorMessage.INVALID_CARS_SIZE;
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
            throw new IllegalArgumentException(INVALID_CARS_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<String> carNames) {
        long uniqueName = carNames.stream()
                .distinct()
                .count();
        if (uniqueName != carNames.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAME.getMessage());
        }
    }

    private List<Car> createCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void playOneRound() {
        for (Car car : cars) {
            car.tryMove();
        }
    }

    public List<String> getNames() {
        return cars.stream()
                .map(Car::getName)
                .toList();
    }

    public List<Integer> getPositions() {
        return cars.stream()
                .map(Car::getPosition)
                .toList();
    }

    public List<String> findWinner() {
        final Car highestPositionCar = findHighestPositionCar();
        return findSamePositionCar(highestPositionCar);
    }

    private Car findHighestPositionCar() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow(IllegalAccessError::new);
    }

    private List<String> findSamePositionCar(Car highestPositionCar) {
        return cars.stream()
                .filter(highestPositionCar::isSamePosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
