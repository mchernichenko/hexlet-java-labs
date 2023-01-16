package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
