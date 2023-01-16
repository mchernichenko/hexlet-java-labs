package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Map <String, String> getWeatherByCityId(@PathVariable("id") long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException());

        return weatherService.getWeatherByCityName(city.getName());
    }

    @GetMapping("/search")
    public List<Map<String,String>> getCities(@RequestParam(required = false) String name) {
        List<City> cityList;

        if (name != null) {
            cityList = cityRepository.findCityByNameIsStartingWithIgnoreCase(name);
        } else {
            cityList = cityRepository.findAllByOrderByNameAsc();
        }
        if (cityList.isEmpty()) {
            throw new CityNotFoundException();
        }
        return weatherService.getWeatherCities(cityList);
    }
    // END
}

