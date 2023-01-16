package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class AppTest {

    private static ArrayList<HashMap<String, String>> books = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        HashMap<String, String> book1 = new HashMap<>(
            Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
        );
        HashMap<String, String> book2 = new HashMap<>(
            Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
        );
        HashMap<String, String> book3 = new HashMap<>(
            Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );
        HashMap<String, String> book4 = new HashMap<>(
            Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
        );
        HashMap<String, String> book5 = new HashMap<>(
            Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );
        HashMap<String, String> book6 = new HashMap<>(
            Map.of("title", "Happy Foo", "author", "FooBar", "year", "4444")
        );
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
    }

    @Test
    void testFindWhere1() {

        ArrayList<HashMap<String, String>> expected = new ArrayList<>();
        expected.add(new HashMap<>(
            Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
        ));
        expected.add(new HashMap<>(
            Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        ));

        HashMap<String, String> where = new HashMap<>(
            Map.of("author", "Shakespeare", "year", "1611")
        );
        ArrayList<HashMap<String, String>> actual = App.findWhere(books, where);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testFindWhere2() {

        ArrayList<HashMap<String, String>> expected = new ArrayList<>();
        expected.add(new HashMap<>(
            Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        ));

        HashMap<String, String> where = new HashMap<>(
            Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );
        ArrayList<HashMap<String, String>> actual = App.findWhere(books, where);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testFindWhere3() {
        HashMap<String, String> where = new HashMap<>(
            Map.of("title", "Still foooing", "author", "FooBar", "year", "4444")
        );
        ArrayList<HashMap<String, String>> actual = App.findWhere(books, where);
        assertThat(actual).isEmpty();
    }
}
