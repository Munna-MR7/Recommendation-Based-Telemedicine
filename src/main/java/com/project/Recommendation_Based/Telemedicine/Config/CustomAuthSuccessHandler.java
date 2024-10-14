package com.project.Recommendation_Based.Telemedicine.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Retrieve the original URL (saved request)
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // If there is a saved request (the original URL user was trying to access)
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            response.sendRedirect(targetUrl);  // Redirect to the original URL
        } else {
            // Fallback: if no saved request, redirect to a default page (user home or admin home)
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/home");
            } else {
                response.sendRedirect("/user/home");
            }
        }
    }
}
