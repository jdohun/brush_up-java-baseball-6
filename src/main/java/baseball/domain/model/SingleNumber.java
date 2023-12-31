package baseball.domain.model;

public class SingleNumber {
    public static final int START_OF_RANGE = 1;
    public static final int END_OF_RANGE = 9;
    private final int number;

    private SingleNumber(int number) {
        this.number = number;
    }

    public static SingleNumber from(int number) {
        validateRange(number);
        return new SingleNumber(number);
    }

    private static void validateRange(int number) {
        if (!isValidateRange(number)) {
            throw new IllegalArgumentException("입력 가능한 숫자의 범위는" + START_OF_RANGE + "~" + END_OF_RANGE + " 입니다.");
        }
    }

    private static boolean isValidateRange(int number) {
        return number >= START_OF_RANGE && number <= END_OF_RANGE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof SingleNumber)) {
            return false;
        }

        SingleNumber target = (SingleNumber) obj;

        return number == target.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("SingleNumber{number=%d}", number);
    }
}
