package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"services", "dao", "mapper", "servlets", "connection", "configuration"})
public class AppConfig {

}
