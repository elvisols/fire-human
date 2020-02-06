package com.fire.human.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmptyListValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyList {
    String message() default "Empty list value.";
    String description() default "...";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
