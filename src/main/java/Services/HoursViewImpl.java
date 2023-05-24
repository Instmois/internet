package Services;

import Agregated.HoursData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoursViewImpl implements HoursView {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> getInMinutes(Long id) {
        return em.createQuery("SELECT id_tech, sum(h.engine_hours) * 60 AS engineM, sum(h.actual_hours) * 60 AS actualM, " +
                                "count(*) * 10 AS durM_measuring " +
                                "FROM HoursAutoSpecTechnic AS h WHERE h.time BETWEEN current_timestamp - (7 DAY) AND current_timestamp " +
                                "AND id_tech = :id " +
                                "GROUP BY id_tech",
                Object[].class)
                .setParameter("id", id)
                .getResultList();
    }
}
