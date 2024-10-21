package com.PiXl.mainframe.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        return new ResponseEntity<Object>(map, status);
    }

    // public static ResponseEntity<Object> generateResponse(String message,
    // HttpStatus status, String data) {
    // Map<String, Object> map = new HashMap<>();
    // map.put("message", message);
    // map.put("status", status);
    // map.put("data", data);
    // return new ResponseEntity<Object>(map, status);
    // }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", data);
        return new ResponseEntity<Object>(map, status);
    }
}
