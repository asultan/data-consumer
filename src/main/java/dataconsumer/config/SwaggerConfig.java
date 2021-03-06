package dataconsumer.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG_DATA_POINTS = "data-points";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/")))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .tags(new Tag(TAG_DATA_POINTS, "Get data points"))
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Data Consumer Service API")
                .description("This is the Data Consumer Service, exposing a REST API for database reading. " +
                        "The service context is <b>/data-consumer</b>, so make sure  that all REST requests URLs start with that.")
                .version("1.0.0")
                .build();
    }
}
