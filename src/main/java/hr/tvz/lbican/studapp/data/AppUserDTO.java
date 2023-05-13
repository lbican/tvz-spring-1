package hr.tvz.lbican.studapp.data;

import hr.tvz.lbican.studapp.models.Authority;

import java.util.Set;

public record AppUserDTO(Long id, String username, String firstName, String lastName, Set<Authority> authorities) {
}
