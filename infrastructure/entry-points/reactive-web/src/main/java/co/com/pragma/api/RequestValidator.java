package co.com.pragma.api;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestValidator {
    private final Validator validator;

    public <T> Mono<T> validate(T dto) {
        return Mono.fromCallable(() -> {
            Set<ConstraintViolation<T>> violations = validator.validate(dto);
            if (!violations.isEmpty()) {
                StringBuilder sb = new StringBuilder("Validation errors: ");
                violations.forEach(v ->
                        sb.append(v.getPropertyPath())
                                .append(" ")
                                .append(v.getMessage())
                                .append("; ")
                );
                throw new ValidationException(sb.toString());
            }
            return dto;
        });
    }
}
