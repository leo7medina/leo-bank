package ec.com.leo.bank;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {"ec.com.leo.bank"})
public class LeoBankApplication {

	public static void main(String[] args) {

		try {
			SpringApplicationBuilder builder = new SpringApplicationBuilder(LeoBankApplication.class);
			//builder.headless(false);
			builder.run(args);
			log.info("*************************************Inicio de sistema**********************************");
		} catch (Exception throwable) {
			if (!Objects.equals(throwable.getClass().getName(), "org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")
					&& log.isErrorEnabled()) {
				log.error("*************************************Ha ocurrido una exception**********************************");
				log.error("Exception: " + throwable.toString());
				//log.error("Root Cause: " + ExceptionUtils.handleThrowable(throwable));
			}
		}
	}

	/**
	 * Open api bean definition.
	 * @return OpenAPI
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("REST API Documentation")
						.description("REST API Documentation for services")
						.version("1.0.0-SNAPSHOT")
						.contact(new Contact()
								.name("Leonardo Dev")
								.email("leo_medina14@hotmail.com")
								.url("https://github.com/leo7medina"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html")));
	}

}
