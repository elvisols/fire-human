package com.fire.human.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<String>> {

    @Override
    public void initialize(NotEmptyList list) {
    }

    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext cxt) {
        return list != null && (list.size() > 0) && (list.stream().anyMatch((s -> !s.trim().isEmpty())));
    }

}