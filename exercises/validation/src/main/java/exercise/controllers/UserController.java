package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        // передаём этот атрибут, т.е. юзется в new.html при ошибках валидации
        ctx.attribute("errors", Map.of());

        // передаём пустого юзера, т.к. при ошибках валидации, для удобства,
        // сами поля формы должны быть заполнены введенными данными
        ctx.attribute("user", Map.of());

        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        // Создаём объект пользователя до валидации параметров, для передачи его в new.html, либо для создания
        // т.к. для удобства сами поля формы должны быть заполнены введенными данными.
        User user = new User(
                ctx.formParam("firstName"),
                ctx.formParam("lastName"),
                ctx.formParam("email"),
                ctx.formParam("password"));

        // параметр с формы <input ... name="firstName" >  см. new.html
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(value -> !value.isEmpty(), "Имя должно быть заполнено");
        Validator<String> lastnameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(value -> !value.isEmpty(), "Фамилия должна быть заполнена");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(value -> EmailValidator.getInstance().isValid(value), "Введён невалидный email");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(StringUtils::isNumeric, "Пароль должен содержать только цифры")
                .check(value -> value.length() >= 4, "Пароль должен состоять из 4 и более символов");

        // автоматически получить ошибки из нескольких валидаторов и собрать их в словарь
        // ключ — это значение поля, не прошедшего валидацию, а значение — список ошибок.
        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastnameValidator,
                emailValidator,
                passwordValidator);

        if (!errors.isEmpty()) {
            ctx.status(422);                 // ctx.res.sendError(422);
            ctx.attribute("errors", errors); // ctx.req.setAttribute("errors", errors);
            ctx.attribute("user", user);
            ctx.render("users/new.html");
        } else {
            user.save();
            ctx.sessionAttribute("flash", "User has been successful create");
            ctx.redirect("/users");
        }
        // END
    };
}
