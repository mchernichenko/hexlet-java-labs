package exercise.domain;

import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

// Указываем, что класс является моделью
@Entity
public class Category extends Model {

    // Указываем, что поле является первичным ключом
    // Значение будет генерироваться автоматически
    @Id
    private long id;

    private String name;

    // Задаём связь "один ко многим"
    // Указываем тип данных Article для свойства
    // Тем самым указываем связанную сущность
    // CascadeType.ALL — означает, что операция, например, записи должна распространяться и на дочерние таблицы
    @OneToMany(
            cascade = CascadeType.ALL
    )
    private List<Article> articles;

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Article> getArticles() {
        return this.articles;
    }
}
