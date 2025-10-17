package calculator.controller;

import static calculator.view.InputView.requestInput;
import static calculator.view.OutputView.printResult;

import calculator.model.Calculator;

public class CalculatorController {
    public void start() {
        String input = requestInput();
        Calculator calculator = new Calculator();
        printResult(calculator.calculate(input));
    }
}
