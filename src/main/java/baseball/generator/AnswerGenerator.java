package baseball.generator;

import baseball.domain.model.Answer;

public class AnswerGenerator {
    private static final AnswerGenerator ANSWER_GENERATOR = new AnswerGenerator();
    private final ThreeRandomNumberGenerator THREE_RANDOM_NUMBER_GENERATOR = ThreeRandomNumberGenerator.getInstance();

    private AnswerGenerator() {
    }

    public static AnswerGenerator getInstance() {
        return ANSWER_GENERATOR;
    }

    public Answer run() {
        return Answer.from(THREE_RANDOM_NUMBER_GENERATOR.run());
    }
}
