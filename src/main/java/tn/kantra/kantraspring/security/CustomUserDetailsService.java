package tn.kantra.kantraspring.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.repositories.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u = userRepo.findUserEntityByUsername(username);
        if(u == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(u.getUsername(),u.getPassword(),mapRolesToAuthorities(u.getRole()));
    }

    private Collection mapRolesToAuthorities(RoleEntity userRole){
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getName().name()));
        return authorities;
    }
}
