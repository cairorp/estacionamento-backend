package br.com.cairo.estacionamento.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VagaException extends RuntimeException {

    public VagaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VagaException(Throwable cause) {
        super(cause);
    }
}
