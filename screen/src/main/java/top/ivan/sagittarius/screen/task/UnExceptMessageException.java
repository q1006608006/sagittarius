package top.ivan.sagittarius.screen.task;

public class UnExceptMessageException extends RuntimeException {

    private static final long serialVersionUID = 7025603200396211142L;

    public UnExceptMessageException(){
    }

    public UnExceptMessageException(String err) {
        super(err);
    }

    public UnExceptMessageException(String err,Throwable cause) {
        super(err,cause);
    }
}
