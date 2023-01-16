package exercise;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.DELETE;

import exercise.model.UserRole;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().and().sessionManagement().disable();
        http.headers().frameOptions().disable(); // решает проблему отображение H2-Console в браузере

        // BEGIN
        http
                .authorizeRequests()
                // Корневая страница, регистрация и консоль h2 доступна всем пользователям
                .antMatchers(GET, "/").permitAll()
                .antMatchers(POST, "/users").permitAll()
                .antMatchers("/h2console/").permitAll()
                // Просмотр списка пользователей или инфу по конкретному пользователю
                // т.е доступ к /users и всем вложенным url, например /users/1, может "USER" или "ADMIN"
                .antMatchers(GET,"/users/**").hasAnyAuthority(UserRole.USER.toString(), UserRole.ADMIN.toString())
                // Удалить пользователя может только "ADMIN"
                .antMatchers(DELETE,"/users/{id}").hasAuthority(UserRole.ADMIN.toString())
                .and().httpBasic();
        // END
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
