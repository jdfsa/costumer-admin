package br.com.jdfs.customer.customeradmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class WheatherDataNotAvailableException extends RuntimeException {

    public WheatherDataNotAvailableException(String message) {
        super(message);
    }
}
