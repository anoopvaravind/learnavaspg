package com.anoop.expmanager.controller;

import com.anoop.expmanager.model.User;
import com.anoop.expmanager.services.service.UserService;
import com.anoop.expmanager.util.Notification;
import com.anoop.expmanager.util.UserSession;
import com.anoop.expmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/6/17
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/app", "/app/", "/app/home"})
    public String home(Model model) throws Exception{
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() throws Exception{
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() throws Exception{
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() throws Exception{
        return "404";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/app/changepassword", method = RequestMethod.GET)
    public String getChangePasswordPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return "changepassword";
    }

    @RequestMapping(value = "/app/dochangepassword", method = RequestMethod.POST)
    @ResponseBody
    public Notification doChangePassword(@RequestParam("password") String password,@RequestParam("newPassword") String newPassword, @RequestParam("changePassword") String changePassword, HttpServletRequest request) throws Exception{
        System.out.println("Inside dochangepassword");
        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        /*if(userSession == null || userSession.getUser() == null) {
            return "redirect:/login?logout";
        }*/
        User user = userSession.getUser();

        System.out.println("user.getPassword() if###########  "+user.getPassword());
        System.out.println("Util.convertStringToMD5(password) if###########  "+Util.convertStringToMD5(password));

        if(!user.getPassword().equalsIgnoreCase(Util.convertStringToMD5(password))) {
            return new Notification(false,true,"Please check the existing password entered.",null);
        }
        user.setPassword(Util.convertStringToMD5(newPassword));
        userService.saveUser(user);
        return new Notification(true,false,"Changed password successfully!!!",null);
    }
}