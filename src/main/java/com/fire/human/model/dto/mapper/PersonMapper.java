package com.fire.human.model.dto.mapper;

import com.fire.human.model.Hobby;
import com.fire.human.model.Person;
import com.fire.human.model.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "hobbies", target = "hobby")
    @Mapping(source = "color", target = "favourite_color")
    PersonDTO toDto(Person person);

    List<PersonDTO> toDtoList(List<Person> persons);

    @Mapping(source = "favourite_color", target = "color")
    @Mapping(source = "hobby", target = "hobbies")
    Person toEntity(PersonDTO personDTO);

}
