package demo.controller;

import demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 5/13/2015.
 */
@RestController
public class SimpleController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    @Qualifier("jdbcMysql")
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/hi")
    public Customer greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Customer(1, "Sergey", "Petrov");
    }

    @RequestMapping("/hiAll")
    public List<Customer> greetingAll(@RequestParam(value="name", defaultValue="World") String name) {
        return jdbcTemplate.query("select id, last_name, first_name from customers", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name") );
            }
        });
    }
}
