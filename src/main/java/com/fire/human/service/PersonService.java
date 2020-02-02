package com.fire.human.service;

import com.fire.human.model.dto.NewPersonDTO;
import com.fire.human.model.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface PersonService {

    /**
     * Create new person entity
     */
    PersonDTO save(NewPersonDTO personDTO);

    /**
     * Update person entity
     */
    PersonDTO update(PersonDTO personDTO);

    /**
     * Get all the persons.
     */
    Page<PersonDTO> findAll(Pageable pageable);

    /**
     * Get the person by Id.
     */
    Optional<PersonDTO> findOne(Long id);

    /**
     * Delete person by Id.
     */
    void delete(Long id);

}
