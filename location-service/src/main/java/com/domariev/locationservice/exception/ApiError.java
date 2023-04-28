package com.domariev.locationservice.exception;

import java.time.LocalDateTime;

public record ApiError(String path, String message, String reason, int statusCode, LocalDateTime localDateTime) {
}
