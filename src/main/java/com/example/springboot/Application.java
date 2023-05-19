package com.example.springboot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;

@SpringBootApplication
@RestController

public class Application {
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/index")
	public String index(@RequestParam(value = "name", defaultValue = "findex") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/findex")
	public String findex(@RequestParam(value = "name", defaultValue = "findex") String name) {
		return String.format("Hello %s!", name);
	}
	public static ArrayList<Autospectechnics> get_tech(ArrayList<Autospectechnics> tech) throws IOException{
		for (Autospectechnics i : tech) {
			i.actual_hours = rnd.nextDouble();
			i.downtime = rnd.nextDouble();
			i.engine_hours = rnd.nextDouble();
			i.fuel_consumption = rnd.nextDouble();
			i.oil_pressure = rnd.nextDouble(11,41);
		}
		return tech;
	}
	static Random rnd = new Random();
	public static ArrayList<Autospectechnics> default_tech(){
		String[] brand = {"Caterpillar","Hitachi","Hyundai","JCB"};
		String[][] model = {{"320D","320","336d","330D2"},{"ZX330", "ZX200", "ZX240"},{"R210LC","R290LC", "R480", "R520LS"},{"JS220", "JS330", "JS305", "JS160"}};
		ArrayList<Autospectechnics> technics = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int i1 = rnd.nextInt() % brand.length;
			int i2 = rnd.nextInt() % model[i1].length;
			Autospectechnics tech = new Autospectechnics(brand[i1],model[i1][i2],i);
			technics.add(tech);
		}
		return technics;
	}
	public static void main(String[] args) throws IOException {
		for (Autospectechnics i : get_tech(default_tech())){
			System.out.println(i);
		}
        SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}