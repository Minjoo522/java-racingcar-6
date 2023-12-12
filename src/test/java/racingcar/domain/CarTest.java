package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static racingcar.config.ErrorMessage.INVALID_CAR_NAME_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

public class CarTest {
    private static MockedStatic<Randoms> randoms;

    @BeforeEach
    void init() {
        randoms = mockStatic(Randoms.class);
    }

    @AfterEach
    void close() {
        randoms.close();
    }

    @DisplayName("[Exception] 차량 이름이 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    void createCarByEmptyName(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Car(name))
                .withMessage(INVALID_CAR_NAME_SIZE.getMessage());
    }

    @DisplayName("[Exception] 차량 이름이 5자를 초과하면 예외가 발생한다.")
    @Test
    void createCarByOverSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Car("다섯자초과이름"))
                .withMessage(INVALID_CAR_NAME_SIZE.getMessage());
    }

    @DisplayName("[Success] 랜덤으로 생성된 숫자가 4 이상이면 position을 하나 증가시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void moveCar(int value) {
        Car car = new Car("car1");
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(value);
        car.tryMove();

        assertThat(car.getPosition())
                .isEqualTo(1);
    }

    @DisplayName("[Success] 랜덤으로 생성된 숫자가 4 미만이면 position을 증가하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void stopCar(int value) {
        Car car = new Car("car1");
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(value);
        car.tryMove();

        assertThat(car.getPosition())
                .isEqualTo(0);
    }

    @DisplayName("[Success] 두 차량이 서로 같은 position이면 true를 반환한다.")
    @Test
    void checkSamePositionBySamePosition() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();
        car2.tryMove();

        assertThat(car1.isSamePosition(car2))
                .isTrue();
    }

    @DisplayName("[Success] 두 차량이 서로 다른 position이면 false를 반환한다.")
    @Test
    void checkSamePositionByDifferentPosition() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(1);
        car2.tryMove();

        assertThat(car1.isSamePosition(car2))
                .isFalse();
    }
}
