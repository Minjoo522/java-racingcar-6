package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static racingcar.config.ErrorMessage.DUPLICATED_NAME;
import static racingcar.config.ErrorMessage.INVALID_CARS_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class CarsTest {
    private static MockedStatic<Randoms> randoms;

    @BeforeEach
    void init() {
        randoms = mockStatic(Randoms.class);
    }

    @AfterEach
    void close() {
        randoms.close();
    }

    @DisplayName("[Exception] 차량 목록이 2개 미만이면 예외가 발생한다.")
    @Test
    void createCarsByLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(List.of(new Car("car1"))))
                .withMessage(INVALID_CARS_SIZE.getMessage());
    }

    @DisplayName("[Exception] 중복된 차량 이름을 입력하면 예외가 발생한다.")
    @Test
    void createCarsByDuplicatedName() {
        List<Car> cars = List.of(new Car("car1"), new Car("car1"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cars(cars))
                .withMessage(DUPLICATED_NAME.getMessage());
    }

    @DisplayName("[Success] 차량 이름들을 순서대로 반환한다.")
    @Test
    void getCarNames() {
        List<Car> carsList = List.of(new Car("car1"), new Car("car2"));
        Cars cars = new Cars(carsList);
        assertThat(cars.getNames())
                .isEqualTo(List.of("car1", "car2"));
    }

    @DisplayName("[Success] 차량 position들을 순서대로 반환한다.")
    @Test
    void getCarPositions() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Cars cars = new Cars(List.of(car1, car2));

        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();

        when(Randoms.pickNumberInRange(0, 9)).thenReturn(0);
        car2.tryMove();

        assertThat(cars.getPositions())
                .isEqualTo(List.of(1, 0));
    }

    @DisplayName("[Success] position이 가장 큰 차량을 우승자로 반환한다.")
    @Test
    void findWinner() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Cars cars = new Cars(List.of(car1, car2));

        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();

        when(Randoms.pickNumberInRange(0, 9)).thenReturn(0);
        car2.tryMove();

        assertThat(cars.findWinner())
                .isEqualTo(List.of("car1"));
    }

    @DisplayName("[Success] 공동 우승자가 있는 경우 해당 이름을 모두 반환한다.")
    @Test
    void findWinnerBySamePosition() {
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Cars cars = new Cars(List.of(car1, car2));

        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();
        car2.tryMove();

        assertThat(cars.findWinner())
                .isEqualTo(List.of("car1", "car2"));
    }
}
