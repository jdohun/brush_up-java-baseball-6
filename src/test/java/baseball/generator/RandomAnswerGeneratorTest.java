package baseball.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThatCode;

class RandomAnswerGeneratorTest {

    private RandomAnswerGenerator RANDOM_ANSWER_GENERATOR = RandomAnswerGenerator.getInstance();

    @DisplayName("자동 정답 생성 시 예외 처리를 통과한다.")
    @RepeatedTest(10)
    void generateRandomAnswerTest() {
        assertThatCode(() -> RANDOM_ANSWER_GENERATOR.run())
                .doesNotThrowAnyException();
    }
}