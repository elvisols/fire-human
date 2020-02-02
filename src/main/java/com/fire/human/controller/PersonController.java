package com.fire.human.controller;


import com.fire.human.exception.PersonNotFoundException;
import com.fire.human.model.EColor;
import com.fire.human.model.dto.NewPersonDTO;
import com.fire.human.model.dto.PersonDTO;
import com.fire.human.service.PersonService;
import com.fire.human.utils.MapValidationErrorHandler;
import com.fire.human.utils.NewPersonValidator;
import com.fire.human.utils.ResponseWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private MapValidationErrorHandler mapValidationErrorHandler;

    @Autowired
    private PersonService personService;

    @Autowired
    private NewPersonValidator newPersonValidator;

//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorHandler.MapValidationService(result);
        if(errorMap != null) return errorMap;

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);
//
//        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAllPersons(Pageable pageable) {
        Page<PersonDTO> personDTOs = personService.findAll(pageable);

        return new ResponseEntity<ResponseWrapper>(new ResponseWrapper(personDTOs), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        Optional<PersonDTO> personDTO = personService.findOne(id);

        if (!personDTO.isPresent()) {
            throw new PersonNotFoundException("Person not found for id: " + id);
        }

        return personDTO.get();
    }


    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody NewPersonDTO person, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorHandler.MapValidationService(result);
        if(errorMap != null)return errorMap;

        // Validate passwords match
        newPersonValidator.validate(person,result);

        PersonDTO newUser = personService.save(person);

        return  new ResponseEntity<PersonDTO>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PersonDTO person, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = mapValidationErrorHandler.MapValidationService(bindingResult);

        if(errorMap != null) return errorMap;

        person.setId(id);
        PersonDTO p = personService.update(person);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);

        return new ResponseEntity<String>("Project '"+id+"' deleted", HttpStatus.OK);
    }

}

@Getter @Setter
class LoginRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

}
