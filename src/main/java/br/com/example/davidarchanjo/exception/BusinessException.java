package br.com.example.davidarchanjo.exception;

import org.springframework.http.*;

public class /**/BusinessException extends RuntimeException {
    public final HttpStatus status;
    public final String code;

    public BusinessException(HttpStatus s, String c, String m) {
        super(m);
        status = s;
        code = c;
    }
}
