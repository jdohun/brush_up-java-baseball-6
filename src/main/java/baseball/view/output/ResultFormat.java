package baseball.view.output;

import baseball.dto.ComparisonResult;

final class ResultFormat {
    static final String NOTHING = "낫싱";
    static final String BALL = "%d볼";
    static final String STRIKE = "%d스트라이크";
    static final String BALL_AND_STRIKE = "%d볼 %d스트라이크";

    private ResultFormat() {
    }

    static String getFormatByResult(ComparisonResult result) {
        int ballCount = result.countOfBall();
        int strikeCount = result.countOfStrike();

        if (ballCount == 0 && strikeCount > 0) {
            return String.format(STRIKE, strikeCount);
        }

        if (ballCount > 0 && strikeCount == 0) {
            return String.format(BALL, ballCount);
        }

        if (ballCount > 0 && strikeCount > 0) {
            return String.format(BALL_AND_STRIKE, ballCount, strikeCount);
        }

        return NOTHING;
    }

}
