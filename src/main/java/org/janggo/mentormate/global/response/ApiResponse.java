package org.janggo.mentormate.global.response;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public class ApiResponse<T> {
    private final int status;
    private final boolean success;
    private final String message;
    private final T data;

    public static <T> ResponseEntity<ApiResponse<T>> success(SuccessMessage successMessage){
        return ResponseEntity.status(successMessage.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(successMessage.getStatusCode())
                        .success(true)
                        .message(successMessage.getMessage())
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(SuccessMessage successMessage, T data){
        return ResponseEntity.status(successMessage.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(successMessage.getStatusCode())
                        .success(true)
                        .message(successMessage.getMessage())
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> failure(FailureMessage failureMessage) {
        return ResponseEntity.status(failureMessage.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(failureMessage.getStatusCode())
                        .success(false)
                        .message(failureMessage.getMessage())
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> failure(FailureMessage failureMessage, T data) {
        return ResponseEntity.status(failureMessage.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(failureMessage.getStatusCode())
                        .success(false)
                        .message(failureMessage.getMessage())
                        .data(data)
                        .build());
    }
}
