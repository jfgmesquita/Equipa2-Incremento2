package Equipa2.Incremento2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração da aplicação para permitir CORS (Cross-Origin Resource Sharing).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura as definições de CORS para a aplicação.
     *
     * @return uma instância de WebMvcConfigurer com as definições de CORS configuradas.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
    
}