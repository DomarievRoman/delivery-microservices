package com.domariev.identityservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public record ApiError(String path, Object message, String reason, int statusCode,
                       @JsonSerialize(using = LocalDateTimeSerializer.class)
                       @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                       LocalDateTime localDateTime) {
}
