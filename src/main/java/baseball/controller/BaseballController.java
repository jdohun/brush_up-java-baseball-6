package baseball.controller;

import baseball.domain.model.Answer;
import baseball.dto.ComparisonResult;
import baseball.generator.AnswerGenerator;
import baseball.handler.InputHandler;
import baseball.view.input.InputView;
import baseball.view.output.OutputView;

public class BaseballController {
    private final InputHandler INPUT_HANDLER = InputHandler.getInstance();
    private final InputView INPUT_VIEW = InputView.getInstance();
    private final OutputView OUTPUT_VIEW = OutputView.getInstance();
    private final AnswerGenerator ANSWER_GENERATOR = AnswerGenerator.getInstance();
    private Answer computer;

    public void run() {
        do {
            gameStart();
        } while (INPUT_HANDLER.inputToRetry(INPUT_VIEW.inputIsRestart()));
    }

    private void gameStart() {
        OUTPUT_VIEW.printStart();

        ComparisonResult comparisonResult;
        computer = ANSWER_GENERATOR.run();

        do {
            Answer userAnswer = Answer.from(InputHandler.inputToIntegerArray(INPUT_VIEW.inputNumbers()));
            comparisonResult = computer.compareTo(userAnswer);
            OUTPUT_VIEW.printResult(comparisonResult);
        } while (Answer.LIMIT_SIZE_OF_ANSWER != comparisonResult.countOfStrike());

        OUTPUT_VIEW.printGameOver();
    }
}
