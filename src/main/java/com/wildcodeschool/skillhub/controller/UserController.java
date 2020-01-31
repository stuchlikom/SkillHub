package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository repository = new UserRepository();


    @GetMapping("/user-id")
      public String getById(Model model, @RequestParam Long userid) {

          model.addAttribute("user", repository.findById(userid));

          return "user_del";
      }


    @GetMapping("/users")
    public String getAll(Model model, @RequestParam(required = false) Long catSelected)  {

        if (catSelected == null) catSelected = 0L;

        model.addAttribute("users", repository.findAll(catSelected));

        return "users";
    }

    @GetMapping("/user")
    public String getWizard(Model model,
                            @RequestParam(required = false) Long userid) {

        User user = new User();
        if (userid != null) {
            user = repository.findById(userid);
        }
        model.addAttribute("user", user);

        return "user";
    }

    @PostMapping("/user")
    public String postUser(@ModelAttribute User user) {

        if (user.getUserId() != null) {
            repository.update(user);
        } else {
            repository.save(user);
        }
        return "redirect:/user";
    }

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam Long userid) {

        repository.deleteById(userid);

        return "redirect:/users";
    }
}
