package com.dodone.dodone.controller.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    @JsonFormat(shape= JsonFormat.Shape.STRING,
            pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime timeStamp;

}
