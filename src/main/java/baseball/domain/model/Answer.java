package baseball.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Answer {

    private static final Pattern PATTERN_ANSWER = Pattern.compile("^[1-9][1-9][1-9]$");
    private static final String ANSWER_DELIMITER = "";
    private static final String ERROR_HAS_DUPLICATE = "중복된 숫자가 존재합니다.";
    private static final String ERROR_DOES_NOT_FIT_PATTERN = "입력 형식에 맞지 않습니다.";

    private List<Integer> answer;

    private Answer(List<Integer> answer) {
        this.answer = answer;
    }

    public static Answer from(List<Integer> autoAnswer) {
        validateDuplication(autoAnswer);
        return new Answer(autoAnswer);
    }

    public static Answer from(String inputNumbers) {
        validateInput(inputNumbers);
        List<Integer> pendingValidationAnswer = inputToPendingValidationAnswer(inputNumbers);
        validateDuplication(pendingValidationAnswer);
        return new Answer(pendingValidationAnswer);
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

        for (String temp : inputNumbers.split(ANSWER_DELIMITER)) {
            pendingValidationAnswer.add(Integer.parseInt(temp));
        }
        return pendingValidationAnswer;
    }
}
