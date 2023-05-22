package Controllers;
import Services.TechServices;
import com.example.springboot.AutoEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tech")

public class TechController {
    private TechServices techServices;

    public TechController(TechServices techServices) {
        this.techServices = techServices;
    }

    @GetMapping(path = "list")
    public List<AutoEntity> getTotalInfo(){
        return techServices.list();
    }
}
