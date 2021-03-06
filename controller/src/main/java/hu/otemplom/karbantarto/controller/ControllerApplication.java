package hu.otemplom.karbantarto.controller;

import hu.otemplom.karbantarto.controller.email.EmailServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.service"})
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.dao"})
public class ControllerApplication {

	public static void main(String[] args) throws MessagingException {
/*		System.out.println("Playing with dates");
		Date date1 = new Date(2020,10,2);
		Date date2 = new Date(2020,9,27);
		Date date3 = new Date(2020,9,10);
		System.out.println("Date 2 is in one week?");
		System.out.println(TimeUnit.DAYS.convert(date1.getTime()-date2.getTime(),TimeUnit.MILLISECONDS));
		String answer = TimeUnit.DAYS.convert(date1.getTime()-date2.getTime(),TimeUnit.MILLISECONDS) < 7?"True":"False";
		System.out.println(answer);
		System.out.println("Date 3 is in one week?");
		System.out.println(TimeUnit.DAYS.convert(date1.getTime()-date3.getTime(),TimeUnit.MILLISECONDS));
		String answer2 = TimeUnit.DAYS.convert(date1.getTime()-date3.getTime(),TimeUnit.MILLISECONDS) < 7?"True":"False";
		System.out.println(answer2);*/
		SpringApplication.run(ControllerApplication.class, args);

	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://10.5.23.203:4200").allowedMethods("GET","POST","PUT", "DELETE")
						;
			}
		};
	}


}
