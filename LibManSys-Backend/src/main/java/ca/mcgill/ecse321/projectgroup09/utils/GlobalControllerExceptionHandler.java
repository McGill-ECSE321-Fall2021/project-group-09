package ca.mcgill.ecse321.projectgroup09.utils;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Used to catch exceptions for all controllers.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	/**
	 * Catch MissingServletRequestParameter Exception and return missing request 
	 * param error message instead of generic BAD_REQUEST message.
	 * @param ex
	 * @return 
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
	    String missingParamName = ex.getParameterName();
	    String type = ex.getParameterType();
	    String errorMessage = "Required request parameter '" + missingParamName + "' for method parameter type <" + type + "> is not present";
	    return httpFailureMessage(errorMessage);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleWrongParamType(MethodArgumentTypeMismatchException ex) {
		return httpFailureMessage(ex.getMessage());
	}
}
