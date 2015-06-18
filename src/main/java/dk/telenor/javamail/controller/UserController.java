package dk.telenor.javamail.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dk.telenor.javamail.dao.RoleDao;
import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dto.Role;
import dk.telenor.javamail.dto.UserDTO;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEditPage(Map<String, Object> model) {

		model.put("user", new UserDTO());

		return "add-user";

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String addUser(Map<String, Object> model,
			@ModelAttribute("user")  UserDTO user) throws Exception {

		//if (bindingResult.hasErrors()) {
			//return "add-user";
		//}

		Role role = new Role();

		if (userDao.createUser(user)) {

			role.setRole("ROLE_USER");
			role.setUserId(userDao.getUserIdByEmail(user.getEmail()));
			roleDao.createRole(role);

			return "redirect:/";
		}
		else{
			model.put("exists", "User already exists");
			return "add-user";
		}

	}
}
