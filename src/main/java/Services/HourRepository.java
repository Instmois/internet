package Services;

import com.example.springboot.HoursAutoSpecTechnic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourRepository extends JpaRepository<HoursAutoSpecTechnic, Long> {
}
