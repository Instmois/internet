package Controllers;

import Services.TechServices;
import com.example.springboot.AutoSpecTechnic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TechController {
    private TechServices techServices;
    @Autowired
    public TechController(TechServices techServices) {
        this.techServices = techServices;
    }

    @GetMapping(path = "list")
    public List<AutoSpecTechnic> getTotalInfo(){
        return techServices.list();
    }
}
