package com.wildcodeschool.skillhub.controller;
import com.wildcodeschool.skillhub.entity.*;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.UserRepository;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;


@Controller
public class UserController {

	private UserRepository repository = new UserRepository();

//	@GetMapping("/user/profile")
//	public String viewProfile(Model model) {
/*        User user = new User();
        User optionalUser = new User();
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = user.getUserId();
		if (userId != null) {
			optionalUser = user.findById(userId);
//			if (optionalUser.isPresent()) {
//				user = optionalUser.get();
//			}
		}
		model.addAttribute("user", user);
		return "user/profile";
    }
*/

	@GetMapping("/register")
	public String getUser(Model model, @RequestParam(required = false) Long userid)
	{
		User user = new User();
		if (userid != null) {
			user = repository.findById(userid);
		}
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String postUser(@ModelAttribute User user, BindingResult bindingResult) 
	{
		if (!bindingResult.hasErrors()){
			
			if (user.getUserId() != null) {
				repository.update(user);
			} else {
				repository.save(user);
			}
			return "redirect:/";
		}
		else {
			return "register";
		}
	}
}
