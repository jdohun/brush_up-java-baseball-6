package baseball.domain.model;

import java.util.regex.Pattern;

final class AnswerConstant {
    private AnswerConstant() {
    }

    static final Pattern PATTERN_ANSWER = Pattern.compile("^[1-9][1-9][1-9]$");
    static final String INPUT_DELIMITER = "";
    static final int LIMIT_SIZE_OF_ANSWER = 3;
    static final String ERROR_HAS_DUPLICATE = "중복된 숫자가 존재합니다.";
    static final String ERROR_SIZE_LIMIT_EXCEEDED = String.format("%d개의 숫자를 가져야 합니다.", LIMIT_SIZE_OF_ANSWER);
    static final String ERROR_DOES_NOT_FIT_PATTERN = "입력 형식에 맞지 않습니다.";
}
