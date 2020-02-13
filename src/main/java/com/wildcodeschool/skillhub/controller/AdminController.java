package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.repository.AdminRepository;
import com.wildcodeschool.skillhub.repository.CategoryRepository;
import com.wildcodeschool.skillhub.repository.ExpertRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private AdminRepository adminRepository = new AdminRepository();
    private CategoryRepository categoryRepository = new CategoryRepository();
    private ExpertRepository expertRepository = new ExpertRepository();


    @GetMapping("/admin")
    public String start() {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String getAllUser(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("users", adminRepository.findAll(null));
        System.out.println("/admin/getAllUser");
        return "adminusers";
        }

    @GetMapping("/admin/user")
    public String getUser(Model model, @RequestParam(required = false) Long userid) {
        User user = new User();
        if (userid != null) {
            user = adminRepository.findById(userid);
        }
        model.addAttribute("user", user);
        System.out.println("/admin/getUser |" + user.getUserId());
        return "adminuser";
        }

    @PostMapping("/admin/user")
    public String postUser(@ModelAttribute User user) {
        if (user.getUserId() != null) {
          adminRepository.update(user);
        } else {
          adminRepository.save(user);
        }
        System.out.println("/admin/user-postUser |" + user.getUserId() + "|");
        return "redirect:/admin/users";
        }

    @GetMapping("/admin/del-id")
      public String getById(Model model, @RequestParam Long userid) {
         model.addAttribute("user", adminRepository.findById(userid));
         System.out.println("/admin/user-getById |" + "|");
         return "admindel";
      }

    @GetMapping("/admin/user/delete")
      public String deleteUser(@RequestParam Long userid) {
        adminRepository.deleteById(userid);
         System.out.println("/admin/user/delete |" + "|");
         return "redirect:/admin/users";
      }
  
      // nachfolgend category

    @GetMapping("/admin/categorys")
      public String getAllCategory(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("categorys", categoryRepository.findAll(null));
        System.out.println("/admin/getAllCategory");
        return "admincategorys";
        }
    
    @GetMapping("/admin/category")
      public String getCategory(Model model, @RequestParam(required = false) Long userid) {
        Category category = new Category();
        model.addAttribute("category", category);
        System.out.println("/admin/getCategory |");
        return "admincategory";
        }
      
    @PostMapping("/admin/category")
      public String postCategory(@ModelAttribute Category category) {
        
        System.out.println("/admin/user-postUser 1. |"  + category.getCategoryName() + "|");
        
        categoryRepository.save(category);
        
        System.out.println("/admin/user-postUser 2. |"  + category + "|");
        return "redirect:/admin/categorys";
        }
 
        // nachfolgend expert

    @GetMapping("/admin/experts")
      public String getAllExpert(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("experts", expertRepository.findAll(null));
        System.out.println("/admin/experts-getAllExperts");
        return "adminexperts";
        }


}
