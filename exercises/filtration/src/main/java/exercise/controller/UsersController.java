package exercise.controller;

import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import liquibase.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsers(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        QUser user = QUser.user;
        List<BooleanExpression> expressionList = new ArrayList<>(); // набор условий (предикатов)

        if (firstName != null) {
            expressionList.add(user.firstName.containsIgnoreCase(firstName));
        }
        if (lastName != null) {
            expressionList.add(user.lastName.containsIgnoreCase(lastName));
        }

        // связываем все предикаты по условию 'and' и получаем окончательное условие запроса 'WHERE'
        BooleanExpression predicate = expressionList.stream()
                .reduce(null, (p1,p2) -> {
                    if (p1 == null) {
                        return p2;
                    }
                    return p1.and(p2);
                });
        // если условий нет, то выводим всех пользователей
        return (predicate != null) ? userRepository.findAll(predicate) : userRepository.findAll();
    }

    // Вариант генерации предиката сразу из параметров запроса
    //
    @GetMapping("/v2")
    public Iterable<User> getFilteredUsers(
            @QuerydslPredicate(root = User.class) Predicate predicate) {
        return userRepository.findAll(predicate);
    }


    // END
}

