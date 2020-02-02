package com.fire.human.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    private Long id;

    @NotBlank(message = ">Please enter your first name")
    private String first_name;

    @NotBlank(message = ">Please enter your last name")
    private String last_name;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private String favourite_color;

    private List<String> hobby = new ArrayList<>();

}
