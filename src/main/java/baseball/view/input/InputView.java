package baseball.view.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_NUMBERS = "숫자를 입력해주세요 : ";
    private static final int RESTART = 1;
    private static final int GAME_OVER = 2;
    private static final String INPUT_RETRY = String.format("게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.", RESTART, GAME_OVER);

    private InputView() {
    }

    public static InputView getInstance() {
        return InputViewHolder.inputView;
    }

    public String requestInputNumbers() {
        System.out.printf(INPUT_NUMBERS);
        return Console.readLine();
    }

    public String requestInputIsRestart() {
        System.out.println(INPUT_RETRY);
        return Console.readLine();
    }

    private static class InputViewHolder {
        private static final InputView inputView = new InputView();
    }
}
