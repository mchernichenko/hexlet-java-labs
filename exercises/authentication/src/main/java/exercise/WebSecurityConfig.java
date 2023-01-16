package exercise;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Переопределяет схему аутентификации
    // метод определяет, какие маршруты должны быть защищены, а какие нет
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // BEGIN
        http
                .authorizeRequests() // аутентифицировать пользователя при обращении к url
                .antMatchers("/").permitAll() // доступ к корню сайта доступен всем пользователям
                .antMatchers(HttpMethod.POST, "/users").permitAll() // регистрация доступна всем
                .antMatchers("/h2console/**").permitAll() //
                .anyRequest().authenticated() // доступ ко всем остальным url только аутентифицированным пользователям
                .and().httpBasic(); // использовать базовую аутентификацию
        // END
    }

    // Указываем, что для сравнения хешей паролей будет использоваться кодировщик BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Указываем, что будем использовать созданный нами диспетчер аутентификации вместо дефолтного
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
