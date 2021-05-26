package kuleba.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan("kuleba.spring.dao")
public class SpringConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/kulebaDB");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dataSource());
    }
}
