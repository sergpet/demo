package demo.datasources;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by user on 5/27/2015.
 */
@Configuration
public class DataSourceConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Primary
    @Bean(name = "dsMysql")
    @ConfigurationProperties(prefix = "spring.ds_mysql")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .url(url)
                .build();
    }

    @Bean(name = "dsPgsql")
    @ConfigurationProperties(prefix = "spring.ds_postgres")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .url(url)
                .build();
    }


    @Bean(name = "jdbcMysql")
    public JdbcTemplate mysqlJdbcTemplate() {
        return new JdbcTemplate(primaryDataSource());
    }

    @Bean(name = "jdbcPgSql")
    public JdbcTemplate pgSqljdbcTemplate() {
        return new JdbcTemplate(secondaryDataSource());
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
