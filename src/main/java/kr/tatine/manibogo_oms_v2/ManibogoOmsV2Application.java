package kr.tatine.manibogo_oms_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ManibogoOmsV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ManibogoOmsV2Application.class, args);
	}

}
