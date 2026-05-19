package pl.edu.pjatk.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
class SwaggerUrlLogger {

    @Value(value = "${server.port}")
    private String port;

    @Value(value = "${springdoc.swagger-ui.path}")
    private String swaggerPath;

    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl(ApplicationReadyEvent event) {
            log.info("""
                \n----------------------------------------------------------
                \tSwagger documentation url:
                \thttp://localhost:{}{}/index.html
                ----------------------------------------------------------""", port, swaggerPath);
    }

}
