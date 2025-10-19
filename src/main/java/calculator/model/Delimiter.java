package calculator.model;

import static calculator.model.ErrorMessage.INVALID_CUSTOM_FORMAT;
import static calculator.model.ErrorMessage.INVALID_CUSTOM_LENGTH;
import static calculator.model.ErrorMessage.INVALID_CUSTOM_NUMERIC;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Delimiter {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";
    private static final int VALID_DELIMITER_LENGTH = 1;
    private static final int CUSTOM_DELIMITER_START = 2;

    private final Set<Character> delimiters;

    // 기본 구분자
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

    // split 메서드 사용 시 구분자들로 분리하기위한 전처리
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
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    // 커스텀 구분자 검증
    private String validateCustomDelimiter(String input) {
        int index = validateInputFormat(input);
        char customDelimiter = validateLength(input, index);
        validateNumericValue(customDelimiter);

        addCustomDelimiter(customDelimiter);
        return input.substring(index + CUSTOM_DELIMITER_START);
    }

    // 커스텀 구분자 입력 포멧 검증
    private int validateInputFormat(String input) {
        int index = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        if (index == -1) {
            throw new IllegalArgumentException(INVALID_CUSTOM_FORMAT.getMessage());
        }

        return index;
    }

    // 커스텀 구분자 갯수 검증
    private char validateLength(String input, int index) {
        String customLine = input.substring(CUSTOM_DELIMITER_START, index);
        if (customLine.length() != VALID_DELIMITER_LENGTH) {
            throw new IllegalArgumentException(INVALID_CUSTOM_LENGTH.getMessage());
        }

        return customLine.charAt(0);
    }

    // 커스텀 구분자 숫자 여부 검증
    private void validateNumericValue(char customDelimiter) {
        if (customDelimiter >= '0' && customDelimiter <= '9') {
            throw new IllegalArgumentException(INVALID_CUSTOM_NUMERIC.getMessage());
        }
    }
}
