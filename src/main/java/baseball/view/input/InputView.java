package baseball.view.input;

import camp.nextstep.edu.missionutils.Console;

import static baseball.handler.InputHandler.GAME_OVER;
import static baseball.handler.InputHandler.RESTART;


public class InputView {
    private static final String INPUT_NUMBERS = "숫자를 입력해주세요 : ";
    private static final String INPUT_RESTART = String.format("게임을 새로 시작하려면 %d, 종료하려면 %d를 입력하세요.", RESTART, GAME_OVER);

    private InputView() {
    }

    public static InputView getInstance() {
        return InputViewHolder.inputView;
    }

    public String inputNumbers() {
        System.out.printf(INPUT_NUMBERS);
        return Console.readLine();
    }

    public String inputIsRestart() {
        System.out.println(INPUT_RESTART);
        return Console.readLine();
    }

    private static class InputViewHolder {
        private static final InputView inputView = new InputView();
    }
}
