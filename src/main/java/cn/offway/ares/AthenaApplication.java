package cn.offway.ares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.vinann","cn.offway"})
public class AthenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthenaApplication.class, args);
	}
}
