package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.model.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

    @ParameterizedTest
    @DisplayName("잘못된 입력 형식 예외 발생")
    @ValueSource(strings = {",1,2", "1,,2", "1,2,"})
    void 잘못된_입력_형식_예외_발생(String input) {
        Calculator calculator = new Calculator();
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 값 입력 시 0반환")
    void 빈_값_입력_시_0반환() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculate("")).isEqualTo(0);
    }
}
