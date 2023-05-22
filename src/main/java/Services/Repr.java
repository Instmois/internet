package Services;

import com.example.springboot.AutoEntity;
import com.example.springboot.AutoSpecTechnic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Repr extends JpaRepository<AutoEntity, Long> {
}
