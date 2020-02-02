package com.fire.human.model.dto.mapper;

import com.fire.human.model.Hobby;
import com.fire.human.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);

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