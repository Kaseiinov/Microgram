package kg.attractor.microgram.service.impl;


import kg.attractor.microgram.exceptions.ErrorResponseBody;
import kg.attractor.microgram.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(NoSuchElementException e){
        String msg = e.getMessage();
        return ErrorResponseBody
                .builder()
                .title(msg)
                .response(Map.of("Errors", List.of(msg)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(Exception e) {
        String msg = e.getMessage();
        return ErrorResponseBody
                .builder()
                .title(msg)
                .response(Map.of("Errors", List.of(msg)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult){
        Map<String, List<String>> response = new HashMap<>();
        bindingResult.getFieldErrors()
                .stream()
                .filter(err -> err.getDefaultMessage() != null)
                .forEach(err -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(err.getDefaultMessage());
                    if(!response.containsKey(err.getField())) {
                        response.put(err.getField(), errors);
                    }

                });

        return ErrorResponseBody
                .builder()
                .title("Validation Error")
                .response(response)
                .build();
    }
}
