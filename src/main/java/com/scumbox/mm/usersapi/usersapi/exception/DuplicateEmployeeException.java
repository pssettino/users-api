package com.scumbox.mm.usersapi.usersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee must be unique")
public class DuplicateEmployeeException extends RuntimeException { }
