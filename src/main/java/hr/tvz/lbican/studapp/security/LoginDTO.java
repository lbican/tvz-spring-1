package hr.tvz.lbican.studapp.security;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(@NotNull String username, @NotNull String password) {
}
