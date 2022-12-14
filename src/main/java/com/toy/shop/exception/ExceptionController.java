package com.toy.shop.exception;

import com.toy.shop.common.ResultCode;
import com.toy.shop.common.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.toy.shop.common.ResultCode.ERROR;
import static com.toy.shop.common.ResultCode.NOT_VALID;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CommonException.class)
    public ResultDto CommonExceptionHandler(CommonException e) {
        ResultCode resultCode = e.getResultCode();

        return new ResultDto(resultCode.getCode(), resultCode.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDto MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidExceptionHandler]", e);

        ArrayList<String> errors = new ArrayList<>();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField());
        }

        return new ResultDto(NOT_VALID.getCode(), NOT_VALID.getMessage(), errors);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultDto ExceptionHandler(Exception e) {
        log.error("[ExceptionHandler]", e);

        return new ResultDto(ERROR.getCode(), ERROR.getMessage(), null);
    }
}
