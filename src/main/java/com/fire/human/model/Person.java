package com.fire.human.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "persons", schema = "human_db")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter your first name")
    private String first_name;

    @NotBlank(message = "Please enter your last name")
    private String last_name;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String email;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private EColor color;

    @NotBlank(message = "Password field is required")
    private String password;

    private Date created;
    private Date modified;

    @ManyToMany
    @JoinTable(name = "person_hobby", schema = "human_db", joinColumns = {
            @JoinColumn(name = "person_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "hobby_id", referencedColumnName = "keyword")
            }
    )
    private List<Hobby> hobbies = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
    }

    @PreUpdate()
    protected void onUpdate() {
        this.modified = new Date();
    }

}
