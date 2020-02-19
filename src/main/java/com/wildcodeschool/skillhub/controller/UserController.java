package com.wildcodeschool.skillhub.controller;
import com.wildcodeschool.skillhub.entity.*;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.UserRepository;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

	private UserRepository repository = new UserRepository();


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
	public String checkPersonInfo(@Valid User user, BindingResult bindingResult, Model model) {
		// Lookup user in database by nickname
		User userExists = repository.findByNick(user.getNickName());
		ModelAndView modelAndView = new ModelAndView();

		System.out.println(userExists);

		if (userExists != null) {
			System.out.println("if-Zweig");
			modelAndView.addObject("NickMessage", "Oops!  There is already a user registered with the email provided.");
			model.addAttribute("NicknameMessage", "abc");
			modelAndView.setViewName("register");
			bindingResult.reject("nickname");
		}
		else {
			System.out.println("Else-Zweig");
		}
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.hasErrors());
			return "register";
		}
		if (user.getUserId() != null) {
			repository.update(user);
		} else {
			repository.save(user);
		}

		return "redirect:/";
	}
}
