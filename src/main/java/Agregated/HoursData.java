package Agregated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class HoursData {
    @Id
    long id_tech;
    double engineM;
    double actualM;
    double durM_measuring;
}
