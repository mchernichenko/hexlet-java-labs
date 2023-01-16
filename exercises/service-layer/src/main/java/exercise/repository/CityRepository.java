package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    // поиск городов, имя которых начинается с указанных символов без учета регистра
    List<City> findCityByNameIsStartingWithIgnoreCase(String name);

    // поиск всех городов, отсортированныхпо имени в прямом порядке
    List<City> findAllByOrderByNameAsc();
    // END
}
