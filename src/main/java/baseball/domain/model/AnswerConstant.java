package baseball.domain.model;

import java.util.regex.Pattern;

final class AnswerConstant {
    private AnswerConstant() {
    }

    static final Pattern PATTERN_ANSWER = Pattern.compile("^[1-9][1-9][1-9]$");
    static final String INPUT_DELIMITER = "";
    static final int LIMIT_SIZE_OF_ANSWER = 3;
}
