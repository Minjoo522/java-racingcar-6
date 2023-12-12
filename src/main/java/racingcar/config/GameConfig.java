package racingcar.config;

public enum GameConfig {
    MINIMUM_CARS_NUMBER(2),
    MAXIMUM_CAR_NAME_SIZE(5);

    private final int value;

    GameConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
