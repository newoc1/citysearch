package grok.citysearch.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import grok.citysearch.controller.dto.ErrorResponse;
import grok.citysearch.user.NoCommodityException;

@ControllerAdvice
public class ExceptionMapper {

	 	@ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(NoCommodityException.class)
	 	@ResponseBody
	    public ErrorResponse handleConflict() {
	        return new ErrorResponse("You don't have that commodity.");
	    }
}
