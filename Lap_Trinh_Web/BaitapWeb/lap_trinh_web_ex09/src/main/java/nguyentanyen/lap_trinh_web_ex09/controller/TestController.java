package nguyentanyen.lap_trinh_web_ex09.controller;

import nguyentanyen.lap_trinh_web_ex09.entity.User;
import nguyentanyen.lap_trinh_web_ex09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String testPage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "test";
    }
}


