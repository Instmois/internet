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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Controller
@EnableJpaRepositories("Services")
@ComponentScan(basePackages = {"Services", "Configuration"})
@EnableWebMvc
public class Application implements WebMvcConfigurer {
	// TODO: Automatic updating of parameters on pages technic - complited
	// TODO: Notifications at low oil pressure - complited
	// TODO: Notifications at high fuel consumption - complited
	// TODO: Extend data on other pages
	// TODO: Fuel graph
	// TODO: Add map - complited
	static Dt dt;
	CoordinatesGenerator Rnd;
	@Autowired
	private PhotoSearchService photoSearchService;
	@GetMapping("/technics")
	public String getTechnics(Model model) {
		Rnd = new CoordinatesGenerator();
		List<AutoSpecTechnic> technics = techServices.list();
		model.addAttribute("technics", technics);
		model.addAttribute("latitude", Rnd.coordLat);
		model.addAttribute("longitude", Rnd.coordLong);
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
	@GetMapping("/technics/{id}/parameters")
	@ResponseBody
	public Map<String, Object> getTechnicParameters(@PathVariable("id") Long id) {
		Map<String, Object> parameters = new HashMap<>();

		List<Object[]> hourData = hoursRepository.getInHours(id);
		List<Object[]> fuelData = fuelRepository.getFuel(id);
		List<Object[]> pressureData = pressureRepository.getOil(id);

		if (!hourData.isEmpty()) {
			Object[] hourDataRow = hourData.get(0);
			parameters.put("engineHours", hourDataRow[1]);
			parameters.put("percentageOfWork", hourDataRow[2]);
			parameters.put("actualHours", hourDataRow[3]);
			parameters.put("percentageWork", hourDataRow[4]);
			parameters.put("durationMeasuring", hourDataRow[5]);
		}

		if (!fuelData.isEmpty()) {
			Object[] fuelDataRow = fuelData.get(0);
			parameters.put("fuelConsumption", fuelDataRow[1]);
		}

		if (!pressureData.isEmpty()) {
			Object[] pressureDataRow = pressureData.get(0);
			parameters.put("oilPressure", pressureDataRow[1]);
		}

		return parameters;
	}


	/*
    @GetMapping("/technics/{id}/parameters")
    public Map<String, Object> getTechnicParameters(@PathVariable("id") Long id) {
        Map<String, Object> responseData = new HashMap<>();
        List<Object[]> combinedData =  hoursRepository.getInHours(id);// Получите обновленные значения combinedData из репозитория или сервиса
        List<Object[]> fuelData =  fuelRepository.getFuel(id);// Получите обновленные значения fuelData из репозитория или сервиса
        List<Object[]> pressureData = pressureRepository.getOil(id);// Получите обновленные значения pressureData из репозитория или сервиса
        responseData.put("combinedData", combinedData);
        responseData.put("fuelData", fuelData);
        responseData.put("pressureData", pressureData);
        return responseData;
    }*/

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
