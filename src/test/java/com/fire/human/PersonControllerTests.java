package com.fire.human;


import com.fire.human.model.EColor;
import com.fire.human.model.dto.NewPersonDTO;
import com.fire.human.model.dto.PersonDTO;
import com.fire.human.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets", uriHost = "localhost", uriPort = 8899)
public class PersonControllerTests {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mvc;

    @Value("${test.jwt.token}")
    private String token;

    @Test
    public void getPersonsTest() throws Exception {

        List<String> hobbies1 = Arrays.asList("shopping", "football");
        PersonDTO personDTO1 = new PersonDTO(1L, "John", "Smith", 29, "red", hobbies1);

        List<String> hobbies2 = Arrays.asList("chess");
        PersonDTO personDTO2 = new PersonDTO(2L, "Sarah", "Conor", 54, "blue", hobbies2);

        given(this.personService.findAll(any(Pageable.class))).willReturn(new PageImpl<>(Arrays.asList(personDTO1, personDTO2)));

        this.mvc.perform(get("/api/persons").param("size", "20").param("page", "0")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.person", Matchers.hasSize(2)))
                .andDo(
                        document("{class-name}/{method-name}",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("size").description("Size of the returned payload").optional(),
                                        parameterWithName("page").description("Payload page number").optional()
                                ),
                                responseFields(
                                        fieldWithPath("person[].id").description("Person auto generated ID").type(JsonFieldType.NUMBER),
                                        fieldWithPath("person[].first_name").description("Registered person first name").type(JsonFieldType.STRING),
                                        fieldWithPath("person[].last_name").description("Registered person last name").type(JsonFieldType.STRING),
                                        fieldWithPath("person[].age").description("Registered person age").type(JsonFieldType.NUMBER),
                                        fieldWithPath("person[].favourite_color").description("Registered person favourite color").type(JsonFieldType.STRING),
                                        fieldWithPath("person[].hobby").description("List of person's hobbies").type(JsonFieldType.ARRAY)
                                )
                        )
                );
        verify(personService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void getPersonTest() throws Exception {

        List<String> hobbies1 = Arrays.asList("shopping", "football");
        PersonDTO personDTO1 = new PersonDTO(1L, "John", "Smith", 29, "red", hobbies1);

        given(this.personService.findOne(anyLong())).willReturn(Optional.of(personDTO1));

        this.mvc.perform(get("/api/persons/{id}", 1)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.first_name", Matchers.is("John")))
                .andExpect(jsonPath("$.last_name", Matchers.is("Smith")))
                .andExpect(jsonPath("$.age", Matchers.is(29)))
                .andExpect(jsonPath("$.favourite_color", Matchers.is("red")))
                .andExpect(jsonPath("$.hobby", Matchers.hasSize(2)))
                .andDo(
                        document("{class-name}/{method-name}",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("id").description("Person's registered ID")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Person auto generated ID").type(JsonFieldType.NUMBER),
                                        fieldWithPath("first_name").description("Registered person first name").type(JsonFieldType.STRING),
                                        fieldWithPath("last_name").description("Registered person last name").type(JsonFieldType.STRING),
                                        fieldWithPath("age").description("Registered person age").type(JsonFieldType.NUMBER),
                                        fieldWithPath("favourite_color").description("Registered person favourite color").type(JsonFieldType.STRING),
                                        fieldWithPath("hobby").description("List of person's hobbies").type(JsonFieldType.ARRAY)
                                )
                        )
                );
        verify(personService, times(1)).findOne(anyLong());
    }

    @Test
    public void storePersonTest() throws Exception {

        List<String> hobbies1 = Arrays.asList("ps4", "music");
        PersonDTO personDTO1 = new PersonDTO(3L, "Ben", "Foe", 25, "blue", hobbies1);

        String person = "{\n" +
                "    \"first_name\": \"Ben\",\n" +
                "    \"last_name\": \"Foe\",\n" +
                "    \"age\": 25,\n" +
                "    \"favourite_color\": \"blue\",\n" +
                "    \"username\": \"bfoe@fire.com\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"confirm_password\": \"password\",\n" +
                "    \"hobby\": [\n" +
                "        \"tennis\", \"ps4\", \"music\"\n" +
                "    ]\n" +
                "}";

        given(this.personService.save(any())).willReturn(personDTO1);

        ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(NewPersonDTO.class);

        this.mvc.perform(post("/api/persons")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(person)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("{class-name}/{method-name}",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("first_name").description("Person first name")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("first_name"))),
                                        fieldWithPath("last_name").description("Person last name")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("last_name"))),
                                        fieldWithPath("username").description("Person login email address")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("username"))),
                                        fieldWithPath("password").description("Person login password")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("password"))),
                                        fieldWithPath("confirm_password").description("Person login confirmation password")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("confirm_password"))),
                                        fieldWithPath("age").description("Person age")
                                                .type(JsonFieldType.NUMBER)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("age"))),
                                        fieldWithPath("favourite_color").description("Person favourite color... " + Stream.of(EColor.values()).map(Enum::name).map(String::toLowerCase).collect(Collectors.toList()))
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("favourite_color"))),
                                        fieldWithPath("hobby").description("List of person's hobbies")
                                                .type(JsonFieldType.ARRAY)
                                                .optional()
                                                .attributes(key("constraints").value("Not empty"))
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Person auto generated ID").type(JsonFieldType.NUMBER),
                                        fieldWithPath("first_name").description("Registered person first name").type(JsonFieldType.STRING),
                                        fieldWithPath("last_name").description("Registered person last name").type(JsonFieldType.STRING),
                                        fieldWithPath("age").description("Registered person age").type(JsonFieldType.NUMBER),
                                        fieldWithPath("favourite_color").description("Registered person favourite color").type(JsonFieldType.STRING),
                                        fieldWithPath("hobby").description("List of person's hobbies").type(JsonFieldType.ARRAY)
                                )
                        )
                );
        verify(personService, times(1)).save(any());
    }

    @Test
    public void loginPersonTest() throws Exception {

        String loginRequest = "{\n" +
                            "    \"username\": \"jsmith@fire.com\",\n" +
                            "    \"password\": \"password\"\n" +
                            "}";

        this.mvc.perform(post("/api/persons/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.isA(Boolean.class)))
                .andExpect(jsonPath("$.token", Matchers.startsWith("Bearer")))
                .andDo(
                        document("{class-name}/{method-name}",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("username").description("Login Username")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value("Username cannot be blank")),
                                        fieldWithPath("password").description("Login password")
                                                .type(JsonFieldType.STRING)
                                                .attributes(key("constraints").value("Password cannot be blank"))
                                ),
                                responseFields(
                                        fieldWithPath("status").description("Valid token status").type(JsonFieldType.BOOLEAN),
                                        fieldWithPath("token").description("JWT token value").type(JsonFieldType.STRING)
                                )
                        )
                );
    }

    @Test
    public void loginFailTest() throws Exception {

        String loginRequest = "{\n" +
                            "    \"username\": \"smith@fire.com\",\n" +
                            "    \"password\": \"password\"\n" +
                            "}";

        this.mvc.perform(post("/api/persons/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updatePersonTest() throws Exception {

        List<String> hobbies1 = Arrays.asList("ps4", "music");
        PersonDTO personDTO1 = new PersonDTO(3L, "BenUpdated", "FoeUpdated", 25, "blue", hobbies1);

        String person = "{\n" +
                "    \"first_name\": \"BenUpdated\",\n" +
                "    \"last_name\": \"FoeUpdated\",\n" +
                "    \"age\": 25,\n" +
                "    \"favourite_color\": \"blue\",\n" +
                "    \"hobby\": [\n" +
                "        \"tennis\", \"ps4\", \"music\"\n" +
                "    ]\n" +
                "}";

        given(this.personService.update(any())).willReturn(personDTO1);

        ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(PersonDTO.class);

        this.mvc.perform(put("/api/persons/{id}", 3L)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(person)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("{class-name}/{method-name}",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                    parameterWithName("id").description("Person ID to update")
                                ),
                                requestFields(
                                        fieldWithPath("first_name").description("Person first name")
                                                .type(JsonFieldType.STRING)
                                                .optional()
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("first_name"))),
                                        fieldWithPath("last_name").description("Person last name")
                                                .type(JsonFieldType.STRING)
                                                .optional()
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("last_name"))),
                                        fieldWithPath("age").description("Person age")
                                                .type(JsonFieldType.NUMBER)
                                                .optional()
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("age"))),
                                        fieldWithPath("favourite_color").description("Person favourite color... " + Stream.of(EColor.values()).map(Enum::name).map(String::toLowerCase).collect(Collectors.toList()))
                                                .type(JsonFieldType.STRING)
                                                .optional()
                                                .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("favourite_color"))),
                                        fieldWithPath("hobby").description("List of person's hobbies")
                                                .type(JsonFieldType.ARRAY)
                                                .optional()
                                                .attributes(key("constraints").value("Not Empty"))
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Person auto generated ID").type(JsonFieldType.NUMBER),
                                        fieldWithPath("first_name").description("Registered person first name").type(JsonFieldType.STRING),
                                        fieldWithPath("last_name").description("Registered person last name").type(JsonFieldType.STRING),
                                        fieldWithPath("age").description("Registered person age").type(JsonFieldType.NUMBER),
                                        fieldWithPath("favourite_color").description("Registered person favourite color").type(JsonFieldType.STRING),
                                        fieldWithPath("hobby").description("List of person's hobbies").type(JsonFieldType.ARRAY)
                                )
                        )
                );
        verify(personService, times(1)).update(any());
    }

    @Test
    public void deletePersonTest() throws Exception {
        doNothing().when(personService).delete(anyLong());

        this.mvc.perform(delete("/api/persons/{id}", 3L)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Person deleted! :("))
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("id").description("Person ID to delete")
                            )
                        )
                );

        verify(personService, times(1)).delete(3L);
    }

    @Test
    public void accessDeniedTest() throws Exception {

        this.mvc.perform(get("/api/persons/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}