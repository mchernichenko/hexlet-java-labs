package exercise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, String> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        LOGGER.info("->>> createPerson <<<- request: " + query);
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping("")
    public List<Map<String, Object>> showPersons(HttpServletRequest request) {
        LOGGER.info("->>> URL: " + request.getMethod() + " " + request.getRequestURI());

        String query = "SELECT id, first_name, last_name FROM person";
        LOGGER.info("->>> showPersons <<<- request: " + query);

        // Map<String, Object> представляет одну строку таблицы, где key=<ИмяСтолбца>, value=<ЗначениеПоля>
        return jdbc.queryForList(query);
    }

    @GetMapping("/{id}")
    public List<Map<String, Object>> showPerson(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("->>> URL: " + request.getMethod() + " " + request.getRequestURI());

        String query = "SELECT id, first_name, last_name FROM person WHERE id = ?";
        LOGGER.info(String.format("->>> showPerson <<<- request: %s, id=%s", query, id));

        // Map<String, Object> представляет одну строку таблицы, где key=<ИмяСтолбца>, value=<ЗначениеПоля>
        return jdbc.queryForList(query, id);
    }
    // END
}
