package baseball.generator;

import baseball.domain.model.SingleNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SingleNumberGeneratorTest {

    private SingleNumberGenerator SINGLE_NUMBER_GENERATOR = SingleNumberGenerator.getInstance();

    @DisplayName("범위를 벗어나지 않는 단일 숫자 객체를 생성하지 않는다.")
    @RepeatedTest(10)
    void generateSingleNumberTest() {
        // Arrange
        List<SingleNumber> singleNumbers = Arrays.asList(
                SingleNumber.from(1),
                SingleNumber.from(2),
                SingleNumber.from(3),
                SingleNumber.from(4),
                SingleNumber.from(5),
                SingleNumber.from(6),
                SingleNumber.from(7),
                SingleNumber.from(8),
                SingleNumber.from(9)
        );

        // Act
        SingleNumber singleNumber = SINGLE_NUMBER_GENERATOR.run();

        // Assert
        assertThat(singleNumbers).contains(singleNumber);
    }
}