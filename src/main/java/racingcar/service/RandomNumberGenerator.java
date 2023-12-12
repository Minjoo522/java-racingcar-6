package racingcar.service;

import static racingcar.config.GameConfig.MAXIMUM_RANDOM_NUMBER;
import static racingcar.config.GameConfig.MINIMUM_RANDOM_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {
    private RandomNumberGenerator() {
        // 인스턴스 생성 방지용
    }

    public static int generate() {
        return Randoms.pickNumberInRange(MINIMUM_RANDOM_NUMBER.getValue(),MAXIMUM_RANDOM_NUMBER.getValue());
    }
}
