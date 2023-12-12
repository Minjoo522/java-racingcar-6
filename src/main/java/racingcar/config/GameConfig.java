package racingcar.config;

public enum GameConfig {
    MINIMUM_CARS_NUMBER(2),
    MAXIMUM_CAR_NAME_SIZE(5),
    MINIMUM_TRY_NUMBER(1),
    MOVE_MINIMUM_THRESHOLD(4),
    MINIMUM_RANDOM_NUMBER(0),
    MAXIMUM_RANDOM_NUMBER(9);

    private final int value;

    GameConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
