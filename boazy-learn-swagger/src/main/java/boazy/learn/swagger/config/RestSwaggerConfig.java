package boazy.learn.swagger.config;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/18
 */
@Configuration
@ApplicationPath("/rest")
public class RestSwaggerConfig extends ResourceConfig {
    @Context
    private ServletConfig servletConfig;

    public RestSwaggerConfig() {
        super();
        super.packages("io.swagger.v3.jaxrs2.integration.resources");

        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .prettyPrint(true)
                .resourcePackages(Stream.of("boazy.learn.swagger.impl").collect(Collectors.toSet())
        );

        try {
            new JaxrsOpenApiContextBuilder()
                    .servletConfig(servletConfig)
                    .application(this)
                    .openApiConfiguration(oasConfig)
                    .buildContext(true);
        } catch (OpenApiConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
