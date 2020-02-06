package com.fire.human.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private Long id;

    @NotBlank(message = "*Please enter your first name")
    private String first_name;

    @NotBlank(message = "*Please enter your last name")
    private String last_name;

    @NotNull(message = "*Please enter your age")
    @Digits(message = "*Your age must be an integer value", integer = 10, fraction = 0)
    private Integer age;

    @NotBlank(message = "*Please select your favourite colour.")
    @Enumerated(value = EnumType.STRING)
    private String favourite_color;

    @NotEmpty(message = "*You forgot to tell us your hobby.")
    // TODO: @annotate this field to prevent empty values sent from the client!
    private List<String> hobby = new ArrayList<>();

}
