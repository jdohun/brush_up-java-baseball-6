package baseball.generator;

import baseball.domain.model.Answer;
import baseball.domain.model.SingleNumber;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleNumberGenerator {
    private static final int COUNT_OF_NUMBERS = Answer.LIMIT_SIZE_OF_ANSWER;
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
