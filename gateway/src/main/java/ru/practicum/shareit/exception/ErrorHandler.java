package ru.practicum.shareit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ErrorHandler {
    // Объявляем ExceptionResponse как статический внутренний класс
    @Data // Lombok аннотация для геттеров, сеттеров, toString и т.д.
    @AllArgsConstructor // Автоматически создаст конструктор со всеми полями
    public static class ExceptionResponse {
        private String error;
        private String message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentValidationExceptionHandle(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ExceptionResponse("Ошибка валидации", message);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse internalServerExceptionHandle(Exception e) {
        return new ExceptionResponse("Ошибка сервера", e.getMessage());
    }
}