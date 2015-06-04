package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Autowired
	@Qualifier("jdbcMysql")
	JdbcTemplate mysqlJdbcTemplate;

	@Autowired
	@Qualifier("jdbcPgSql")
	JdbcTemplate pgSqlJdbcTemplate;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testMySQLDataBaseAllwaysTrimOnRun(){
		List<Long> results  = pgSqlJdbcTemplate.query("select count(*) as cnt from customers", new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("cnt");
			}
		});
		assertEquals(new Long(4), results.get(0));
	}

	@Test
	public void testPGDataBaseAllwaysTrimOnRun(){
		List<Long> results = pgSqlJdbcTemplate.query("select count(*) as cnt from customers", new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("cnt");
			}
		});
		assertEquals(new Long(4), results.get(0));
	}

}
