package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Getter
@AllArgsConstructor
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String serialize() throws JsonProcessingException {
        return MAPPER.writeValueAsString(this);
    }

    public static Car unserialize(String json) throws IOException {
        return MAPPER.readValue(json, Car.class);
    }
    // END
}
