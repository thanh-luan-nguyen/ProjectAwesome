package com.example.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {

    /** return code */
    private int result;

    /** error map
     * key: field name
     * value: error message
     */
    private Map<String, String> errors;
}