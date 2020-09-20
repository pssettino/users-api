package com.scumbox.mm.usersapi.usersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Error when trying to store a document")
public class DocumentException extends RuntimeException { }
