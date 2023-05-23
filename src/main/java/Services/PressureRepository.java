package Services;

import com.example.springboot.PressureAutoSpecTechic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PressureRepository extends JpaRepository<PressureAutoSpecTechic, Long> {
}
