package hei.school.even_sync_backend.exception;

public class ForbiddenRequestException extends RuntimeException {
    public ForbiddenRequestException(String message){
        super(message);
    }
}
