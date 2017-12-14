package connection;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HikariCPConnect {

	static DataSource datasource;

	@Bean
	public static DataSource getConnection() {

		if(datasource == null) {
			String configFile = "/db.properties";
			HikariConfig config = new HikariConfig(configFile);

			datasource = new HikariDataSource(config);

		}
		return datasource;	
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}