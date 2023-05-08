package com.domariev.identityservice.exception;

import lombok.Builder;

import java.util.List;

@Builder
public record ValidationApiError(String validationMessage, List<String> validationReasons) {
}
