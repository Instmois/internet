package com.example.springboot;

import java.io.IOException;
import java.util.*;

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
	// TODO: Automatic updating of parameters on pages technic
	// TODO: Notifications at low oil pressure
	// TODO: Notifications at high fuel consumption
	// TODO: Extend data on other pages
	// TODO: Fuel graph
	static Dt dt;
	@Autowired
	private PhotoSearchService photoSearchService;
	@GetMapping("/technics")
	public String getTechnics(Model model) {
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
		List<Object[]> hourData = hoursRepository.getInHours(id);
		List<Object[]> fuelData = fuelRepository.getFuel(id);
		List<Object[]> pressureData = pressureRepository.getOil(id);
		if (technic.isPresent()) {
			model.addAttribute("combinedData", hourData);
			model.addAttribute("fuelData",	fuelData);
			model.addAttribute("pressure", pressureData);
			model.addAttribute("technic", technic.get());
			try {
				List<String> photoUrls = photoSearchService.searchPhotos(technic.get().getBrand(), technic.get().getModel());
				model.addAttribute("photoUrls", photoUrls);
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
			return "technic";
		} else {
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
	@Autowired
	private FuelViewImpl fuelRepository;
	@Autowired
	private PressureViewImpl pressureRepository;

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
