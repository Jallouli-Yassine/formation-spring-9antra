package tn.kantra.kantraspring.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // 401 Unauthorized : when the user is not authenticated
        //403 Forbidden : when the user is authenticated but does not have the required permissions
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
















}
