package com.bartender.api.controllers;

import com.bartender.api.dto.ErrorDto;
import com.bartender.api.dto.BartenderRequestDto;
import com.bartender.api.services.BartenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${version}")
public class BartenderController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private BartenderService bartenderService;

    @GetMapping("/")
    public String hello(){
        return "Server Running in port: "+port;
    }

    @PostMapping("/bartender")
    public Object calculate(@RequestBody @Valid BartenderRequestDto body) {
        return bartenderService.calculate(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            if(!errors.containsValue(errorMessage)) {
                errors.put(fieldName, errorMessage);
            }
        });
        ErrorDto response = new ErrorDto();
        response.setErrors(errors);
        return response;
    }

}
