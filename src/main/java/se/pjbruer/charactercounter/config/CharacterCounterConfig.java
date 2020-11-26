package se.pjbruer.charactercounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CharacterCounterConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/findWordsParams")
                        .allowedOrigins("http://localhost:8080/api");

                registry.addMapping("/findWordsBody")
                        .allowedOrigins("http://localhost:8080/api");
            }
        };
    }
}
