package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Bootstraps the application. Let the hating being!.
 * Created by escobeitor on 27/05/15.
 */
@SpringBootApplication
public class HatefulStarter {

    public static void main(String[] args) {
        SpringApplication.run(HatefulStarter.class, args);
    }

    /**
     * Enable JSR-303 Validation for mongo documents
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(localValidatorFactoryBean());
    }

    /**
     * JSR-303 Validator factory
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}
