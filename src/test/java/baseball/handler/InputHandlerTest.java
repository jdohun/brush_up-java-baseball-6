package baseball.handler;

import baseball.domain.model.SingleNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputHandlerTest {

    private InputHandler INPUT_HANDLER = InputHandler.getInstance();

    @Nested
    @DisplayName("정답 입력 유효성 테스트")
    class InputAnswerTest {

        @DisplayName("null 이 입력되면 예외를 발생시킨다.")
        @Test
        void inputNullTest() {
            assertThatThrownBy(() -> INPUT_HANDLER.inputToSourceForAnswer(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("null 을 입력할 수 없습니다.");
        }

        @DisplayName("정답 입력 형식에 맞지 않으면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {" 213", "1234", "", " ", "asd"})
        void inputDoesNotFitFormatTest(String input) {
            assertThatThrownBy(() -> INPUT_HANDLER.inputToSourceForAnswer(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("정답 입력 형식에 맞지 않습니다.");
        }

        @DisplayName("형식에 맞는 입력을 받으면 단일 숫자 객체 리스트를 반환한다.")
        @ParameterizedTest
        @CsvSource({"213, 2,1,3", "123, 1,2,3"})
        void inputDoesNotFitFormatTest(String inputAnswer, int expectedSingleNumber1, int expectedSingleNumber2, int expectedSingleNumber3) {
            // Arrange
            List<SingleNumber> sample = Arrays.asList(
                    SingleNumber.from(expectedSingleNumber1),
                    SingleNumber.from(expectedSingleNumber2),
                    SingleNumber.from(expectedSingleNumber3)
            );

            // Act
            List<SingleNumber> inputNumbers = INPUT_HANDLER.inputToSourceForAnswer(inputAnswer);

            // Assert
            assertThat(sample).isEqualTo(inputNumbers);
        }
    }

    @Nested
    @DisplayName("재시도 입력 유효성 테스트")
    class InputRetryTest {

        @DisplayName("null 이 입력되면 예외를 발생시킨다.")
        @Test
        void inputNullTest() {
            assertThatThrownBy(() -> INPUT_HANDLER.inputToRetry(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("null 을 입력할 수 없습니다.");
        }

        @DisplayName("재시도 입력 형식에 맞지 않으면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"12", " 1", "2 ", "", " ", "asd"})
        void inputDoesNotFitFormatTest(String input) {
            assertThatThrownBy(() -> INPUT_HANDLER.inputToRetry(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("재시도 입력 형식에 맞지 않습니다.");
        }

        @DisplayName("1을 입력 받으면 true 2를 입력 받으면 false 를 반환한다.")
        @ParameterizedTest
        @CsvSource({"1, true", "2, false"})
        void inputDoesNotFitFormatTest(String input, boolean expected) {
            // Act
            boolean result = INPUT_HANDLER.inputToRetry(input);

            // Assert
            assertThat(result).isEqualTo(expected);
        }
    }
}