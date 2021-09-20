package fr.groupe1.goevent.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.service.EmailService;
import fr.groupe1.goevent.service.IUserService;
import fr.groupe1.goevent.util.Utility;
import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

	@Autowired
    private EmailService emailService;;
     
    @Autowired
    private IUserService userService;
     
    @GetMapping("/resetPassword")
    public String showForgotPasswordForm(Model model) {
    	return "resetPassword";
    }
 
    @PostMapping("/resetPassword")
    public String processForgotPassword(HttpServletRequest request,Model model) throws UnsupportedEncodingException, MessagingException {
    	String email = request.getParameter("email");
        String token = RandomString.make(30);
     
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword2?token=" + token;
            emailService.sendForgottenPasswordLink(email, resetPasswordLink);
            String message ="Vous allez recevoir un email";
            model.addAttribute("message", message);
            return "resetPassword";
    }
      
     
    @GetMapping("/resetPassword2")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        System.out.println(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }      
        return "resetPassword2";
    }
     
    @PostMapping("/resetPassword2")
    public String processResetPassword(HttpServletRequest request, Model model) {
    	String token = request.getParameter("token");
    	System.out.println("ça marche?");
    	System.out.println(token);
        String password = request.getParameter("password");
    	System.out.println(password);
        User user=userService.getByResetPasswordToken(token);
        System.out.println(token);
        userService.updatePassword(user, password);
        model.addAttribute("message","Votre mot de passe a été changé avec succés");
        return "redirect:login";
    }
}
