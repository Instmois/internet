package Agregated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class FuelData {
    @Id
    long id_tech;
    double fuel_cons;
    double durM_measuring;
}
