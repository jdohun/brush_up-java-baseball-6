package baseball.view.output;

import baseball.domain.model.Answer;
import baseball.dto.ComparisonResult;

public class OutputView {
    private static final String OUTPUT_START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String OUTPUT_GAME_OVER = String.format("%d개의 숫자를 모두 맞히셨습니다! 게임 종료", Answer.LIMIT_SIZE_OF_ANSWER);

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OutputView.OutputViewHolder.outputView;
    }

    public void printStart() {
        System.out.println(OUTPUT_START_MESSAGE);
    }

    public void printResult(ComparisonResult result) {
        System.out.println(ResultFormat.getFormatByResult(result));
    }

    public void printGameOver() {
        System.out.println(OUTPUT_GAME_OVER);
    }

    private static class OutputViewHolder {
        private static final OutputView outputView = new OutputView();
    }
}
