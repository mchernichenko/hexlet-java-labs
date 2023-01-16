package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String HelloSpring() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello") // /hello?name=John
    public String HelloName(@RequestParam("name") Optional<String> name) {
        return name.map(s -> String.format("Hello, %s!", s)).orElse("Hello, World!");
    }
}
// END
