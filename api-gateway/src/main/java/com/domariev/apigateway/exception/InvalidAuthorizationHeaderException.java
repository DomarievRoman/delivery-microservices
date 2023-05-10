package com.domariev.apigateway.exception;

import lombok.experimental.StandardException;

@StandardException
public class InvalidAuthorizationHeaderException extends RuntimeException{
}
