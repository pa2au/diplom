package com.example.tametable.config;

import com.example.tametable.exception.ConfirmEmailException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            response.sendRedirect("/login?error");
        } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            response.sendRedirect("/login?disabled");
        } else if (exception.getCause().getClass().isAssignableFrom(ConfirmEmailException.class)) {
            response.sendRedirect("/login?email");
        }
    }
}
