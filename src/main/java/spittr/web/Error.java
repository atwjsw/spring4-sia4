package spittr.web;

/**
 * Created by wenda on 8/11/2017.
 */
public class Error {
    private int code;
    private String message;
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
