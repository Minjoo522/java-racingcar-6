package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {
    private RandomNumberGenerator() {
        // 인스턴스 생성 방지용
    }

    public static int generate() {
        return Randoms.pickNumberInRange(0,9);
    }
}
