package tn.kantra.kantraspring.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //step 1 extract jwt string from authorization header
        String token = getJwtFromRequest(request);
        //step 2 validate the token
        if(token != null && jwtGenerator.validateToken(token)){
            //step 3 extract username from token payload
            String username = jwtGenerator.getUsernameFromToken(token);
            //step 4 load full user details from database
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //step 5 build spring security authentication object
            UsernamePasswordAuthenticationToken authoken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //step 6 set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authoken);
        }

        filterChain.doFilter(request,response);

    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
