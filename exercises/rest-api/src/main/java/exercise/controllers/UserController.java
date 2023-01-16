package exercise.controllers;

import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) { // GET /api/v1/users
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        ctx.json(DB.json().toJson(users));
        // END
    };

    public void getOne(Context ctx, String id) { // GET /api/v1/users/{id}

        // BEGIN
       // int id = Integer.parseInt(ctx.pathParam("id"));
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id)).findOne();
        ctx.json(DB.json().toJson(user));
        // END
    };

    public void create(Context ctx) { // POST /api/v1/users

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(value -> value.getFirstName() != null, "Имя должно быть заполнено")
                .check(value -> value.getLastName() != null, "Фамилия должна быть заполнена")
                .check(value -> EmailValidator.getInstance().isValid(value.getEmail()), "Введён невалидный email")
                .check(value -> StringUtils.isNumeric(value.getPassword()), "Пароль должен содержать только цифры")
                .check(value -> value.getPassword().length() >= 4, "Пароль должен состоять из 4 и более символов")
                .get();

        user.save();

//        String user = ctx.body();
//        DB.json().toBean(User.class, user).save();
        // END
    };

    public void update(Context ctx, String id) { // PUT /api/v1/users/{id}
        // BEGIN
        String json = ctx.body();
        User user = DB.json().toBean(User.class, json);
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) { // DELETE /api/v1/users/{id}
        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id)).findOne();
        user.delete();
        // END
    };
}
