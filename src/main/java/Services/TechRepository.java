package Services;

import com.example.springboot.AutoSpecTechnic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<AutoSpecTechnic, Long> {
}
