package exercise;

import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // BEGIN
        User user = repository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        String userRole = user.getRole().name();

        // Создаём список полномочий, которым будет обладать пользователь
        // GrantedAuthority — это интерфейс для доступов пользователя.
        // Одна из его имплементаций — SimpleGrantedAuthority в которую можно добавить только имя роли и
        // Spring будет давать доступ если имя роли будет совпадать с именем роли в методе hasRole
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userRole));

        // Создаём новый объект org.springframework.security.core.userdetails.User
        // Передаём туда логин (в данном случае почта), пароль и список полномочий
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities
        );
        // END
    }
}
