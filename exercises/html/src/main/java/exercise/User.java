package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
