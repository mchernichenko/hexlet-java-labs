package exercise;

import exercise.db.DBServerInstance;
import io.ebean.EbeanServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;
import io.ebean.Transaction;

import exercise.domain.User;
import exercise.domain.query.QUser;

class AppTest {

    private static Javalin app;
    private static String baseUrl;
    private static Transaction transaction;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start(0); // 0 - запуск на случайном порту
        int port = app.port();
        // db = DBServerInstance.getInstance(); // эта штука устаревшая
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void testAddUser() {
        String firstName = "Mikhail";
        String lastName = "Chernichenko";
        String email = "email@mail.ru";
        String password = "123456";

        // POST http://localhost:5000/users?firstName=firstName&lastName=lastName&email=email&password=123456
        // Response: redirect("/users")
        HttpResponse<?> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asEmpty();
        assertThat(responsePost.getStatus()).isEqualTo(302);
        assertThat(responsePost.getHeaders().getFirst("Location")).isEqualTo("/users");

        // т.к. в ответе после добавления идёт redirect("/users"), то успешное добавление можно проверить только по БД
        // получаем из БД пользователя с добавленным именем. Если он есть, значит пользователь добавлен успешно
        User actualUser = new QUser()
                .firstName.equalTo(firstName)
                .findOne();
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(firstName);
        assertThat(actualUser.getLastName()).isEqualTo(lastName);
        assertThat(actualUser.getEmail()).isEqualTo(email);
        assertThat(actualUser.getPassword()).isEqualTo(password);
    }

    @Test
    void testAddUserNegative() {
        String firstName = "";
        String lastName = "";
        String email = "email#mail.ru";
        String password = "123";

        // POST http://localhost:5000/users?firstName=firstName&lastName=lastName&email=email&password=123456
        // Response: redirect("/users")
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);

        // проверяем сообщения о невалидности каждого из переданых параметров
        String body = response.getBody();
        assertThat(body).contains("имя не должно быть пустым");
        assertThat(body).contains("Фамилия не должна быть пустой");
        assertThat(body).contains("Должно быть валидным email");
        assertThat(body).contains("Пароль должен содержать не менее 4 символов");

        // проверяем, что в БД пользователь не добавился
        User actualUser = new QUser()
                .firstName.equalTo(firstName)
                .findOne();
        assertThat(actualUser).isNull();
    }
    // END

    // Хорошей практикой является запуск тестов с базой данных внутри транзакции.
    // Перед каждым тестом транзакция открывается,
    @BeforeEach
    void beforeEach() {
        transaction = DB.beginTransaction();
    }

    // А после окончания каждого теста транзакция откатывается
    // Таким образом после каждого теста база данных возвращается в исходное состояние,
    // каким оно было перед началом транзакции.
    // Благодаря этому тесты не влияют друг на друга
    @AfterEach
    void afterEach() {
        transaction.rollback();
    }

    @Test
    void testRoot() {
        HttpResponse<String> response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что на станице есть определенный текст
        assertThat(content).contains("Wendell Legros");
        assertThat(content).contains("Larry Powlowski");
    }

    @Test
    void testUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/5")
            .asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Rolando Larson");
        assertThat(content).contains("galen.hickle@yahoo.com");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @AfterAll
    static void afterAll() {
        app.stop();
    }
    // END
}
