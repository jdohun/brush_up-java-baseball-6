package baseball.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class ThreeRandomNumberGenerator {
    public static final ThreeRandomNumberGenerator THREE_RANDOM_NUMBER_GENERATOR = new ThreeRandomNumberGenerator();
    public static final int COUNT_OF_NUMBERS = 3;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;

    private ThreeRandomNumberGenerator() {
    }

    public static ThreeRandomNumberGenerator getInstance() {
        return THREE_RANDOM_NUMBER_GENERATOR;
    }

    public static List<Integer> run() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < COUNT_OF_NUMBERS) {
            int randomNumber = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }
}
