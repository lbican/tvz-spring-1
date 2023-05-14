package hr.tvz.lbican.studapp.user;

import java.util.Set;

public record AppUserDTO(Long id, String username, String firstName, String lastName, Set<Authority> authorities) {
}
