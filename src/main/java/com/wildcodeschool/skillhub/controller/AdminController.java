package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.entity.Expert;
import com.wildcodeschool.skillhub.entity.ExpertCategory;

import com.wildcodeschool.skillhub.repository.UserRepository;
import com.wildcodeschool.skillhub.repository.AvatarRepository;
import com.wildcodeschool.skillhub.repository.CategoryRepository;
import com.wildcodeschool.skillhub.repository.ExpertCategoryRepository;
import com.wildcodeschool.skillhub.repository.ExpertRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private UserRepository userRepository = new UserRepository();
    private AvatarRepository avatarRepository = new AvatarRepository();
    private CategoryRepository categoryRepository = new CategoryRepository();
    private ExpertRepository expertRepository = new ExpertRepository();
    private ExpertCategoryRepository expertCategoryRepository = new ExpertCategoryRepository();

    @GetMapping("/admin")
      public String start() {
          return "admin";
      }

    @GetMapping("/admin/users")
      public String getAllUser(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("users", userRepository.findAll(null));
        System.out.println("/admin/getAllUser");
        return "adminusers";
        }

    @GetMapping("/admin/user")
      public String getUser(Model model, @RequestParam(required = false) Long userid) {
        User user = new User();
        if (userid != null) {
            user = userRepository.findById(userid);
        }
        model.addAttribute("user", user);
        System.out.println("/admin/getUser |" + user.getUserId());
        return "adminuser";
        }

    @PostMapping("/admin/user")
      public String postUser(@ModelAttribute User user) {
        if (user.getUserId() != null) {
          userRepository.update(user);
        } else {
          userRepository.save(user);
        }
        return "redirect:/admin/users";
      }
  
    @GetMapping("/admin/del-id")
      public String getById(Model model, @RequestParam Long userid) {
         model.addAttribute("user", userRepository.findById(userid));
         System.out.println("/admin/user-getById |" + "|");
         return "admindel";
      }

    @GetMapping("/admin/user/delete")
      public String deleteUser(@RequestParam Long userid) {
        avatarRepository.deleteById(userid);
        userRepository.deleteById(userid);
         System.out.println("/admin/user/delete |" + "|");
         return "redirect:/admin/users";
      }
  
      // nachfolgend category

    @GetMapping("/admin/categories")
      public String getAllCategory(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("categories", categoryRepository.findAll(null));
        System.out.println("/admin/getAllCategory");
        return "admincategories";
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
        return "redirect:/admin/categories";
        }
 
        // nachfolgend expert

    @GetMapping("/admin/experts")
      public String getAllExpert(Model model, @RequestParam(required = false) Long catSelected) {
        model.addAttribute("experts", expertRepository.findAll(null));
        System.out.println("/admin/experts-getAllExperts");
        return "adminexperts";
        }

    @GetMapping("/admin/expert")
      public String getExpert(Model model, @RequestParam(required = false) Long userid) {
        Expert expert = new Expert();
        if (userid != null) {
            expert = expertRepository.findById(userid);
        }
        model.addAttribute("expert", expert);
        model.addAttribute("categories", categoryRepository.findAll(null));
        model.addAttribute("expertCategory", new ExpertCategory ());
        System.out.println("/admin/getExpert |" + expert.getUserId());
        return "adminexpert";
        }

        // nachfolgend expertCategory
      
    @PostMapping("/admin/expert")
      public String postExpert(Model model,@ModelAttribute ExpertCategory expertCategory) {
          
          expertCategoryRepository.save(expertCategory);
          
          System.out.println("/admin/expert-postUser 2. |"  + expertCategory.getUserId() + "|"+ expertCategory.getCategoryId() + "|");
          return "redirect:/admin/experts";
          }

     @PostMapping("/admin/expert/delete")
        public String deleteExpertCategory(Model model,@ModelAttribute ExpertCategory expertCategory) {
          
          System.out.println("/admin/expert/delete-postUser 1. |"  + expertCategory.getUserId() + "|"+ expertCategory.getCategoryId() + "|");
          
          expertCategoryRepository.deleteExpertCategory(expertCategory);
          
          System.out.println("/admin/expert/delete-postUser 2. |"  + expertCategory.getUserId() + "|");
          return "redirect:/admin/experts";
          }

        @PostMapping("/admin/expert/deleteall")
          public String deleteAllExpertCategory(Model model,@ModelAttribute ExpertCategory expertCategory) {
            
            System.out.println("/admin/expert/deleteall-postUser 1. |"  + expertCategory.getUserId() + "|");
            
            expertCategoryRepository.deleteAllExpertCategory(expertCategory);
            
            System.out.println("/admin/expert/delete-postUser 2. |"  + expertCategory.getUserId() + "|");
            return "redirect:/admin/";
            }
}
