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
    private final Repr repr;
    @Autowired
    public TechServices(Repr repr) {
        this.repr = repr;
    }
    public Optional<AutoSpecTechnic> findById(Long id) {
        return repr.findById(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(AutoSpecTechnic user) {
        repr.save(user);
    }

    public List<AutoSpecTechnic> list(){
        List<AutoSpecTechnic> technics = repr.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return technics;
    }
}