package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class ResultTest {
    private static MockedStatic<Randoms> randoms;
    private static Car car1;
    private static Car car2;
    private static Cars cars;

    @BeforeEach
    void init() {
        randoms = mockStatic(Randoms.class);

        car1 = new Car("car1");
        car2 = new Car("car2");
        cars = new Cars(List.of(car1, car2));
    }

    @AfterEach
    void close() {
        randoms.close();
    }

    @DisplayName("[Success] 한 라운드의 결과를 요구하는 형식에 맞추어(이름 : -) 반환한다.")
    @Test
    void getOneRoundResult() {
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(0);
        car2.tryMove();

        assertThat(Result.getOneRound(cars))
                .isEqualTo(List.of(
                        "car1 : -",
                        "car2 : "
                ));
    }

    @DisplayName("[Success] 우승자를 쉼표로 join하여 반환한다")
    @Test
    void getWinnerResult() {
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car1.tryMove();
        when(Randoms.pickNumberInRange(0, 9)).thenReturn(4);
        car2.tryMove();

        assertThat(Result.getWinner(cars))
                .isEqualTo("car1, car2");
    }
}
