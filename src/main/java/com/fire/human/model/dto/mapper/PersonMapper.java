package com.fire.human.model.dto.mapper;

import com.fire.human.model.Person;
import com.fire.human.model.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {String.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "hobbies", target = "hobby")
    @Mapping(source = "email", target = "username")
    @Mapping(source = "color", target = "favourite_color")
    PersonDTO toDto(Person person);

    List<PersonDTO> toDtoList(List<Person> persons);

    @Mapping(source = "favourite_color", target = "color")
    @Mapping(source = "username", target = "email")
    @Mapping(source = "hobby", target = "hobbies")
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }

}
