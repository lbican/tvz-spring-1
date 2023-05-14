package hr.tvz.lbican.studapp.data;

import java.util.List;
import java.util.Set;

public record AppUserDTO(Long id, String username, String firstName, String lastName, List<AuthorityDTO> authorities) {
}
