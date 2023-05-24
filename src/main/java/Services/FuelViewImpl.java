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
        return em.createQuery("SELECT id_tech, " +
                                "ROUND(sum(fuel_consumption) * 60.0 / count(*), 2) AS fuelConsSum " +
                                "FROM FuelAutoSpecTechnic AS f WHERE id_tech = :id AND " +
                                "time BETWEEN current_timestamp - (1 HOUR) AND current_timestamp "  +
                                "GROUP BY id_tech",
                        Object[].class)
                .setParameter("id", id)
                .getResultList();
    }

}
