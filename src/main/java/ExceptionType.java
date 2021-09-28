public class ExceptionType extends Exception {
    ExceptionName type;

    public ExceptionType(ExceptionName type, String message) {
        super(message);
        this.type = type;
    }

    enum ExceptionName {NAME_NOT_FOUND}

}
