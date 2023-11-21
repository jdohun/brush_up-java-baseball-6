package baseball.domain.model;

import baseball.dto.ComparisonResult;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Answer {
    public static final int LIMIT_SIZE_OF_ANSWER = 3;

    private final List<SingleNumber> numbers;

    private Answer(List<SingleNumber> numbers) {
        this.numbers = numbers;
    }

    public static Answer from(List<SingleNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
        return new Answer(numbers);
    }

    private static void validateSize(List<SingleNumber> singleNumbers) {
        if (singleNumbers.size() != LIMIT_SIZE_OF_ANSWER) {
            throw new IllegalArgumentException(String.format("%d개의 숫자를 가져야 합니다.", LIMIT_SIZE_OF_ANSWER));
        }
    }

    private static void validateDuplication(List<SingleNumber> singleNumbers) {
        if (new HashSet<>(singleNumbers).size() != LIMIT_SIZE_OF_ANSWER) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    public ComparisonResult compareTo(Answer target) {
        int countOfBall = countBall(target);
        int countOfStrike = countStrike(target);

        return new ComparisonResult(countOfBall, countOfStrike, LIMIT_SIZE_OF_ANSWER == countOfStrike);
    }

    private int countStrike(Answer target) {
        return (int) IntStream.range(0, LIMIT_SIZE_OF_ANSWER)
                .filter(i -> this.numbers.get(i).equals(target.numbers.get(i)))
                .count();
    }

    private int countBall(Answer target) {
        return (int) IntStream.range(0, LIMIT_SIZE_OF_ANSWER)
                .filter(i -> isBallCount(target.numbers.get(i), i))
                .count();
    }

    private boolean isBallCount(SingleNumber targetNumber, int excludedIndex) {
        return IntStream.range(0, LIMIT_SIZE_OF_ANSWER)
                .filter(index -> index != excludedIndex)
                .anyMatch(index -> this.numbers.get(index).equals(targetNumber));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof Answer target)) {
            return false;
        }

        return numbers.equals(target.numbers);
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Answer{numbers=%s}", numbers);
    }
}
