package Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoursViewImpl implements HoursView {
    @PersistenceContext
    private EntityManager em;

    public List<Object[]> getInMinutes(Long id) {
        return em.createQuery("WITH hrs AS (" +
                                "SELECT id_tech AS techId, " +
                                "ROUND(SUM(h.engine_hours) * 60, 2) AS engineM, " +
                                "ROUND(SUM(h.actual_hours) * 60, 2) AS actualM, " +
                                "ROUND(COUNT(*) * 10, 2) AS durM_measuring, " +
                                "ROUND(SUM(h.engine_hours) * 60 / COUNT(*) * 10, 2) AS engineP, " +
                                "ROUND(SUM(h.actual_hours) / SUM(h.engine_hours) * 100, 2) AS actualP " +
                                "FROM HoursAutoSpecTechnic AS h WHERE h.time BETWEEN current_timestamp - (7 DAY) AND current_timestamp " +
                                "AND id_tech = :id " +
                                "GROUP BY id_tech" +
                                ")" +
                                "SELECT techId, " +
                                "engineM, " +
                                "engineP|| '%' AS engineP, " +
                                "actualM, " +
                                "actualP || '%' AS actualP, " +
                                "durM_measuring " +
                                "FROM hrs",
                        Object[].class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Object[]> getInHours(Long id) {
        return em.createQuery("WITH hrs AS (" +
                                "SELECT id_tech AS techId, " +
                                "ROUND(SUM(h.engine_hours), 2) AS engineH, " +
                                "ROUND(SUM(h.actual_hours), 2) AS actualH, " +
                                "ROUND(COUNT(*) / 6, 2) AS durH_measuring, " +
                                "ROUND(SUM(h.engine_hours) * 60 / COUNT(*) * 10, 2) AS engineP, " +
                                "ROUND(SUM(h.actual_hours) / SUM(h.engine_hours) * 100, 2) AS actualP " +
                                "FROM HoursAutoSpecTechnic AS h WHERE h.time BETWEEN current_timestamp - (7 DAY) AND current_timestamp " +
                                "AND id_tech = :id " +
                                "GROUP BY id_tech" +
                                ")" +
                                "SELECT techId, " +
                                "engineH, " +
                                "engineP|| '%' AS engineP, " +
                                "actualH, " +
                                "actualP || '%' AS actualP, " +
                                "durH_measuring " +
                                "FROM hrs",
                        Object[].class)
                .setParameter("id", id)
                .getResultList();
    }
}
