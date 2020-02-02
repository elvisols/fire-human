package com.fire.human.service;

import com.fire.human.model.dto.NewPersonDTO;
import com.fire.human.model.dto.PersonDTO;
import com.fire.human.model.dto.mapper.PersonMapper;
import com.fire.human.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
//    private final BCryptPasswordEncoder encoder;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public PersonDTO save(NewPersonDTO personDTO) {
        return null;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        return null;
    }

    @Override
    public Page<PersonDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PersonDTO> findOne(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }

}
