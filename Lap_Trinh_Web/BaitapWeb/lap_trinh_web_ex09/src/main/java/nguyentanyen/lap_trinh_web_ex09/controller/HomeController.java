package nguyentanyen.lap_trinh_web_ex09.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && 
            !authentication.getName().equals("anonymousUser")) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("authorities", authentication.getAuthorities());
        }
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}

