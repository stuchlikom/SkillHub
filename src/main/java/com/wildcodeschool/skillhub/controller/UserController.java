package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

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

		if (userExists != null) {
			bindingResult.rejectValue("nickName", "message.regError");
		}

		userExists = repository.findByMail(user.getMailAdress());

		if (userExists != null){
			bindingResult.rejectValue("mailAdress", "message.mailError");
		}
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.hasErrors());
			return "register";
		}
		if (user.getUserId() != null) {
            repository.update(user);
        } else {
			user.setRole("ROLE_USER");
            repository.save(user);
        }
        return "redirect:/";
	}
}
