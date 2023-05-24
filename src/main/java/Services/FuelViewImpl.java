package Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelViewImpl implements FuelView{
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> getFuel(Long id) {
        return em.createQuery("SELECT id_tech, sum(h.fuel_consumption) AS fuelConsSum, count(*) * 10 AS durM_measuring " +
                                "FROM FuelAutoSpecTechnic AS h WHERE h.time BETWEEN current_timestamp - (7 DAY) AND current_timestamp " +
                                "AND h.id_tech = :id " +
                                "GROUP BY id_tech",

                        Object[].class)
                .setParameter("id", id)
                .getResultList();
    }

}
