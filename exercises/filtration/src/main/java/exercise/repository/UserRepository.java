package exercise.repository;

import com.querydsl.core.types.dsl.StringExpression;
import exercise.model.User;
import exercise.model.QUser;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
    JpaRepository<User, Long>,
    QuerydslPredicateExecutor<User>,   // предоставляет доступ к определенным методам фильтрации
    QuerydslBinderCustomizer<QUser> {  // расширение для автогенерации предиката

    // переопределение предиката по умолчанию
    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        // Дополнительная задача

        // BEGIN
        bindings.bind(user.firstName).first((StringExpression::containsIgnoreCase));
        bindings.bind(user.lastName).first((StringExpression::containsIgnoreCase));
        bindings.bind(user.email).first((StringExpression::containsIgnoreCase));
        bindings.bind(user.profession).first((StringExpression::equalsIgnoreCase));
        // END
    }

}
