package Services;


import com.example.springboot.AutoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechServices {
    private final Repr repr;

    public TechServices(Repr repr) {
        this.repr = repr;
    }

    public List<AutoEntity> list(){
        return repr.findAll();
    }
}
