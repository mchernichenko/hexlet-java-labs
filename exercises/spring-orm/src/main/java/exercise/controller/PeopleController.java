package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public String postPerson(@RequestBody Person person) {
        personRepository.save(person);
        return String.format("Person %s has been saved successfully", person.getLastName());
    }

    @DeleteMapping(path = "/{id}")
    public String deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
        return String.format("Person with id=%d has been deleted successfully", id);
    }

    @PatchMapping(path = "/{id}")
    public String patchPerson(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        // метод save сохраняет сущность, либо обновляет, если уже существует сущность с указанным id
        personRepository.save(person);
        return String.format("Person with id=%d has been updated successfully", id);
    }
    // END
}
