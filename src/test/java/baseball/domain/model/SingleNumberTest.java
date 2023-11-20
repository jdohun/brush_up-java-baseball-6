package baseball.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
        void createSingleNumberByValueInRange(int valueInRange) {
            assertThatCode(() -> SingleNumber.from(valueInRange))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("동치성 테스트")
    class EqualityTest {

        @DisplayName("의미하는 값을 통해 객체의 같고 다름을 판별할 수 있다.")
        @Test
        void testEquals() {
            SingleNumber sample1 = SingleNumber.from(3);
            SingleNumber sample2 = SingleNumber.from(3);
            SingleNumber sample3 = SingleNumber.from(4);

            assertThat(sample1).isEqualTo(sample2)
                    .isNotEqualTo(sample3);
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
