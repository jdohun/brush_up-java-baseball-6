package baseball.domain.model;

public class SingleNumber {
    public static final int START_OF_RANGE = 1;
    public static final int END_OF_RANGE = 9;
    private final int number;

    private SingleNumber(int number) {
        this.number = number;
    }

    public static SingleNumber from(int number) {
        return new SingleNumber(number);
    }

    private static void validate(int number) {
        if (!isValidate(number)) {
            throw new IllegalArgumentException("입력 가능한 숫자의 범위는" + START_OF_RANGE + "~" + END_OF_RANGE + " 입니다.");
        }
    }

    private static boolean isValidate(int number) {
        return number >= START_OF_RANGE && number <= END_OF_RANGE;
    }
}
