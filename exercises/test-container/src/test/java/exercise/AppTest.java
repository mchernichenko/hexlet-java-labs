package exercise;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {
    private static final String BASE_URL = "/people";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonRepository personRepository;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Test
    void testGetPeople() throws Exception {
        MockHttpServletRequestBuilder request = get(BASE_URL);
        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("John", "Jack", "Jassica", "Robert");
    }

    @Test
    void testGetPerson() throws Exception {
        MockHttpServletRequestBuilder request = get(BASE_URL + "/{id}", 1);
        var response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("John");
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(
                        delete(BASE_URL + "/{id}", 1))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(personRepository.existsById(1L)).isFalse();
    }

    @Test
    void testUpdatePerson() throws Exception {

        MockHttpServletRequestBuilder updateRequest = patch(BASE_URL + "/{id}", 1)
                .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
                .contentType(APPLICATION_JSON);

        mockMvc.perform(updateRequest)
                .andExpect(status().isOk());

        Person actualPerson = personRepository.findById(1L);
        assertThat(actualPerson).isNotNull();
        assertThat(actualPerson.getFirstName()).isEqualTo("Jackson");
    }

    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
