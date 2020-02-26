package com.wildcodeschool.skillhub.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.wildcodeschool.skillhub.entity.Avatar;

import com.wildcodeschool.skillhub.repository.AvatarRepository;
import com.wildcodeschool.skillhub.repository.LoggedInUserRepository;

@Controller
public class AvatarController {

	private AvatarRepository repository = new AvatarRepository(); // Deklaration des Repositories
	private LoggedInUserRepository logrepository = new LoggedInUserRepository(); // Deklaration

	@GetMapping("/avataradmin")
	public String imageuploadform(Model model, Principal principal) {
		model.addAttribute("avatar", new Avatar());
		model.addAttribute("username", principal.getName());
		
		return "avataradmin";
	}
	
	@PostMapping("/avataradmin")
	public String imageload(Avatar avatar /*,Principal principal*/) {
		Long avatarId = logrepository.findId();
		avatar.setAvatarId(avatarId);
		repository.update(avatar);
		return "redirect:/user";
	}
	
	@PostMapping("/avataruser")
	public String imageloadUser(Avatar avatar /*,Principal principal*/) {
		Long avatarId = logrepository.findId();
		avatar.setAvatarId(avatarId);
		repository.update(avatar);
		return "redirect:/questions";
	}

	@GetMapping("/image/{avatarId}")
	public ResponseEntity<byte[]> loadImage(@PathVariable(required = false) Long avatarId) {

		byte[] dummyReturnValue = new byte[0];
		try {

			byte[] imageAsByteArray;

			if (repository.findById(avatarId) != null) {
				imageAsByteArray = repository.findById(avatarId).getAvatar(); // Zugriff auf den Avatar (Bild)
			} else {
				imageAsByteArray = dummyReturnValue;
			}

			return ResponseEntity.status(HttpStatus.OK)//
					.contentType(MediaType.IMAGE_JPEG)//
					.body(imageAsByteArray);

		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)//
					.body(dummyReturnValue);
		}

	}
	
//	@GetMapping("/admin/user/delete") // soll aus der Userveraltung (Einzeluser bzw. Admin) aufgerufen werden - Einbindung fehlt noch - genauso wie save/ update
//    public String deleteAvatar(@RequestParam Long avatarId)
//    {
//       repository.deleteById(avatarId);
//       System.out.println("/admin/user/delete |" + "|");
//       return "redirect:/admin/users";
//    }
}
