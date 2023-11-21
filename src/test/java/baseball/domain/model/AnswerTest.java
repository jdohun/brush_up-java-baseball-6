package baseball.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class AnswerTest {

    @Nested
    @DisplayName("정답 객체 생성 유효성 테스트")
    class ValidationTest {

        @DisplayName("중복되는 단일 숫자 객체를 가지면 예외를 발생시킨다.")
        @Test
        void createAnswerByDuplicateSingleNumbers() {
            assertThatThrownBy(() -> Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(1)
            )))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("중복된 숫자가 존재합니다.");
        }

        @DisplayName("개수 조건 이상의 단일 숫자 객체를 가지면 예외를 발생시킨다.")
        @Test
        void createAnswerByRangeOutOfSingleNumbers() {
            assertThatThrownBy(() -> Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3),
                    SingleNumber.from(4)
            )))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("3개의 숫자를 가져야 합니다.");
        }

        @DisplayName("개수 조건을 충족하며 중복되지 않는 단일 숫자 객체를 가지면 예외를 발생시키지 않는다.")
        @Test
        void createAnswerByValidationSingleNumbers() {
            assertThatCode(() -> Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3)
            )))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("동치성 테스트")
    class EqualityTest {

        @DisplayName("의미하는 값을 통해 객체의 같고 다름을 판별할 수 있다.")
        @Test
        void testEquals() {
            Answer sample1 = Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3)
            ));
            Answer sample2 = Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3)
            ));
            Answer sample3 = Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(4)
            ));

            assertThat(sample1).isEqualTo(sample2)
                    .isNotEqualTo(sample3);
        }

        @DisplayName("의미하는 값이 같은 객체는 같은 해시코드를 가진다.")
        @Test
        void testHashCode() {
            Answer sample1 = Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3)
            ));
            Answer sample2 = Answer.from(Arrays.asList(
                    SingleNumber.from(1),
                    SingleNumber.from(2),
                    SingleNumber.from(3)
            ));

            assertThat(sample1).hasSameHashCodeAs(sample2);
        }
    }

    @Nested
    @DisplayName("숫자 비교 테스트")
    class ComparisonTest {

        @DisplayName("같은 위치의 같은 숫자 개수를 알 수 있다")
        @ParameterizedTest(name = "{0}{1}{2}과 {3}{4}{5}을 비교하여 나온 결과 : {6}")
        @CsvSource({"1,2,3, 4,5,6, 0", "1,2,3, 4,5,3, 1", "1,2,3, 1,5,3, 2", "1,2,3, 1,2,3, 3"})
        void strikeTest(int source1ForSample1, int source2ForSample1, int source3ForSample1, int source1ForSample2, int source2ForSample2, int source3ForSample2, int expected) {
            // Arrange
            Answer sample1 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample1),
                    SingleNumber.from(source2ForSample1),
                    SingleNumber.from(source3ForSample1)
            ));
            Answer sample2 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample2),
                    SingleNumber.from(source2ForSample2),
                    SingleNumber.from(source3ForSample2)
            ));

            // Act
            int actual = sample1.compareTo(sample2).countOfStrike();

            // Assert
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("다른 위치의 같은 숫자 개수를 알 수 있다")
        @ParameterizedTest(name = "{0}{1}{2}과 {3}{4}{5}을 비교하여 나온 결과 : {6}")
        @CsvSource({"1,2,3, 4,5,6, 0", "1,2,3, 3,4,5, 1", "1,2,3, 5,1,2, 2", "1,2,3, 2,3,1, 3"})
        void ballTest(int source1ForSample1, int source2ForSample1, int source3ForSample1, int source1ForSample2, int source2ForSample2, int source3ForSample2, int expected) {
            // Arrange
            Answer sample1 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample1),
                    SingleNumber.from(source2ForSample1),
                    SingleNumber.from(source3ForSample1)
            ));
            Answer sample2 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample2),
                    SingleNumber.from(source2ForSample2),
                    SingleNumber.from(source3ForSample2)
            ));

            // Act
            int actual = sample1.compareTo(sample2).countOfBall();

            // Assert
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("두 숫자의 일치 여부를 알 수 있다")
        @ParameterizedTest(name = "{0}{1}{2}과 {3}{4}{5}을 비교하여 나온 결과 : {6}")
        @CsvSource({"1,2,3, 2,3,1, false", "1,2,3, 1,2,4, false", "1,2,3, 1,2,3, true"})
        void isCorrectTest(int source1ForSample1, int source2ForSample1, int source3ForSample1, int source1ForSample2, int source2ForSample2, int source3ForSample2, boolean expected) {
            // Arrange
            Answer sample1 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample1),
                    SingleNumber.from(source2ForSample1),
                    SingleNumber.from(source3ForSample1)
            ));
            Answer sample2 = Answer.from(Arrays.asList(
                    SingleNumber.from(source1ForSample2),
                    SingleNumber.from(source2ForSample2),
                    SingleNumber.from(source3ForSample2)
            ));

            // Act
            boolean actual = sample1.compareTo(sample2).isCorrect();

            // Assert
            assertThat(actual).isEqualTo(expected);
        }
    }
}
