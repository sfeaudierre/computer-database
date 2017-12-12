package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"services", "dao", "mapper", "servlets"})
public class AppConfig {

}