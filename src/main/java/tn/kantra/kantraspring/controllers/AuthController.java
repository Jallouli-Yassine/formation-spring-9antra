package tn.kantra.kantraspring.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.kantra.kantraspring.DTO.AuthResponseDTO;
import tn.kantra.kantraspring.DTO.LoginDTO;
import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.RoleName;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.repositories.RoleRepo;
import tn.kantra.kantraspring.repositories.UserRepo;
import tn.kantra.kantraspring.security.JWTGenerator;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final RoleRepo roleRepo;


    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, RoleRepo roleRepo) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.roleRepo = roleRepo;
    }

    @PostConstruct
    public void createDefaultAdmin(){
        if (!userRepo.existsByUsername("admin")){
            UserEntity newAdmin = new UserEntity();
            newAdmin.setUsername("admin");
            newAdmin.setEmail("admin@demo.tn");
            newAdmin.setName("AdminFirstname");
            newAdmin.setLastname("AdminLastname");
            newAdmin.setPassword(passwordEncoder.encode("admin123"));
            RoleEntity adminRole = roleRepo.findRoleByRoleName(RoleName.ADMIN);
            if(adminRole == null){
                adminRole = new RoleEntity();
                adminRole.setName(RoleName.ADMIN);
                roleRepo.save(adminRole);
                newAdmin.setRole(adminRole);
            }else {
                newAdmin.setRole(adminRole);
            }
        userRepo.save(newAdmin);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            //step 1 : authentication manager verify creadentials of the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            //step 2 store the authentication object in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // step 3 : generate jwt token
            String token = jwtGenerator.generateToken(authentication);

            AuthResponseDTO response = new AuthResponseDTO();
            response.setToken(token);

            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

    }

















}
