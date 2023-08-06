package pl.matrasbartosz.parcellockersystem.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api.properties")
@SecurityScheme(name = "parcellockersystem", scheme = "basic",
        type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(
        info = @Info(
                title = "${api.title}",
                version = "${api.version}",
                description = "${api.description}",
                contact = @Contact(
                        name = "${api.owner.name}", email = "${api.owner.email}", url = "${api.owner.url}"
                ),
                license = @License(
                        name = "${api.license.name}", url = "${api.license.url}"
                )
        )
)
public class OpenAPISecurityConfiguration {
}
