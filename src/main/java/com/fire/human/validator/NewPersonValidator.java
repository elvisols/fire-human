package com.fire.human.validator;

import com.fire.human.model.dto.NewPersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewPersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NewPersonDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        NewPersonDTO person = (NewPersonDTO) object;

        if (person.getPassword() != null && person.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");
        }

        if (person.getPassword() != null && !person.getPassword().equals(person.getConfirm_password())) {
            errors.rejectValue("confirm_password", "Match", "Passwords must match");
        }

    }
}
