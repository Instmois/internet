package com.example.springboot;

import java.io.IOException;
import java.util.*;

import Agregated.HoursData;
import Services.*;
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
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	private PhotoSearchService photoSearchService;
	@GetMapping("/technics")
	public String getTechnics(Model model) {
		for(Object[] o : hoursRepository.getInMinutes()) {
			for (Object i : o)
				System.out.println(i);
			System.out.println();
		}


		List<AutoSpecTechnic> technics = techServices.list();
		model.addAttribute("technics", technics);
		return "technics";
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
	}
	@GetMapping("/technics/{id}")
	public String getTechnicById(@PathVariable("id") Long id, Model model) {
		Optional<AutoSpecTechnic> technic = techServices.findById(id);
		if (technic.isPresent()) {
			model.addAttribute("technic", technic.get());
			try {
				// Поиск фотографий
				List<String> photoUrls = photoSearchService.searchPhotos(technic.get().getBrand(), technic.get().getModel());
				model.addAttribute("photoUrls", photoUrls);
			} catch (IOException e) {
				// Обработка исключения IOException
				e.printStackTrace();
				// Добавьте соответствующую логику обработки ошибок
				// Возможно, вы хотите добавить сообщение об ошибке на страницу или выполнить другие действия
				return "error";
			}
			return "technic";
		} else {
			// Обработка случая, когда техника не найдена
			return "error";
		}
	}

	@Autowired
	private TechServices techServices;
	@Autowired
	private HourServices hourServices;
	@Autowired
	private FuelServices fuelServices;
	@Autowired
	private PressureServices pressureServices;
	@Autowired
	private HoursViewImpl hoursRepository;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			if (techServices.list().size() == 0) {
				dt = new Dt();
				for (AutoSpecTechnic i : dt.technics) {
					techServices.saveUser(i);
				}
			}
			else{
				dt = new Dt(techServices.list());
			}
			Thread run = new Thread(new TimerInfo(hourServices, fuelServices, pressureServices));
			run.start();
		};
	}
}
