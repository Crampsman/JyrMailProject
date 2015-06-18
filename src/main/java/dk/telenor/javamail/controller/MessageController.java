package dk.telenor.javamail.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sun.tracing.dtrace.Attributes;

import dk.telenor.javamail.dao.UserDao;
import dk.telenor.javamail.dto.TextMessageDTO;
import dk.telenor.javamail.dto.UserDTO;
import dk.telenor.javamail.service.FolderService;
import dk.telenor.javamail.service.MessageService;
import dk.telenor.javamail.service.MessageServiceImpl;
import dk.telenor.javamail.service.UserService;
import dk.telenor.javamail.smtp.ScheduledUpdateMessage;
import dk.telenor.javamail.util.AesScramblerPassword;

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
    public String getMessages(Map<String, Object> model) throws Exception {

	UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	model.put("messages", messageService.getMessages(1, user.getUsername()));
	model.put("folders", folderService.getFolders());

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

	UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	String email = user.getUsername();

	textMessage.setMailFrom(email);
	textMessage.setDirectoryId(folderService.getFolderIdByName("SENT"));
	textMessage.setUserId(userServiceImpl.getUserIdByEmail(email));
	String password = user.getPassword();
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

	UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	model.put("messages", messageService.getMessages(id, user.getUsername()));
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
