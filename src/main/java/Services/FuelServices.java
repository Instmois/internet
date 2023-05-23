package Services;

import com.example.springboot.FuelAutoSpecTechnic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelServices {
    private final FuelRepository fuelRepository;

    public FuelServices(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;
    public void saveFuel(FuelAutoSpecTechnic fuel){
        fuelRepository.save(fuel);
    }
    public List<FuelAutoSpecTechnic> list() {
        return fuelRepository.findAll();
    }
}
