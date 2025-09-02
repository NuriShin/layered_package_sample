package com.example.layered.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    
    private final boolean success;
    private final String code;
    private final String message;
    private final T data;
    
    private ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "200", "SUCCESS", data);
    }
    
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, "200", "SUCCESS", null);
    }
    
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }
    
    public static <T> ApiResponse<T> error(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}