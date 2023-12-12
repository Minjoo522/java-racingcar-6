package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Result {
    private static final String MOVEMENT_SYMBOL = "-";

    public Result() {
        // 인스턴스 생성 방지용
    }

    public static List<String> getOneRound(Cars cars) {
        List<String> names = cars.getNames();
        List<Integer> positions = cars.getPositions();
        return IntStream.range(0, names.size())
                .mapToObj(i -> names.get(i) + " : " + MOVEMENT_SYMBOL.repeat(positions.get(i)))
                .collect(Collectors.toList());
    }
}
