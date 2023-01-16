package exercise.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;

// BEGIN
@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    // Поле может иметь большое содержимое, например текст
    @Lob
    private String body;

    // Статья может относиться только к одной категории, но к одной категории может относиться несколько статей
    @ManyToOne
    private Category category;
}
// END
