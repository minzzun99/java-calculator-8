package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.model.Delimiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DelimiterTest {
    @Test
    @DisplayName("기본 구분자 파싱")
    void 기본_구분자_파싱() {
        Delimiter delimiter = new Delimiter();
        String[] numbers = delimiter.split("1,2:3");
        assertThat(numbers).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자 파싱")
    void 커스텀_구분자_파싱() {
        Delimiter delimiter = new Delimiter();
        String[] numbers = delimiter.split("//;\\n1;2;3");
        assertThat(numbers).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자 커스텀 구분자 혼합 파싱")
    void 기본_구분자_커스텀_구분자_혼합_파싱() {
        Delimiter delimiter = new Delimiter();
        String[] numbers = delimiter.split("//;\\n1,2:3;4");
        assertThat(numbers).containsExactly("1", "2", "3", "4");
    }

    @Test
    @DisplayName("커스텀 구분자 입력 포멧 예외 발생")
    void 커스텀_구분자_입력_포멧_예외_발생() {
        Delimiter delimiter = new Delimiter();
        assertThatThrownBy(() -> delimiter.split("//;1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 글자 이상의 커스텀 구분자 예외 발생")
    void 두_글자_이상의_커스텀_구분자_예외_발생() {
        Delimiter delimiter = new Delimiter();
        assertThatThrownBy(() -> delimiter.split("//;;\\n1;;2;;3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자 숫자 입력 예외 발생")
    void 커스텀_구분자_숫자_입력_예외_발생() {
        Delimiter delimiter = new Delimiter();
        assertThatThrownBy(() -> delimiter.split("//1\\n11213"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 입력 형식의 첫 구분자 파싱")
    void 잘못된_입력_형식의_첫_구분자_파싱() {
        Delimiter delimiter = new Delimiter();
        assertThat(delimiter.split(",1,2,3")).containsExactly("", "1", "2", "3");
    }

    @Test
    @DisplayName("잘못된 입력 형식의 중간 구분자 파싱")
    void 잘못된_입력_형식의_중간_구분자_파싱() {
        Delimiter delimiter = new Delimiter();
        assertThat(delimiter.split("1,,2")).containsExactly("1", "", "2");
    }

    @Test
    @DisplayName("잘못된 입력 형식의 끝 구분자 파싱")
    void 잘못된_입력_형식의_끝_구분자_파싱() {
        Delimiter delimiter = new Delimiter();
        assertThat(delimiter.split("1,2,")).containsExactly("1", "2", "");
    }
}
