package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// BEGIN
@Entity
@Table(name = "PERSON")
@Getter
@Setter
// END
public class Person {

    // BEGIN
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    // END
}
