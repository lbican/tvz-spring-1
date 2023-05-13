package hr.tvz.lbican.studapp.security;

import hr.tvz.lbican.studapp.data.AppUserDTO;
import hr.tvz.lbican.studapp.models.AppUser;
import hr.tvz.lbican.studapp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {

        return userRepository
                .findOneByUsername(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    @Transactional
    public Optional<AppUserDTO> getCurrentUser() {
        Optional<String> currentUsername = SecurityUtils.getCurrentUserUsername();
        return currentUsername.flatMap(username ->
                userRepository.findOneByUsername(username)
                        .map(this::mapAppUserToDTO)
        );
    }




    private org.springframework.security.core.userdetails.User createSpringSecurityUser(AppUser user) {
        List<? extends GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private AppUserDTO mapAppUserToDTO(AppUser user){
        return new AppUserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getAuthorities()
        );
    }


}
