package Services;

import com.example.springboot.PressureAutoSpecTechic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressureServices {
    private final PressureRepository pressureRepository;

    public PressureServices(PressureRepository pressureRepository) {
        this.pressureRepository = pressureRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;
    public void savePressure(PressureAutoSpecTechic pressure){
        pressureRepository.save(pressure);
    }
    public List<PressureAutoSpecTechic> list(){
        return pressureRepository.findAll();
    }
}
