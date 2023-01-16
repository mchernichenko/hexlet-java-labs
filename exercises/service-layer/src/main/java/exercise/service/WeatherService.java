package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map <String, String> getWeatherByCityName(String cityName) {

        String URL = "http://weather/api/v2/cities/" + cityName;
        String response = client.get(URL);
        Map <String, String> resultInfo = new HashMap<>();
        try {
            resultInfo = MAPPER.readValue(response, new TypeReference<Map<String,String>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    public List<Map <String, String>> getWeatherCities(List<City> cityList) {
        String n = "name";
        String t = "temperature";

        return cityList.stream()
                .map(x -> {
                    Map<String, String> map = getWeatherByCityName(x.getName());
                    return Map.of(n, map.get(n), t, map.get(t));
                }).collect(Collectors.toList());
    }
    // END
}
