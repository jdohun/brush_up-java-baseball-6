package baseball.handler;

import baseball.domain.model.Answer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    public static final Pattern PATTERN_RETRY = Pattern.compile("^[1-2]$");
    public static final int RESTART = 1;
    public static final int GAME_OVER = 2;
    private static final Pattern PATTERN_ANSWER = Pattern.compile("^[1-9][1-9][1-9]$");
    private static final String INPUT_DELIMITER = "";

    private InputHandler() {
    }

    public static InputHandler getInstance() {
        return Holder.INSTANCE;
    }

    public static int[] inputToIntegerArray(String inputNumbers) {
        validateNotNull(inputNumbers);
        validateAnswerFormat(inputNumbers);

        String[] splitInputNumbers = inputNumbers.split(INPUT_DELIMITER);
        int[] pendingValidationAnswer = new int[Answer.LIMIT_SIZE_OF_ANSWER];

        for (int i = 0; i < Answer.LIMIT_SIZE_OF_ANSWER; i++) {
            pendingValidationAnswer[i] = Integer.parseInt(splitInputNumbers[i]);
        }

        return pendingValidationAnswer;
    }

    private static void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("null 을 입력할 수 없습니다.");
        }
    }

    private static void validateAnswerFormat(String inputNumbers) {
        Matcher matcher = PATTERN_ANSWER.matcher(inputNumbers);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("정답 입력 형식에 맞지 않습니다.");
        }
    }

    public boolean inputToRetry(String inputRetry) {
        validateNotNull(inputRetry);
        validateRetryFormat(inputRetry);
        return Integer.parseInt(inputRetry) == RESTART;
    }

    private void validateRetryFormat(String inputRetry) {
        Matcher matcher = PATTERN_RETRY.matcher(inputRetry);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("재시도 입력 형식에 맞지 않습니다.");
        }
    }

    private static class Holder {
        private static final InputHandler INSTANCE = new InputHandler();
    }

}
