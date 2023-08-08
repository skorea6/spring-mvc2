package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// @ControllerAdvice("org.example.controllers") : 컨트롤러 별로 따로 분리도 가능하다.
public class ExControllerAdvice {
    /**
     * 해당 컨트롤러에서 예외가 발생하면 이 메서드가 호출된다.
     * 참고로 지정한 예외 또는 그 예외의 자식 클래스는 모두 잡을 수 있다.
     * @ExceptionHandler 에 지정한 부모 클래스는 자식 클래스까지 처리할 수 있다.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    /**
     * @ExceptionHandler 에 예외를 지정하지 않으면 해당 메서드 파라미터 예외를 사용한다.
     * 여기서는 UserException 을 사용한다.
     * ResponseEntity 를 사용해서 HTTP 메시지 바디에 직접 응답한다. 물론 HTTP 컨버터가 사용된다.
     * ResponseEntity 를 사용하면 HTTP 응답 코드를 프로그래밍해서 '동적'으로 변경할 수 있다.
     * 앞서 살펴본 @ResponseStatus 는 애노테이션이므로 HTTP 응답 코드를 동적으로 변경할 수 없다.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    /**
     * RuntimeException 은 Exception 의 자식 클래스이다. 따라서 이 메서드가 호출된다.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
