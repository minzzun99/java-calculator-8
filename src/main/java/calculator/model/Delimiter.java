package calculator.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Delimiter {
    private final Set<Character> delimiters;

    public Delimiter() {
        delimiters = new HashSet<>(Arrays.asList(',', ':'));
    }

    public String[] split(String input) {
        if (checkCustomDelimiter(input)) {
            input = validateCustomDelimiter(input);
        }

        // 마지막 문자열이 빈 문자열일 경우 빈 문자열이 제거되지 않고 유지되도록
        return input.split(createRegex(), -1);
    }

    private String createRegex() {
        StringBuilder regex = new StringBuilder("[");
        for (char delimiter : delimiters) {
            regex.append(delimiter);
        }
        regex.append("]");
        return regex.toString();
    }

    private void addCustomDelimiter(char delimiter) {
        delimiters.add(delimiter);
    }

    // 커스텀 구분자 존재 여부 확인
    private boolean checkCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    // 커스텀 구분자 검증
    private String validateCustomDelimiter(String input) {
        int index = validateInputFormat(input);
        char customDelimiter = validateLength(input, index);
        validateNumericValue(customDelimiter);

        addCustomDelimiter(customDelimiter);
        return input.substring(index + 2);
    }

    // 커스텀 구분자 입력 포멧 검증
    private int validateInputFormat(String input) {
        int index = input.indexOf("\\n");
        if (index == -1) {
            throw new IllegalArgumentException("커스텀 구분자는 '//'와 '\\n' 사이의 문자로 입력 가능합니다.");
        }

        return index;
    }

    // 커스텀 구분자 갯수 검증
    private char validateLength(String input, int index) {
        String customLine = input.substring(2, index);
        if (customLine.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 반드시 하나의 문자로 구성되어야 합니다.");
        }

        return customLine.charAt(0);
    }

    // 커스텀 구분자 숫자 여부 검증
    private void validateNumericValue(char customDelimiter) {
        if (customDelimiter >= '0' && customDelimiter <= '9') {
            throw new IllegalArgumentException("커스텀 구분자는 숫자를 사용할 수 없습니다.");
        }
    }
}
