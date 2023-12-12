package racingcar.domain;

import static racingcar.config.GameConfig.MAXIMUM_CAR_NAME_SIZE;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.trim().isEmpty() || name.length() > MAXIMUM_CAR_NAME_SIZE.getValue()) {
            throw new IllegalArgumentException("차 이름은 한 글자 이상 5자 이하여야 합니다.");
        }
    }
}
