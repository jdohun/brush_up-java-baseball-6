package baseball.generator;

import baseball.domain.model.Answer;
import baseball.domain.model.SingleNumber;

import java.util.List;
import java.util.stream.Stream;

public class AnswerGenerator {

    private static final int COUNT_OF_NUMBERS = Answer.LIMIT_SIZE_OF_ANSWER;
    private final SingleNumberGenerator ANSWER_NUMBER_GENERATOR = SingleNumberGenerator.getInstance();

    private AnswerGenerator() {
    }

    public static AnswerGenerator getInstance() {
        return Holder.ANSWER_GENERATOR;
    }

    public Answer run() {
        return Answer.from(createSingleNumbers());
    }

    private List<SingleNumber> createSingleNumbers() {
        return Stream.iterate(ANSWER_NUMBER_GENERATOR.run(), singleNumber
                        -> ANSWER_NUMBER_GENERATOR.run())
                .limit(COUNT_OF_NUMBERS)
                .distinct().toList();
    }

    public static class Holder {
        private static final AnswerGenerator ANSWER_GENERATOR = new AnswerGenerator();
    }
}
