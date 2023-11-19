package baseball.view.output;

import baseball.dto.ComparisonResult;

public class OutputView {
    private static final String OUTPUT_START_MESSAGE = "숫자 야구 게임을 시작합니다.";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OutputView.OutputViewHolder.outputView;
    }

    public void printStart() {
        System.out.println(OUTPUT_START_MESSAGE);
    }

    public void printResult(ComparisonResult result) {
        System.out.println(ResultFormat.getFormatByResult(result));;
    }

    private static class OutputViewHolder {
        private static final OutputView outputView = new OutputView();
    }
}
