package calculator.model;

import static calculator.model.ErrorMessage.INVALID_INPUT_FORMAT;
import static calculator.model.ErrorMessage.NOT_NUMBER_FORMAT;
import static calculator.model.ErrorMessage.NOT_POSITIVE_NUMBER;

public class Calculator {
    private final Delimiter delimiter;

    public Calculator() {
        this.delimiter = new Delimiter();
    }

    public int calculate(String input) {
        // 빈 값이 입력되면 0 리턴
        if (input.isEmpty()) {
            return 0;
        }

        String[] numbers = delimiter.split(input);
        validateNumbers(numbers);
        return sum(numbers);
    }

    private void validateNumbers(String[] numbers) {
        for (String number : numbers) {
            if (number.isEmpty()) {
                throw new IllegalArgumentException(INVALID_INPUT_FORMAT.getMessage());
            }
            validatePositiveNumber(number);
        }
    }

    private void validatePositiveNumber(String number) {
        try {
            if (Integer.parseInt(number) <= 0) {
                throw new IllegalArgumentException(NOT_POSITIVE_NUMBER.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_FORMAT.getMessage());
        }
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}
