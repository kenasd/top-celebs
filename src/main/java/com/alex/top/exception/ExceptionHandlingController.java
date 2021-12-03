package com.alex.top.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Reset is running")
    @ExceptionHandler(ResetIsActiveException.class)
    public void conflict() {
        log.error("Reset is running conflict");
    }
}
