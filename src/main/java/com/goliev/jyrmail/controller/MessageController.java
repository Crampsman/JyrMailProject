package com.goliev.jyrmail.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.goliev.jyrmail.dao.FolderDao;
import com.goliev.jyrmail.dao.UserDao;
import com.goliev.jyrmail.dto.TextMessageDTO;
import com.goliev.jyrmail.dto.UserDTO;
import com.goliev.jyrmail.service.FolderService;
import com.goliev.jyrmail.service.MessageService;
import com.goliev.jyrmail.service.MessageServiceImpl;
import com.goliev.jyrmail.service.UserService;
import com.goliev.jyrmail.smtp.ScheduledUpdateMessage;
import com.goliev.jyrmail.util.AesScramblerPassword;
import com.sun.tracing.dtrace.Attributes;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private UserService userServiceImpl;

    /*
     * @RequestMapping(value = "/update", method = RequestMethod.GET) public
     * String updateMessageInDataBase() throws Exception {
     * 
     * UserDetails user = (UserDetails)
     * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     * 
     * messageService.createMessages(user.getUsername(),
     * AesScramblerPassword.decryptPassword(user.getPassword()));
     * 
     * return "redirect:/message/account"; }
     */

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getMessages(Map<String, Object> model, @RequestParam(value = "page", required = false) Integer numPage,
	    @RequestParam(value = "folderId", required = false) Integer folderId) throws Exception {

	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	int page = 1;
	int recordsPerPage = 5;

	if (numPage != null) {
	    page = numPage;
	}

	if (folderId != null && folderId <= folderService.getFolders().size()) {
	    model.put("messages", messageService.getMessages(folderId, userDetails.getUsername(), (page - 1) * recordsPerPage, recordsPerPage));
	    model.put("currentFolder", folderService.getFolderById(folderId));
	} else {
	    model.put("messages", messageService.getMessages(1, userDetails.getUsername(), (page - 1) * recordsPerPage, recordsPerPage));
	    model.put("currentFolder", folderService.getFolderById(1));
	}
	int noOfRecords = messageService.getNoOfRecords();
	int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	model.put("folders", folderService.getFolders());
	model.put("noOfPages", noOfPages);
	model.put("currentPage", page);

	return "account";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getMessageEditPage(Map<String, Object> model) {

	TextMessageDTO textMessage = new TextMessageDTO();

	model.put("message", textMessage);

	return "add-message";

    }

    @RequestMapping(value = { "/edit", "/redirect/{message_id}" }, method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute("message") TextMessageDTO textMessage) throws Exception {

	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	String email = userDetails.getUsername();

	textMessage.setMailFrom(email);
	textMessage.setDirectoryId(folderService.getFolderIdByName("SENT"));
	textMessage.setUserId(userServiceImpl.getUserIdByEmail(email));
	String password = userDetails.getPassword();
	messageService.sendMessage(textMessage, AesScramblerPassword.decryptPassword(password));

	return "redirect:/message/account";

    }

    @RequestMapping(value = "/{message_id}", method = RequestMethod.GET)
    public String getMessage(Map<String, Object> model, @PathVariable("message_id") long id) throws MessagingException, IOException {

	TextMessageDTO message = messageService.getMessageById(id);
	model.put("messageDTO", message);

	return "message";
    }

    @RequestMapping(value = "/folder/{folderId}", method = RequestMethod.GET)
    public String getMessages(Map<String, Object> model, @PathVariable("folderId") long id) throws Exception {

	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	// model.put("messages", messageService.getMessages(id,
	// userDetails.getUsername()));
	model.put("folders", folderService.getFolders());

	return "account";

    }

    @RequestMapping(value = "/move/{message_id}", method = RequestMethod.GET)
    public String deleteMessage(@PathVariable("message_id") long messageId, @RequestParam(value = "folderId") int folderId) {

	messageService.transferMessageToFolder(messageId, folderId);

	return "redirect:/message/account";
    }

    @RequestMapping(value = "/redirect/{message_id}", method = RequestMethod.GET)
    public String redirectMessage(Map<String, Object> model, @PathVariable("message_id") long id) {

	TextMessageDTO textMessage = messageService.getMessageById(id);

	model.put("message", textMessage);

	return "add-message";

    }

}
