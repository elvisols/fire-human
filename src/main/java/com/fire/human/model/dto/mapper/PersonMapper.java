package com.fire.human.model.dto.mapper;

import com.fire.human.model.Hobby;
import com.fire.human.model.Person;
import com.fire.human.model.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
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

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }

    default List<String> mapFromHobby(List<Hobby> hobbies) {
        List<String> flattenHobbies = new ArrayList<>(hobbies.size());

        for(Hobby hobby: hobbies) {
            flattenHobbies.add(hobby.getKeyword());
        }
        return flattenHobbies;
    }

    default List<Hobby> mapFromString(List<String> flattenHobbies) {
        List<Hobby> hobbies = new ArrayList<>(flattenHobbies.size());

        for(String hobby: flattenHobbies) {
            hobbies.add(new Hobby(hobby));
        }
        return hobbies;
    }

}
