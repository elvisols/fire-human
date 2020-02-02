package com.fire.human.model.dto.mapper;

import com.fire.human.model.Person;
import com.fire.human.model.dto.NewPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface NewPersonMapper extends EntityMapper<NewPersonDTO, Person> {

    @Mapping(source = "hobbies", target = "hobby")
    @Mapping(source = "email", target = "username")
    @Mapping(source = "color", target = "favourite_color")
    NewPersonDTO toDto(Person person);

    List<NewPersonDTO> toDtoList(List<Person> persons);

    @Mapping(source = "favourite_color", target = "color")
    @Mapping(source = "username", target = "email")
    @Mapping(source = "hobby", target = "hobbies")
    Person toEntity(NewPersonDTO personDTO);

}
