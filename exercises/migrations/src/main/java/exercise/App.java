package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*import org.springframework.context.annotation.Bean;
import liquibase.integration.spring.SpringLiquibase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import liquibase.exception.LiquibaseException;
import liquibase.Liquibase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;*/

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // Запускаем приложение
        SpringApplication.run(App.class, args);
    }

    // Попытка достать нативный sql генерируемый liquibase
    // https://stackoverflow.com/questions/12147855/liquibase-any-way-to-output-change-log-sql-to-a-file-in-2-0-5
    /*@Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibaseWriter();
        return liquibase;
    }

    private static class SpringLiquibaseWriter extends SpringLiquibase {
        @Autowired
        static JdbcTemplate jdbc;

        @Override
        public void afterPropertiesSet() throws LiquibaseException {

            try (Connection connection = jdbc.getDataSource().getConnection()) {

                Liquibase liquibase = createLiquibase(connection);
                Writer writer = new OutputStreamWriter(new FileOutputStream("", false));
                liquibase.update(getContexts(), writer);

            } catch (LiquibaseException | SQLException | FileNotFoundException e) {
                e.getStackTrace().toString();
            }
            super.afterPropertiesSet();
        }
    }*/
}
