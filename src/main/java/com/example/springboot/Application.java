package com.example.springboot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	public static void get_pairs() throws IOException{
		Document document = Jsoup.connect("https://ru.investing.com/equities/").get();
		Elements h1 = document.select("tbody");
		Element rus = h1.get(0);
		Elements trs = rus.select("tr");
		for (Element i: trs) {
			String id = i.attributes().get("id");
			int id_int = Integer.parseInt(id.substring(5));
			Elements title0 = i.getElementsByAttributeValue("class", "bold left noWrap elp plusIconTd");
			Elements title = title0.select("a");
			Elements cost = i.getElementsByAttributeValueEnding("class", "-last");
			Elements high = i.getElementsByAttributeValueEnding("class", "-high");
			Elements low = i.getElementsByAttributeValueEnding("class", "-low");
			Elements change = i.getElementsByAttributeValueEnding("class", "-pc");
			Elements percent = i.getElementsByAttributeValueEnding("class", "-pcp");
			Elements turnover = i.getElementsByAttributeValueEnding("class", "-turnover");
			Elements time = i.getElementsByAttributeValueEnding("class", "-time");
//			System.out.println(i);
// 			System.out.println(id_int);
			System.out.println(title.text());
			System.out.println(cost.text());
			System.out.println(high.text());
			System.out.println(low.text());
			System.out.println(change.text());
			System.out.println(percent.text());
			System.out.println(turnover.text());
			System.out.println(time.text());
			System.out.println("________");
		}
	}
	public static void main(String[] args) throws IOException {
		get_pairs();
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