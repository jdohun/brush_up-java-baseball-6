package baseball.generator;

import baseball.domain.model.SingleNumber;
import camp.nextstep.edu.missionutils.Randoms;

public class SingleNumberGenerator {
    private static final int START_OF_RANGE = SingleNumber.START_OF_RANGE;
    private static final int END_OF_RANGE = SingleNumber.END_OF_RANGE;

    private SingleNumberGenerator() {
    }

    public static SingleNumberGenerator getInstance() {
        return Holder.INSTANCE;
    }

    private int createSingleInteger() {
        return Randoms.pickNumberInRange(START_OF_RANGE, END_OF_RANGE);
    }

    public SingleNumber run() {
        return SingleNumber.from(createSingleInteger());
    }

    private static class Holder {
        private static final SingleNumberGenerator INSTANCE = new SingleNumberGenerator();
    }
}
