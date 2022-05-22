package ec.com.leo.bank;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

}
