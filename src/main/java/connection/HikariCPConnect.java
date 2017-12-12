package connection;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariCPConnect {

	 private static DataSource datasource;
	 	 
	 public static DataSource getConnection() {
	    	
		 if(datasource == null) {
			 	String configFile = "/db.properties";
			 	HikariConfig config = new HikariConfig(configFile);
			 	
			 	datasource = new HikariDataSource(config);

		 }
	    	return datasource;	
	    }
	}