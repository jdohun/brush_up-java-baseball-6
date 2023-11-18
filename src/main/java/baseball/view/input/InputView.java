package baseball.view.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_NUMBERS = "숫자를 입력해주세요 : ";
    private static final String INPUT_RETRY = String.format("게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.", 1, 2);

    private InputView() {
    }

    public static InputView getInstance() {
        return InputViewHolder.inputView;
    }

    public String requestInputNumbers() {
        System.out.printf(INPUT_NUMBERS);
        return Console.readLine();
    }

    private static class InputViewHolder {
        private static final InputView inputView = new InputView();
    }
}
