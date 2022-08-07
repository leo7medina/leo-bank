package ec.com.leo.bank.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ApiException extends RuntimeException {

    /**
     * Serial.
     */
    private static final long serialVersionUID = 8481265843809953875L;

    /**
     * Code for control.
     */
    private int code;

    /**
     * Type of string, respect enum name.
     */
    private String type;

    /**
     * Constructor.
     */
    public ApiException() {
        super();
    }

    /**
     * Constructor with args.
     * @param message            The message
     * @param cause              The cause
     * @param enableSuppression  true if want to enable suppression, false if not.
     * @param writableStackTrace true if are writable the stack trace, false if not.
     */
    public ApiException(String type, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.setType(type);
    }

    /**
     * Constructor with args.
     * @param message The message
     * @param cause   The cause
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with args.
     * @param message The message
     * @param cause   The cause
     */
    public ApiException(String type, String message, Throwable cause) {
        super(message, cause);
        this.setType(type);
    }

    /**
     * Constructor with args. Personalize exception for API.
     * @param msg A message for exception
     */
    public ApiException(String msg) {
        super(msg);
    }

    /**
     * Constructor with args. Personalize exception for API.
     * @param msg A message for exception
     */
    public ApiException(String msg, int code) {
        super(msg);
        this.setCode(code);
    }

    /**
     * Constructor with args. Personalize exception for API.
     * @param msg A message for exception
     */
    public ApiException(String type, String msg) {
        super(msg);
        this.setType(type);
    }

    /**
     * Constructor with args. Personalize exception for API.
     * @param msg A message for exception
     */
    public ApiException(String type, String msg, int code) {
        super(msg);
        this.setType(type);
        this.setCode(code);
    }

    /**
     * Constructor with args.
     * @param cause The cause
     */
    public ApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with args.
     * @param cause The cause
     */
    public ApiException(Throwable cause, int code) {
        super(cause);
        this.setCode(code);
    }

    /**
     * Get type.
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Set type.
     * @param typeException String
     */
    public void setType(String typeException) {
        this.type = typeException;
    }

    /**
     * Obtener definicion de mensaje de excepcion.
     * @return String
     */
    public String getMessageDefinition() {
        if (StringUtils.isNotBlank(this.getMessage())) {
            return this.getMessage();
        } else if (StringUtils.isNotBlank(this.getLocalizedMessage())) {
            return this.getLocalizedMessage();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Obtener definicion de mensaje de excepcion.
     * @param ex Exception
     * @return String
     */
    public static String getMessageDefinition(Exception ex) {
        if (StringUtils.isNotBlank(ex.getMessage())) {
            return ex.getMessage();
        } else if (StringUtils.isNotBlank(ex.getLocalizedMessage())) {
            return ex.getLocalizedMessage();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Obtener codigo de excepcion.
     * @return int
     */
    public int getCode() {
        if (Objects.nonNull(code)) {
            return code;
        } else {
            return -1;
        }
    }

    /**
     * Comparar codigo de excepcion.
     * @return boolean
     */
    public boolean isCode(int codeEquals) {
        if (getCode() == codeEquals) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Definir codigo de excepcion.
     * @param code  Integer
     */
    public void setCode(int code) {
        this.code = code;
    }
}
