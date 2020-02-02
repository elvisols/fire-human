package com.fire.human.service;

import com.fire.human.exception.PersonNotFoundException;
import com.fire.human.model.Person;
import com.fire.human.model.dto.NewPersonDTO;
import com.fire.human.model.dto.PersonDTO;
import com.fire.human.model.dto.mapper.NewPersonMapper;
import com.fire.human.model.dto.mapper.PersonMapper;
import com.fire.human.repository.HobbyRepository;
import com.fire.human.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final HobbyRepository hobbyRepository;
//    private final BCryptPasswordEncoder encoder;
    private final PersonMapper personMapper;
    private final NewPersonMapper newPersonMapper;

    public PersonServiceImpl(NewPersonMapper newPersonMapper, PersonMapper personMapper, PersonRepository personRepository, HobbyRepository hobbyRepository) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.newPersonMapper = newPersonMapper;
        this.hobbyRepository = hobbyRepository;
    }

    @Override
    public PersonDTO save(NewPersonDTO personDTO) {
        log.info("Saving a new PersonDTO...{}", personDTO);

        Person person = newPersonMapper.toEntity(personDTO);

//        person.setPassword(encoder.encode(person.getPassword()));

        log.info("Saving Person...{} Hobbies: {}", person, person.getHobbies());

        hobbyRepository.saveAll(person.getHobbies());

        person = personRepository.save(person);

        return personMapper.toDto(person);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        log.debug("Updating Person: {}", personDTO);

        Person person = personMapper.toEntity(personDTO);

        Optional<Person> p = this.findById(person.getId());

        if(p.isPresent()) {
            Person tmp = p.get();
            tmp.setFirst_name(person.getFirst_name() == null ? tmp.getFirst_name() : person.getFirst_name());
            tmp.setLast_name(person.getLast_name() == null ? tmp.getLast_name() : person.getLast_name());
            tmp.setAge(person.getAge() == null ? tmp.getAge() : person.getAge());
            tmp.setColor(person.getColor() == null ? tmp.getColor() : person.getColor());
            tmp.setHobbies(person.getHobbies() == null ? tmp.getHobbies() : person.getHobbies());
            person = tmp;
        } else {
            throw new PersonNotFoundException("Id["+person.getId()+"] not found.");
        }

        log.info("Updating Person ... {}", person);

        hobbyRepository.saveAll(person.getHobbies());

        person = personRepository.save(person);

        return personMapper.toDto(person);
    }

    @Override
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Persons");

        return personRepository.findAll(pageable)
                .map(personMapper::toDto);
    }

    @Override
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person: {}", id);

        return personRepository.findById(id)
                .map(personMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person: {}", id);

        personRepository.deleteById(id);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

}
