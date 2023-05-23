package Services;

import com.example.springboot.FuelAutoSpecTechnic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<FuelAutoSpecTechnic, Long> {
}
