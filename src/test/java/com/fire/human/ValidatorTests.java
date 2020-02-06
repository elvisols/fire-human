package com.fire.human;

import com.fire.human.validator.NotEmptyListValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Not Empty List Validation Unit Test
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidatorTests {

    @Mock
    private ConstraintValidatorContext ctx;

    private NotEmptyListValidator notEmptyListValidator = new NotEmptyListValidator();

    @Test
    public void should_fail_when_person_hobby_list_is_empty() {
        List<String> hobby = new ArrayList<>();
        hobby.add(" ");
        hobby.add("");

        boolean response = notEmptyListValidator.isValid(hobby, ctx);
        assertFalse(response);
    }

    @Test
    public void should_pass_when_person_hobby_list_is_not_empty() {
        List<String> hobby = new ArrayList<>();
        hobby.add(" ");
        hobby.add("hi");

        boolean response = notEmptyListValidator.isValid(hobby, ctx);
        assertTrue(response);
    }
}
