package com.example.springboot;

import java.io.File;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://ru.investing.com/equities/").get();
		Elements h1 = document.select("tbody");
		Element rus = h1.get(0);
		Elements trs = rus.select("tr");
		for (Element i: trs) {
			String id = i.attributes().get("id");
			Integer id_int = Integer.valueOf(id.substring(5));
			String costValue = "pid-" + id_int + "-last";
			String highValue = "pid-" + id_int + "-high";
			String lowValue = "pid-" + id_int + "-low";
			String changeValue1 = "bold greenFont pid-" + id_int + "-pc";
			String percentValue1 = "bold greenFont pid-" + id_int + "-pcp";
			String changeValue2 = "bold redFont pid-" + id_int + "-pc";
			String percentValue2 = "bold redFont pid-" + id_int + "-pcp";
			String turnoverValue = "pid-" + id_int + "-turnover";
			String timeValue = "pid-" + id_int + "-time";
			Elements title0 = i.getElementsByAttributeValue("class", "bold left noWrap elp plusIconTd");
			Elements title = title0.select("a");
			Elements cost = i.getElementsByAttributeValue("class", costValue);
			Elements high = i.getElementsByAttributeValue("class", highValue);
			Elements low = i.getElementsByAttributeValue("class", lowValue);
			Elements change = i.getElementsByAttributeValue("class", changeValue1);
			Elements change1 = i.getElementsByAttributeValue("class", changeValue2);
			Elements percent = i.getElementsByAttributeValue("class", percentValue1);
			Elements percent1 = i.getElementsByAttributeValue("class", percentValue2);
			Elements turnover = i.getElementsByAttributeValue("class", turnoverValue);
			Elements time = i.getElementsByAttributeValue("class", timeValue);
			//System.out.println(i);
			//System.out.println(id);
			System.out.println(title.text());
			System.out.println(cost.text());
			System.out.println(high.text());
			System.out.println(low.text());
			if(change.size() != 0) {System.out.println(change.text());}
			if(change1.size() != 0)System.out.println(change1.text());
			if(percent.size() != 0) {System.out.println(percent.text());}
			if(percent1.size() != 0) System.out.println(percent1.text());
			System.out.println(turnover.text());
			System.out.println(time.text());
			//System.out.println(id_int);
			System.out.println("________");
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