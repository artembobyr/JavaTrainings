package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping
    public String add (@RequestParam String name,
                                            @RequestParam String email, Map<String, Object> model) {
        User user = new User(name, email);
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("filter")
    public String find (@RequestParam String filter, Map<String, Object> model) {
        List<User> users = userRepository.findByEmail(filter);
        model.put("users", users);
        return "main";
    }
}
