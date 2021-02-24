package com.github.dreamroute.distx.starter.exception;

/**
 * 异常
 * 
 * @author w.dehai
 */
public class DistxException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2314282162812799283L;

    public DistxException() {
        super();
    }

    public DistxException(String message) {
        super(message);
    }

    public DistxException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistxException(Throwable cause) {
        super(cause);
    }
}
