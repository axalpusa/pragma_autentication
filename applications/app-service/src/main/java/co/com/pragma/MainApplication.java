package co.com.pragma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {
        "co.com.pragma.api",
        "co.com.pragma.r2dbc",
        "co.com.pragma.usecase",
        "co.com.pragma.model"
})
//@ConfigurationPropertiesScan
public class MainApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
