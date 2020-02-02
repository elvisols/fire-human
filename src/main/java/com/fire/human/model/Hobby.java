package com.fire.human.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "hobbies", schema = "human_db")
public class Hobby {

    @Id
    private String keyword;

    private String description = "n/a";

    public Hobby(String keyword) {
        this.keyword = keyword;
    }

}
