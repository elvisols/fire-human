package com.fire.human.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "hobbies")
public class Hobby {

    @Id
    private String keyword;

    private String description;

    public Hobby(String keyword) {
        this.keyword = keyword;
    }

}
