package pl.sda.converter.exceptions;

import java.io.IOException;

public class FileConverterException extends RuntimeException {
    public FileConverterException(String msg) {
        super(msg);
    }

    public FileConverterException(String msg, Throwable e) {
        super(msg, e);
    }
}