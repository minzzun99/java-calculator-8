package calculator.model;

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
                throw new IllegalArgumentException("잘못된 형식으로 입력하셨습니다.");
            }
            validatePositiveNumber(number);
        }
    }

    private void validatePositiveNumber(String number) {
        try {
            if (Integer.parseInt(number) <= 0) {
                throw new IllegalArgumentException("양수가 아닌 숫자는 입력할 수 없습니다. 양수를 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
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
