package kg.attractor.microgram.service;

import kg.attractor.microgram.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

public interface ErrorService {
    ErrorResponseBody makeResponse(NoSuchElementException e);

    ErrorResponseBody makeResponse(Exception e);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
