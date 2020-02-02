package com.fire.human.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "hobbies")
class Hobby {

    @Id
    private String keyword;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

}
