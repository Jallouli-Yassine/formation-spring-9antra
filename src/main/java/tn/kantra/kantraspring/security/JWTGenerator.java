package tn.kantra.kantraspring.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.repositories.UserRepo;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JWTGenerator {

    private UserRepo userRepo;

    public JWTGenerator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private static final Key SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //generate jwt after successful authentication
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date dateNow = new Date();
        Date expirationDate = new Date(dateNow.getTime() + SecurityConstants.jwtExpiration);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        UserEntity user = userRepo.findUserEntityByUsername(username);

        return Jwts.builder()
                .setSubject(username)
                .claim("user", Map.of(
                        "id", user.getId(),
                        "username",user.getUsername(),
                        "email",user.getEmail()
                ))
                .claim("roles",roles)
                .setIssuedAt(dateNow)
                .setExpiration(expirationDate)
                .signWith(SecretKey)
                .compact();

    }

    //get username from token
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //validate token
    public Boolean validateToken(String token){
        try {
             Jwts.parserBuilder()
                    .setSigningKey(SecretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException(
                    "JWT token is expired or invalid", e.fillInStackTrace()
            );
        }

    }



















}
