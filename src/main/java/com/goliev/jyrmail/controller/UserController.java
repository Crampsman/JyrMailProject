package com.goliev.jyrmail.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goliev.jyrmail.dao.RoleDao;
import com.goliev.jyrmail.dao.UserDao;
import com.goliev.jyrmail.dto.Role;
import com.goliev.jyrmail.dto.UserDTO;
import com.goliev.jyrmail.util.ImapMessageReader;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    private static final String STORE_TYPE = "imaps";

    private static final String IMAP_HOST = "imap.gmail.com";

    private ImapMessageReader messageReader = new ImapMessageReader();

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditPage(Map<String, Object> model) {

	model.put("user", new UserDTO());

	return "add-user";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String addUser(Map<String, Object> model, @ModelAttribute("user") UserDTO user) {

	boolean checkUser = true;

	try {

	    messageReader.createStore(IMAP_HOST, STORE_TYPE, user.getEmail(), user.getPassword());

	} catch (MessagingException e) {

	    checkUser = false;
	}

	if (checkUser && userDao.createUser(user)) {

	    Role role = new Role();
	    role.setRole("ROLE_USER");
	    role.setUserId(userDao.getUserIdByEmail(user.getEmail()));
	    roleDao.createRole(role);

	    return "redirect:/";

	} else {

	    if (!checkUser) {
		model.put("invalidUserMessage", "Your Gmail account does not exist or has blocked");
	    } else {
		model.put("invalidUserMessage", "Gmail account already exists");
	    }
	    return "add-user";
	}

    }
}
