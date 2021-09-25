package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Value
public class UserAdvisorCreateDto {
    @NotNull
    @NotBlank
    String registration;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 22)
    String password;

    @NotNull
    @NotBlank
    @Email
    String email;

    @NotNull
    Collection<Role> roles;

    @NotNull
    List<UUID> coursesIds;
}
