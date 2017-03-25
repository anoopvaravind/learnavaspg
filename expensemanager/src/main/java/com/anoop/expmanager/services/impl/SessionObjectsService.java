package com.anoop.expmanager.services.impl;

import com.anoop.expmanager.services.service.UserService;
import com.anoop.expmanager.util.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/6/17
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("sessionObjectsService")
public class SessionObjectsService {

    @Autowired
    UserService userService;

    private UserSession userSession = new UserSession();

    private Authentication authentication;;

    public void setSessionObjects(HttpServletRequest request)throws Exception{
        setCurrentUser();
        request.getSession().setAttribute("userSession",userSession);
        UserSession usr = (UserSession)request.getSession().getAttribute("userSession");
        System.out.println("user session >>"+usr.getUser().getDisplayName());
    }

    public void setCurrentUser() throws Exception{
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication !=null)
            userSession.setUser(userService.getUserByUserName(authentication.getName().toString()));
        else
            System.out.println("Authentication is null");
    }
    /*
    public void populateMenu()throws Exception{
        authentication =SecurityContextHolder.getContext().getAuthentication();

        if(authentication !=null){
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)authentication.getAuthorities();
            roles = rolesService.getByName(authorities.iterator().next().getAuthority());
        }else
            roles = null;

        if(NotEmpty.notEmpty(roles))
        {
            Set<Menu> menuSet = roles.getMenuSet();
            ArrayList<Menu> menuList = new ArrayList<Menu>(menuSet);
            userSession.setMenuList(menuList);
        }

    }*/


}
