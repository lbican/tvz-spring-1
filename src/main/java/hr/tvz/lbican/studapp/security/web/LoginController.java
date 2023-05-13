package hr.tvz.lbican.studapp.security.web;

import hr.tvz.lbican.studapp.data.AppUserDTO;
import hr.tvz.lbican.studapp.security.DomainUserDetailsService;
import hr.tvz.lbican.studapp.security.jwt.JwtFilter;
import hr.tvz.lbican.studapp.security.jwt.TokenProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final DomainUserDetailsService domainUserDetailsService;


    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, DomainUserDetailsService domainUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginController.LoginDTO login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.username(),
                login.password()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/user/current-user")
    public ResponseEntity<AppUserDTO> getCurrentUser(){
        Optional<AppUserDTO> currentUser = domainUserDetailsService.getCurrentUser();

        return currentUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }




    /**
     * Return jwt token in body because Angular has problems with parsing plain string response entity
     */
    static class JWTToken {
        private String token;

        public JWTToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    record LoginDTO(@NotNull String username, @NotNull String password) {}

}
