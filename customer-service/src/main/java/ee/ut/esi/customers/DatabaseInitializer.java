package ee.ut.esi.customers;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseInitializer {
    public static void initialize(String databaseName) {
        String url = "jdbc:postgresql://localhost:5432/";
        String username = "postgres";
        String password = "postgres";

        var dataSource = new DriverManagerDataSource(url, username, password);
        var jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.execute("CREATE DATABASE " + databaseName);
        } catch (Exception e) {
            System.err.println("Database already exists");
        }
    }
}
