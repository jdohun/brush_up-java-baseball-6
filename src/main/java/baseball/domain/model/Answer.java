package baseball.domain.model;

import baseball.dto.ComparisonResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Answer {
    public static final int LIMIT_SIZE_OF_ANSWER = 3;
    private static final Pattern PATTERN_ANSWER = Pattern.compile("^[1-9][1-9][1-9]$");
    private static final String INPUT_DELIMITER = "";

    private List<SingleNumber> numbers;

    private Answer(List<SingleNumber> numbers) {
        this.numbers = numbers;
    }

    public static Answer from(List<SingleNumber> autoNumbers) {
        validateSize(autoNumbers);
        validateDuplication(autoNumbers);
        return new Answer(autoNumbers);
    }

    public static Answer from(String inputNumbers) {
        validateInput(inputNumbers);
        List<SingleNumber> singleNumbers = getSingleNumberList(inputNumbers);
        validateSize(singleNumbers);
        validateDuplication(singleNumbers);
        return new Answer(singleNumbers);
    }

    private static void validateSize(List<SingleNumber> singleNumbers) {
        if (singleNumbers.size() != LIMIT_SIZE_OF_ANSWER) {
            throw new IllegalArgumentException(LIMIT_SIZE_OF_ANSWER + "개의 숫자를 가져야 합니다.");
        }
    }

    private static void validateDuplication(List<SingleNumber> singleNumbers) {
        if (new HashSet<>(singleNumbers).size() != LIMIT_SIZE_OF_ANSWER) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    private static void validateInput(String inputNumbers) {
        Matcher matcher = PATTERN_ANSWER.matcher(inputNumbers);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식에 맞지 않습니다.");
        }
    }

    private static List<SingleNumber> getSingleNumberList(String inputNumbers) {
        List<Integer> integers = getIntegerList(inputNumbers);
        List<SingleNumber> singleNumbers = new ArrayList<>();
        for (int temp : integers) {
            singleNumbers.add(SingleNumber.from(temp));
        }
        return singleNumbers;
    }

    private static List<Integer> getIntegerList(String inputNumbers) {
        List<Integer> pendingValidationAnswer = new ArrayList<>();

        for (String temp : inputNumbers.split(INPUT_DELIMITER)) {
            pendingValidationAnswer.add(Integer.parseInt(temp));
        }
        return pendingValidationAnswer;
    }

    public ComparisonResult compareTo(Answer target) {
        int countOfBall = countBall(target);
        int countOfStrike = countStrike(target);
        boolean isCorrect = countOfStrike == LIMIT_SIZE_OF_ANSWER;

        return new ComparisonResult(countOfBall, countOfStrike, isCorrect);
    }

    private int countStrike(Answer target) {
        return (int) IntStream.range(0, LIMIT_SIZE_OF_ANSWER)
                .filter(i -> this.numbers.get(i) == target.numbers.get(i))
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
                .anyMatch(index -> targetNumber == this.numbers.get(index));
    }
}
