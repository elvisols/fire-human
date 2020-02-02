package com.fire.human.service;

import com.fire.human.model.Person;
import com.fire.human.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(username);
        if(person==null) new UsernameNotFoundException("Person not found");
        return person;
    }

    @Transactional
    public Person loadUserById(Long id) {
        Person person = personRepository.getOne(id);
        if(person==null) new UsernameNotFoundException("Person not found");
        return person;
    }
}
