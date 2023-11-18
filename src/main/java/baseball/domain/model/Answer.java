package baseball.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

import static baseball.domain.model.AnswerConstant.*;

public class Answer {

    private List<Integer> numbers;

    private Answer(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Answer from(List<Integer> autoNumbers) {
        validateSize(autoNumbers);
        validateDuplication(autoNumbers);
        return new Answer(autoNumbers);
    }

    public static Answer from(String inputNumbers) {
        validateInput(inputNumbers);
        List<Integer> pendingValidationAnswer = inputToPendingValidationAnswer(inputNumbers);
        validateSize(pendingValidationAnswer);
        validateDuplication(pendingValidationAnswer);
        return new Answer(pendingValidationAnswer);
    }

    private static void validateSize(List<Integer> pendingValidationData) {
        if (pendingValidationData.size() != LIMIT_SIZE_OF_ANSWER) {
            throw new IllegalArgumentException(LIMIT_SIZE_OF_ANSWER + "개의 숫자를 가져야 합니다.");
        }
    }

    private static void validateDuplication(List<Integer> pendingValidationData) {
        Set<Integer> duplicateChecker = new HashSet<>();
        for (int temp : pendingValidationData) {
            if (!duplicateChecker.add(temp)) {
                throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
            }
        }
    }

    private static void validateInput(String inputNumbers) {
        Matcher matcher = PATTERN_ANSWER.matcher(inputNumbers);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력 형식에 맞지 않습니다.");
        }
    }

    private static List<Integer> inputToPendingValidationAnswer(String inputNumbers) {
        List<Integer> pendingValidationAnswer = new ArrayList<>();

        for (String temp : inputNumbers.split(INPUT_DELIMITER)) {
            pendingValidationAnswer.add(Integer.parseInt(temp));
        }
        return pendingValidationAnswer;
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

    private boolean isBallCount(int targetNumber, int excludedIndex) {
        return IntStream.range(0, LIMIT_SIZE_OF_ANSWER)
                .filter(index -> index != excludedIndex)
                .anyMatch(index -> targetNumber == this.numbers.get(index));
    }
}
