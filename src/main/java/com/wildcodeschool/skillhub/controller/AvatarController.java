package com.wildcodeschool.skillhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wildcodeschool.skillhub.repository.AvatarRepository;

@Controller
public class AvatarController {

	private AvatarRepository repository = new AvatarRepository(); // Deklaration des Repositories

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
