package ee.bcs.valiit.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //controller advice
public class ErrorHandler {

    @ExceptionHandler(Exception.class) //ütleme, et see on meetod, mis vastutab exeption händlemise eest
    public ResponseEntity<Object> handleError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Account is not active!", 400),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
