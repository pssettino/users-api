package com.scumbox.mm.usersapi.usersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "File too large!")
public class MaxSizeException extends MaxUploadSizeExceededException {
    public MaxSizeException(long maxUploadSize) {
        super(maxUploadSize);
    }

    public MaxSizeException(long maxUploadSize, @Nullable Throwable ex) {
        super(maxUploadSize, ex);
    }
}
