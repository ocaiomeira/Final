package handler;

import com.projetoEstoque.ControleEstoque.exception.BadRequestException;
import com.projetoEstoque.ControleEstoque.exception.BadRequestExceptionDatails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDatails> handlerBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
            BadRequestExceptionDatails.builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .title("BAD REQUEST EXCEPTION, Check the Documentation")
                    .details(badRequestException.getMessage())
                    .developerMessage(badRequestException.getClass().getName())
                    .build(), HttpStatus.BAD_REQUEST
        );
    }


}
