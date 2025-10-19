package calculator.model;

public enum ErrorMessage {
    INVALID_CUSTOM_FORMAT("커스텀 구분자는 '//'와 '\\n' 사이의 문자로 입력 가능합니다."),
    INVALID_CUSTOM_LENGTH("커스텀 구분자는 반드시 하나의 문자로 구성되어야 합니다."),
    INVALID_CUSTOM_NUMERIC("커스텀 구분자는 숫자를 사용할 수 없습니다."),
    INVALID_INPUT_FORMAT("잘못된 형식으로 입력하셨습니다."),
    NOT_POSITIVE_NUMBER("양수가 아닌 숫자는 입력할 수 없습니다. 양수를 입력해주세요."),
    NOT_NUMBER_FORMAT("숫자를 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
