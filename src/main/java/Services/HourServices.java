package Services;

import com.example.springboot.AutoSpecTechnic;
import com.example.springboot.HoursAutoSpecTechnic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HourServices {
    private final HourRepository hourRepository;

    public HourServices(HourRepository hourRepository) {
        this.hourRepository = hourRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;
    public void saveHour(HoursAutoSpecTechnic hour){
        hourRepository.save(hour);
    }
    public List<HoursAutoSpecTechnic> list(){
        return hourRepository.findAll();
    }
}
