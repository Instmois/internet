package com.example.springboot;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

import Services.Repr;
import Services.TechServices;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Controller
@EnableJpaRepositories("Services")
@ComponentScan(basePackages = {"Services", "Configuration"})
@EnableWebMvc
public class Application implements WebMvcConfigurer {
	static Dt dt;

	@GetMapping("/technics")
	public String getTechnics(Model model) {
		List<AutoEntity> technics = techServices.list();
		model.addAttribute("technics", technics);
		return "technics";
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
	}

	@Autowired
	private TechServices techServices;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			dt = new Dt();

			for (AutoSpecTechnic i : dt.technics) {
				AutoSpecTechnic autoSpecTechnic = new AutoSpecTechnic(i.brand, i.model, i.id_tech);
				techServices.saveUser(autoSpecTechnic);
			}
			Thread run = new Thread(new TimerInfo());
			run.start();
		};
	}
}
