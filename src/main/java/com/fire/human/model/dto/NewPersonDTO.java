package com.fire.human.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewPersonDTO extends PersonDTO {

    @Email(message = ">Username needs to be an email")
    @NotBlank(message = ">Username is required")
    private String username;

    @NotBlank(message = ">Password field is required")
    private String password;

    @JsonIgnore
    private String confirmPassword;

}
