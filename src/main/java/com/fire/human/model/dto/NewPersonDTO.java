package com.fire.human.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewPersonDTO extends PersonDTO {

    @Email(message = "*Username needs to be an email")
    @NotBlank(message = "*Username is required")
    private String username;

    @NotBlank(message = "*Password is required")
    private String password;

    @NotBlank(message = "*Confirmation Password is required")
    private String confirm_password = null;

}
