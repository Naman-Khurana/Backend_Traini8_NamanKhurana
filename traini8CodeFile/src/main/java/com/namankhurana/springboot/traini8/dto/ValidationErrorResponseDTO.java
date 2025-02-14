package com.namankhurana.springboot.traini8.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ValidationErrorResponseDTO {

    private int status;
    private String message;
    private String timestamp;
    private Map<String, String> errors; // Key: Field Name, Value: Error Message

}
