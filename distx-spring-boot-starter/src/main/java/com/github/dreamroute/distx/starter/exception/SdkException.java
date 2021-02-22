package com.github.dreamroute.distx.starter.exception;

/**
 * SDK异常
 * 
 * @author w.dehai
 */
public class SdkException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2314282162812799283L;

    public SdkException() {
        super();
    }

    public SdkException(String message) {
        super(message);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SdkException(Throwable cause) {
        super(cause);
    }
}
