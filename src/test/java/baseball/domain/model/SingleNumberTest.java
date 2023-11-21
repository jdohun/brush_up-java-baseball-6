package baseball.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class SingleNumberTest {

    @Nested
    @DisplayName("단일 숫자 객체 생성 유효성 테스트")
    class ValidationTest {

        @DisplayName("범위를 벗어나는 값은 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(ints = {0, 10})
        void createSingleNumberByOutOfRangeValue(int outOfRangeValue) {
            assertThatThrownBy(() -> SingleNumber.from(outOfRangeValue))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력 가능한 숫자의 범위는" + SingleNumber.START_OF_RANGE + "~" + SingleNumber.END_OF_RANGE + " 입니다.");
        }

        @DisplayName("범위에 포함된 값은 예외를 발생시키지 않는다.")
        @ParameterizedTest
        @MethodSource("provideValuesInRange")
        void createSingleNumberByValueInRange(int valueInRange) {
            assertThatCode(() -> SingleNumber.from(valueInRange))
                    .doesNotThrowAnyException();
        }

        private static Stream<Integer> provideValuesInRange() {
            return IntStream.rangeClosed(SingleNumber.START_OF_RANGE, SingleNumber.END_OF_RANGE).boxed();
        }
    }

    @Nested
    @DisplayName("동치성 테스트")
    class EqualityTest {

        @DisplayName("자기자신과의 비교는 항상 참이다.")
        @Test
        void testEqualWithSelf() {
            SingleNumber sample1 = SingleNumber.from(3);

            assertThat(sample1).isEqualTo(sample1);
        }

        @DisplayName("null 과 비교는 같지 않다.")
        @Test
        void testEqualWithNull() {
            SingleNumber sample1 = SingleNumber.from(3);

            assertThat(sample1).isNotEqualTo(null);
        }

        @DisplayName("SingleNumber 클래스의 하위 클래스가 아니면 같지 않다.")
        @Test
        void testEqualWithNotInstanceof() {
            SingleNumber sample1 = SingleNumber.from(3);

            assertThat(sample1).isNotEqualTo(3);
        }

        @DisplayName("의미하는 값이 같으면 같은 객체이고 다른 값이면 다른 객체다.")
        @Test
        void testEquals() {
            SingleNumber sample1 = SingleNumber.from(3);
            SingleNumber sample2 = SingleNumber.from(3);
            SingleNumber sample3 = SingleNumber.from(4);

            assertThat(sample1).isEqualTo(sample2);
            assertThat(sample2).isEqualTo(sample1);
            assertThat(sample3).isNotEqualTo(sample1)
                    .isNotEqualTo(sample2);
        }

        @DisplayName("의미하는 값이 같은 객체는 같은 해시코드를 가진다.")
        @Test
        void testHashCode() {
            SingleNumber sample1 = SingleNumber.from(3);
            SingleNumber sample2 = SingleNumber.from(3);

            assertThat(sample1).hasSameHashCodeAs(sample2);
        }
    }
}
