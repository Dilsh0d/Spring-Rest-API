package com.backend.tasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Dilsh0d Tadjiev on 05.03.2019 13:09.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Object not found")
public class NotFound404Exception extends RuntimeException {
}
