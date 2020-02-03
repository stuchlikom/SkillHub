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

    @GetMapping("/admin/users")
    public String getAll(Model model, @RequestParam(required = false) Long catSelected)
    {
        model.addAttribute("users", repository.findAll(null));
        System.out.println("/admin/users-getAll");
        return "adminusers";
    }

    @GetMapping("/admin/user")
    public String getUser(Model model, @RequestParam(required = false) Long userid)
    {
        User user = new User();
        if (userid != null) {
            user = repository.findById(userid);
        }
        model.addAttribute("user", user);
        System.out.println("/admin/user-getUser |" + user.getUserId());
        return "adminuser";
    }

    @PostMapping("/admin/user")
    public String postUser(@ModelAttribute User user) 
    {
        if (user.getUserId() != null) {
            repository.update(user);
        } else {
            repository.save(user);
        }
        System.out.println("/admin/user-postUser |" + user.getUserId() + "|");
        return "redirect:/admin/users";
    }
 //-----------------------------------------------------------------------   

    @GetMapping("/admin/del-id")
      public String getById(Model model, @RequestParam Long userid)
      {
         model.addAttribute("user", repository.findById(userid));
         System.out.println("/admin/user-getById |" + "|");
         return "admindel";
      }


      @GetMapping("/admin/user/delete")
      public String deleteUser(@RequestParam Long userid)
      {
         repository.deleteById(userid);
         System.out.println("/admin/user/delete |" + "|");
         return "redirect:/admin/users";
      }




}
