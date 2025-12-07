package org.example.miniprojpetadoptionshelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.example.miniprojpetadoptionshelter.common.enums.ErrorCode;
import org.example.miniprojpetadoptionshelter.dto.adoption.response.AdoptionResponse;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    private final boolean success;         // 성공 여부
    private final String message;          // 메시지
    private final T data;                  // 응답 데이터
    private final Instant timestamp;       // 시간

    private final Integer status;          // HTTP 상태 코드
    private final String code;             // ErrorCode(C001, A003 등)

    @Builder
    private ResponseDto(boolean success,
                        String message,
                        T data,
                        Instant timestamp,
                        Integer status,
                        String code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
    }

    // ================================
    // SUCCESS
    // ================================
    public static <T> ResponseDto<T> success(String message, T data) {
        return ResponseDto.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .status(200)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ResponseDto<T> success(String message) {
        return ResponseDto.<T>builder()
                .success(true)
                .message(message)
                .status(200)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ResponseDto<T> success(T data) {
        return ResponseDto.<T>builder()
                .success(true)
                .message("success")
                .data(data)
                .status(200)
                .timestamp(Instant.now())
                .build();
    }

    // ================================
    // FAILURE (커스텀 메시지)
    // ================================
    public static <T> ResponseDto<T> failure(String message) {
        return ResponseDto.<T>builder()
                .success(false)
                .message(message)
                .status(400)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ResponseDto<T> failure(String message, Integer httpStatus) {
        return ResponseDto.<T>builder()
                .success(false)
                .message(message)
                .status(httpStatus)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ResponseDto<T> failure(String message, Integer httpStatus, String code) {
        return ResponseDto.<T>builder()
                .success(false)
                .message(message)
                .status(httpStatus)
                .code(code)
                .timestamp(Instant.now())
                .build();
    }

    // ================================
    // FAILURE (ErrorCode 기반) → ★ 실무 핵심 ★
    // ================================
    public static <T> ResponseDto<T> error(ErrorCode errorCode) {
        return ResponseDto.<T>builder()
                .success(false)
                .message(errorCode.getMessage())
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ResponseDto<T> ok(T data) {
        return ResponseDto.<T>builder()
                .success(true)
                .data(data)                      // 너의 DTO 필드명이 data라면 이대로, 아니라면 필드명에 맞춰 변경
                .message(null)                   // 메시지 없으면 null
                .status(HttpStatus.OK.value())   // 200
                .code("0")                       // 성공 코드 관례에 맞게 변경 가능
                .timestamp(Instant.now())
                .build();
    }
}