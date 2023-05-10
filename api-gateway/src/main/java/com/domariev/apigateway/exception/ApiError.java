package com.domariev.apigateway.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public record ApiError(String path, Object message, String reason, int statusCode,
                       @JsonSerialize(using = LocalDateTimeSerializer.class)
                       @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                       @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                       LocalDateTime localDateTime) {
    public ApiError(String reason, int statusCode, LocalDateTime localDateTime) {
        this(null, null, reason, statusCode, localDateTime);
    }

}
