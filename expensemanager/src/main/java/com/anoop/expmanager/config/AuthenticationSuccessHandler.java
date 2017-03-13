package com.anoop.expmanager.config;

import com.anoop.expmanager.services.impl.SessionObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/6/17
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    SessionObjectsService sessionObjectsService;

    private static final String HOME_PAGE = "/app/";

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        getRedirectStrategy().sendRedirect(request, response, HOME_PAGE);
        /*SavedRequest savedRequest = requestCache.getRequest(request, response);   */

        try {
            sessionObjectsService.setSessionObjects(request);
        } catch (Exception e) {
            e.printStackTrace();
        } /*
        if (savedRequest == null) {
            getRedirectStrategy().sendRedirect(request, response, HOME_PAGE);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();

        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                .getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);
        String targetUrl = savedRequest.getRedirectUrl();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);*/
    }



}
