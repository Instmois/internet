package Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressureViewImpl implements PressureView{
    @PersistenceContext
    private EntityManager em;
    public List<Object[]> getOil(Long id) {
        return em.createQuery("SELECT id_tech, ROUND(oil_pressure, 2) FROM PressureAutoSpecTechnic " +
                                "WHERE time = " +
                                "(SELECT MAX(time) as time FROM PressureAutoSpecTechnic WHERE id_tech = :id GROUP BY id_tech)" +
                                "AND id_tech = :id",
                        Object[].class)
                .setParameter("id", id)
                .getResultList();
    }
}