package ec.com.leo.bank.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LeoBankUtil {

    private LeoBankUtil() {}

    public static ResponseEntity<?> checkErrors(BindingResult result, Map<String, Object> response) {
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> {return "El campo '"+err.getField()+"' "+err.getDefaultMessage();})
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
