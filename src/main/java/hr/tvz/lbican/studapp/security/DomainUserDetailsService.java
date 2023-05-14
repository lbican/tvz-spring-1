package hr.tvz.lbican.studapp.security;

import hr.tvz.lbican.studapp.data.AppUserDTO;
import hr.tvz.lbican.studapp.data.AuthorityDTO;
import hr.tvz.lbican.studapp.models.AppUser;
import hr.tvz.lbican.studapp.models.Authority;
import hr.tvz.lbican.studapp.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<List<AuthorityDTO>> testFetchAuthorities(String username) {
        Optional<AppUser> userOptional = userRepository.findOneByUsername(username);
        return userOptional.map(user -> user.getAuthorities()
                .stream()
                .map(authority -> new AuthorityDTO(authority.getId(), authority.getName()))
                .collect(Collectors.toList()));
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
        List<AuthorityDTO> authorities = user.getAuthorities()
                .stream()
                .map(this::mapAuthorityToDTO)
                .toList();

        return new AppUserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                authorities
        );
    }

    private AuthorityDTO mapAuthorityToDTO(Authority authority){
        return new AuthorityDTO(
                authority.getId(),
                authority.getName()
        );
    }


}
