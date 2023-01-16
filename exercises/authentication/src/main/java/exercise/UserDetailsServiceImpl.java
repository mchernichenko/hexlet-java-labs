package exercise;

//import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Spring Security читает хидер Authorization, вычленяет из него значение логина и передаёт его в метод
    // Как интерпретировать значение Authorization зависит от типа авторизации, определённой в конфигурации WebSecurityConfig
    // В качестве логина может быть имя пользователя или его e-mail.
    // В нашем случае аутентификация идёт по имени пользователя, то ищем пользователя по имени
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Список полномочий, которые будут предоставлены пользователю после аутентификации
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));

        // BEGIN
        // т.к. аутентификация идёт по имени пользователя, то ищем пользователя по имени и ругаемся, если не находим
        exercise.model.User user = repository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        String password = user.getPassword();

        // Внимание: возвращаем  User из org.springframework.security.core, а не из модели
        // именно этот User реализует UserDetails
        return new User(username, password, authorities);
        // END
    }
}
