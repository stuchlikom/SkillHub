package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.entity.Avatar;
import com.wildcodeschool.skillhub.repository.UserRepository;
import com.wildcodeschool.skillhub.repository.AvatarRepository;
import com.wildcodeschool.skillhub.repository.LoggedInUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import javax.validation.Valid;

@Controller
public class UserController {

	private UserRepository repository = new UserRepository();

	@GetMapping("/register")
	public String getUser(Model model, @RequestParam(required = false) Long userid)
	{
		User user = new User();
		System.out.println("UserID: " + userid);
		if (userid != null) {
			user = repository.findById(userid);
		}
		model.addAttribute("user", user);
		return "register";
	}

	@GetMapping("/user")
	public String showUser(Model model, Principal principal) 
	{
		System.out.println("Bin in @GetMapping");
		model.addAttribute("user", repository.findByNick(principal.getName()));
		model.addAttribute("avatar", new Avatar());
		Long loggedInUserId = LoggedInUserRepository.findId();
        model.addAttribute("loggedInUserId", loggedInUserId);
		return "user";
	}

	@PostMapping("/user")
	public String UserSubmit(@ModelAttribute User user, BindingResult bindingResult, Principal principal, Model model){

		User userExists = repository.findByNick(user.getNickName());
		User actualUser;
		model.addAttribute("avatar", new Avatar());
		Long loggedInUserId = LoggedInUserRepository.findId();
        model.addAttribute("loggedInUserId", loggedInUserId);		

		System.out.println("Id: " + user.getUserId());
		System.out.println("Nickname: " + user.getNickName());
		System.out.println("Principal: " + principal.getName());
		System.out.println("User exists: " + userExists);
		
		if ((userExists != null) && (! principal.getName().equals(user.getNickName()))) {
			System.out.println("Nickname existiert bereits");
			bindingResult.rejectValue("nickName", "message.regError");
		}

		userExists = repository.findByMail(user.getMailAdress());
		System.out.println("User exists: " + userExists);

		if (userExists != null)
			System.out.println("userExists-Mail: " + userExists.getMailAdress());
		else
			System.out.println("userExists = null");

		actualUser = repository.findByNick(principal.getName());
		System.out.println("actualUser-Mail: " + actualUser.getMailAdress());

		if ((userExists != null) && (! userExists.getMailAdress().equals(actualUser.getMailAdress()))){
			bindingResult.rejectValue("mailAdress", "message.mailError");
		}
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.hasErrors());
			return "user";
		}
		//System.out.println("Passwort alt: " + userExists.getPassWord());
		System.out.println("Passwort neu: " + actualUser.getPassWord());
		System.out.println("Passwort user: " + user.getPassWord());

		if (user.getUserId() != null) {
			if (user.getPassWord() != "") {
				repository.update(user);
			}
			else {
				System.out.println("Update without Passwort");
				user.setPassWord(actualUser.getPassWord()); // sonst würde hier ein leerer String übergeben werden
				repository.updateWithoutPassword(user);
			}
		}
		return "index";
	}


	@PostMapping("/register")
	public String checkPersonInfo(@Valid User user, BindingResult bindingResult, Model model) {
		

		if (user.getPassWord().equals(user.getRole())){
			System.out.println("Passwörter gleich");
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
				//avatar.setAvatar()
            	repository.save(user);
			}
		}
		else {
			System.out.println("Passwörter unterschiedlich");
			bindingResult.rejectValue("passWord", "message.passWord");
			return "register";
		}
        return "redirect:/user";
	}
}
