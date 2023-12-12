package racingcar.domain;

import static racingcar.config.ErrorMessage.DUPLICATED_NAME;
import static racingcar.config.ErrorMessage.INVALID_CARS_SIZE;
import static racingcar.config.GameConfig.MINIMUM_CARS_NUMBER;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validate(cars);
        this.cars = cars;
    }

    private void validate(List<Car> cars) {
        validateSize(cars);
        validateDuplicate(cars);
    }

    private void validateSize(List<Car> cars) {
        if (cars.size() < MINIMUM_CARS_NUMBER.getValue()) {
            throw new IllegalArgumentException(INVALID_CARS_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<Car> cars) {
        long uniqueName = cars.stream()
                .map(Car::getName)
                .distinct()
                .count();
        if (uniqueName != cars.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAME.getMessage());
        }
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
