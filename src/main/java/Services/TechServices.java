package Services;

import com.example.springboot.AutoSpecTechnic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TechServices {
    private final TechRepository techRepository;
    @Autowired
    public TechServices(TechRepository techRepository) {
        this.techRepository = techRepository;
    }
    public Optional<AutoSpecTechnic> findById(Long id) {
        return repr.findById(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(AutoSpecTechnic user) {
        techRepository.save(user);
    }

    public List<AutoSpecTechnic> list(){
        List<AutoSpecTechnic> technics = techRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return technics;
    }
}