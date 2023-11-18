package baseball.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

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
            throw new IllegalArgumentException(ERROR_SIZE_LIMIT_EXCEEDED);
        }
    }

    private static void validateDuplication(List<Integer> pendingValidationData) {
        Set<Integer> duplicateChecker = new HashSet<>();
        for (int temp : pendingValidationData) {
            if (!duplicateChecker.add(temp)) {
                throw new IllegalArgumentException(ERROR_HAS_DUPLICATE);
            }
        }
    }

    private static void validateInput(String inputNumbers) {
        Matcher matcher = PATTERN_ANSWER.matcher(inputNumbers);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ERROR_DOES_NOT_FIT_PATTERN);
        }
    }

    private static List<Integer> inputToPendingValidationAnswer(String inputNumbers) {
        List<Integer> pendingValidationAnswer = new ArrayList<>();

        for (String temp : inputNumbers.split(INPUT_DELIMITER)) {
            pendingValidationAnswer.add(Integer.parseInt(temp));
        }
        return pendingValidationAnswer;
    }
}
