package Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public interface HoursView<T> {
    T getInMinutes(Long id);

}

