package baseball.controller;

import baseball.domain.model.Answer;
import baseball.dto.ComparisonResult;
import baseball.generator.RandomAnswerGenerator;
import baseball.handler.InputHandler;
import baseball.view.input.InputView;
import baseball.view.output.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class BaseballController {
    private final InputHandler INPUT_HANDLER = InputHandler.getInstance();
    private final InputView INPUT_VIEW = InputView.getInstance();
    private final OutputView OUTPUT_VIEW = OutputView.getInstance();
    private final RandomAnswerGenerator RANDOM_ANSWER_GENERATOR = RandomAnswerGenerator.getInstance();
    private Answer computer;

    public void run() {
        try {
            do {
                gameStart();
            } while (INPUT_HANDLER.inputToRetry(INPUT_VIEW.inputIsRestart()));
        } finally {
            Console.close();
        }
    }

    private void gameStart() {
        OUTPUT_VIEW.printStart();

        ComparisonResult comparisonResult;
        computer = RANDOM_ANSWER_GENERATOR.run();

        do {
            Answer userAnswer = Answer.from(InputHandler.inputToSourceForAnswer(INPUT_VIEW.inputNumbers()));
            comparisonResult = computer.compareTo(userAnswer);
            OUTPUT_VIEW.printResult(comparisonResult);
        } while (comparisonResult.isCorrect());

        OUTPUT_VIEW.printGameOver();
    }
}
