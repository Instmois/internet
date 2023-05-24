package Services;

import com.example.springboot.PressureAutoSpecTechnic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PressureRepository extends JpaRepository<PressureAutoSpecTechnic, Long> {
}
