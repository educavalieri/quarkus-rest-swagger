package acc.br.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "user", description = "User operations."),
        },
        info = @Info(
                title = "User API with Quarkus",
                version = "0.0.1",
                contact = @Contact(
                        name = "",
                        url = "",
                        email = ""),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"))
)

//@ApplicationScoped
public class SwaggerConfig extends Application {

}
