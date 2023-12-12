package racingcar.domain;

import static racingcar.config.GameConfig.MAXIMUM_CAR_NAME_SIZE;

import racingcar.service.RandomNumberGenerator;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        validate(name);
        this.name = name.trim();
    }

    private void validate(String name) {
        if (name.trim().isEmpty() || name.length() > MAXIMUM_CAR_NAME_SIZE.getValue()) {
            throw new IllegalArgumentException("차 이름은 한 글자 이상 5자 이하여야 합니다.");
        }
    }

    public void tryMove() {
        int randomNumber = RandomNumberGenerator.generate();
        if (randomNumber >= 4) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
