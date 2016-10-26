package com.leqcar.instalmentbilling.infrastructure.infixpostfix;

public class ParseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
