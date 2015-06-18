package com.goliev.jyrmail.controller;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goliev.jyrmail.service.FolderService;
import com.goliev.jyrmail.service.MessageService;

@Controller
public class HomePageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getSignPage(){
		return "home";
	}
}
