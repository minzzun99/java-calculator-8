package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    public static String requestInput() {
        System.out.print(REQUEST_INPUT_MESSAGE);
        return Console.readLine();
    }
}
